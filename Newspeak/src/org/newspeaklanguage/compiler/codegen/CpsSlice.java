/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.MessageSendWithReceiver;
import org.newspeaklanguage.compiler.semantics.VariableReference;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A slice is a fragment of an expression coverted to CPS
 * which will become a single function. For example,
 * <pre>
 *   self foo + self bar
 * </pre>
 * consists of the following slices (where the pseudocode {@code boundTo()} is understood
 * essentially as the actual bindTo(), except that it potentially bind multiple arguments):
 * <pre>
 *   s1(k) = foo(self, s2.boundTo(k))
 *   s2(k, a) = bar(self, s3.boundTo(k, a))
 *   s3(k, a, b) = +(a, b, k)
 * </pre>
 * A slice always has the argument 'k' for the original expression's continuation passed down
 * to the final slice (which produces the values of the expression). It is always the first
 * argument.
 * <p>
 * All slices but the first (the one with no precedingSlice) always have the argument for
 * the previous slice's value. It is always the last argument.
 * <p>
 * A slice can have additional arguments for values passed down the line to some future
 * continuations. (In typical academic CPS examples written in Scheme, such values are
 * available lexically instead of being passed down explicitly). These arguments are discovered
 * as part of the whole translation process. They are tracked by the slice. In the final
 * function they go in between the final continuation and the previous slice result.
 * (Or one could consider the final continuation as one of such passed down values).
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class CpsSlice {

  static class InitialSlice extends CpsSlice {
    InitialSlice() {
      super(null);
    }

    @Override
    protected void linkToInboundArgumentOfNextSlice(InboundArgument arg) {
      OutboundArgument target = arg.finalDestination();
      if (target instanceof MethodVarReference) {
        PassDownArgument var = new PassDownArgument(arg);
        MethodVarSource varSource = new MethodVarSource(var, ((MethodVarReference) target).node.definition().name());
        var.setSource(varSource);
        passDownArguments.add(var);
      } else {
        super.linkToInboundArgumentOfNextSlice(arg);
      }
    }
  }
  /**
   * Describes the argument passed to the terminating call of the slice.
   * Not to be confused with the arguments of the slice itself
   * ({@link InboundArgument}).
   */
  static abstract class OutboundArgument {
    private InboundArgument supplier;

    public InboundArgument supplier() {
      return supplier;
    }

    public void setSupplier(InboundArgument supplier) {
      this.supplier = supplier;
    }

    public abstract void accept(OutboundArgumentVisitor visitor);
  }
  /**
   * A reference to the result of a prior slice. If the slice is not the immediate
   * preceding slice, it needs to be copied down from the slice following the one
   * that produced the result.
   */

  static class IntermediateResult extends OutboundArgument {
    private final String selector;

    public IntermediateResult(String selector) {
      this.selector = selector;
    }

    @Override
    public void accept(OutboundArgumentVisitor visitor) {
      visitor.visitIntermediateResult(this);
    }

    @Override
    public String toString() {
      return "IntermediateResultOf{" + selector + "}";
    }
  }
  /**
   * A reference to an argument or a temp of the method hosting the original
   * statement. If located in a chained slice, it needs to be copied down
   * the chain.
   */
  static class MethodVarReference extends OutboundArgument {
    private final VariableReference node;

    MethodVarReference(VariableReference node) {
      this.node = node;
    }

    @Override
    public void accept(OutboundArgumentVisitor visitor) {
      visitor.visitMethodVarReference(this);
    }
  }

  /**
   * A reference to something that can be loaded no matter its location,
   * not needing to be copied down the chain of slices. Things like
   * {@code self} or literals are pervasive.
   */
  static class PervasiveReference extends OutboundArgument {
    private final AstNode node;

    PervasiveReference(AstNode node) {
      this.node = node;
    }

    @Override
    public void accept(OutboundArgumentVisitor visitor) {
      visitor.visitPervasiveReference(this);
    }
  }

  static class PassDownArgument extends OutboundArgument {
    private InboundArgument source;
    private final InboundArgument destination;

    PassDownArgument(InboundArgument destination) {
      this.destination = destination;
    }

    public void setSource(InboundArgument source) {
      this.source = source;
    }

    public OutboundArgument finalDestination() {
      return destination.finalDestination();
    }

    @Override
    public void accept(OutboundArgumentVisitor visitor) {
      visitor.visitPassThroughArgument(this);
    }
  }

  static interface OutboundArgumentVisitor {
    void visitPervasiveReference(PervasiveReference pervasiveReference);
    void visitMethodVarReference(MethodVarReference methodVarReference);
    void visitIntermediateResult(IntermediateResult intermediateResult);
    void visitPassThroughArgument(PassDownArgument passDownArgument);
  }


  static class InboundArgument {
    private final OutboundArgument destination;
    private int index;

    InboundArgument(OutboundArgument destination) {
      this.destination = destination;
      destination.setSupplier(this);
    }

    public int index() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public OutboundArgument finalDestination() {
      if (destination instanceof PassDownArgument) {
        return ((PassDownArgument) destination).finalDestination();
      } else
        return destination;
    }
  }

  static class MethodVarSource extends InboundArgument {
    private final String name;

    MethodVarSource(OutboundArgument destination, String name) {
      super(destination);
      this.name = name;
    }
  }

  /*
   * Instance side
   */

  protected final int index;
  private final CpsSlice precedingSlice;
  private MessageSendWithReceiver terminator;
  private final List<OutboundArgument> terminatorArguments = new LinkedList<>();
  protected final List<PassDownArgument> passDownArguments = new LinkedList<>();
  private IntermediateResult result;
  private final List<InboundArgument> inboundArguments = new LinkedList<>();
  /** The argument for the result received by this continuation. */
  private InboundArgument inboundResult;

  CpsSlice(CpsSlice precedingSlice) {
    this.precedingSlice = precedingSlice;
    this.index = precedingSlice == null ? 0 : precedingSlice.index + 1;
  }

  public CpsSlice precedingSlice() {
    return precedingSlice;
  }

  public void addArgumentHandles(Collection<OutboundArgument> outboundArguments) {
    this.terminatorArguments.addAll(outboundArguments);
  }

  public void setTerminator(MessageSendWithReceiver terminator) {
    assert terminator.arity() + 1 /* the receiver */ == terminatorArguments.size();
    this.terminator = terminator;
  }

  public IntermediateResult result() {
    return result;
  }

  public void setResult(IntermediateResult result) {
    this.result = result;
  }

  public void linkArguments() {
    linkTerminatorArguments();
    if (precedingSlice != null) {
      precedingSlice.linkArgumentsFrom(this);
    }
  }

  protected void linkArgumentsFrom(CpsSlice next) {
    next.inboundArguments.forEach(this::linkToInboundArgumentOfNextSlice);
    linkArguments();
  }

  protected void linkToInboundArgumentOfNextSlice(InboundArgument arg) {
    PassDownArgument outArg = new PassDownArgument(arg);
    OutboundArgument destination = arg.destination;
    if (destination instanceof IntermediateResult && precedingSlice.result().equals(destination)) {
      inboundResult = new InboundArgument(outArg);
      outArg.setSource(inboundResult);
    } else {
      InboundArgument inArg = new InboundArgument(outArg);
      outArg.setSource(inArg);
      passDownArguments.add(outArg);
      inboundArguments.add(inArg);
    }
  }

  private void linkTerminatorArguments() {
    for (OutboundArgument arg : terminatorArguments) {
      arg.accept(new OutboundArgumentVisitor() {
        @Override
        public void visitPervasiveReference(PervasiveReference pervasiveReference) {
          // nothing to do
        }

        @Override
        public void visitMethodVarReference(MethodVarReference methodVarReference) {
          InboundArgument arg = new InboundArgument(methodVarReference);
          inboundArguments.add(arg);
        }

        @Override
        public void visitIntermediateResult(IntermediateResult target) {
          if (precedingSlice.result().equals(target)) {
            inboundResult = new InboundArgument(target);
          } else {
            InboundArgument arg = new InboundArgument(target);
            inboundArguments.add(arg);
          }
        }

        @Override
        public void visitPassThroughArgument(PassDownArgument passDownArgument) {
          throw new IllegalArgumentException("Unexpected argument type");
        }
      });
    }
  }

  public void printDetailsTo(StringBuilder builder) {
    if (precedingSlice != null) {
      precedingSlice.printDetailsTo(builder);
    }

    builder.append("Slice$").append(index).append("(");
    Map<InboundArgument, String> argNames = new HashMap<>();
    int count = 0;
    for (InboundArgument arg : inboundArguments) {
      String argName = "arg" + count++;
      argNames.put(arg, argName);
      if (count > 1) {
        builder.append(", ");
      }
      builder.append(argName);
    }
    if (inboundResult != null) {
      argNames.put(inboundResult, "r");
      if (count > 0) {
        builder.append(", ");
      }
      builder.append("r");
    }
    builder.append(")\n\t");

    builder.append(terminator.selector() + "(");
    count = 0;
    for (OutboundArgument arg : terminatorArguments) {
      String argName = argNames.get(arg.supplier);
      if (argName == null) {
        if (arg instanceof PervasiveReference) {
          argName = ((PervasiveReference) arg).node.toString();
        } else {
          argName = "?";
        }
      }
      if (count++ > 0) {
        builder.append(", ");
      }
      builder.append(argName);
    }

    // pass down (continuation bound) args
    if (count++ > 0) {
      builder.append(", ");
    }
    builder.append("k(");
    count = 0;
    for (PassDownArgument arg : passDownArguments) {
      if (count++ > 0) {
        builder.append(", ");
      }
      String name = argNames.get(arg.source);
      if (name == null) {
        if (arg.source instanceof MethodVarSource) {
          name = ((MethodVarSource) arg.source).name;
        } else
          name = "?";
      }
      builder.append(name);
    }
    builder.append(")");
    builder.append(")\n");
  }
}
