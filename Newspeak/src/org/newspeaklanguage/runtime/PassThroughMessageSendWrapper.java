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

package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * The call site manager for the sites of pass-through ("setter") message sends.
 * Those sends are always arity 1 and return the argument rather than the result
 * of the message send.
 * <p>
 * Generating regular bytecode for those sites is extremely tricky when each argument
 * translates to two stack entries, because of the need to copy the int/Object
 * pair of the argument to the location on the stack below the int/Object pair of the receiver,
 * to be eventually used as the result. Fortunately, {@link java.lang.invoke} offers
 * an elegant altenative to resorting to the use of temps to solve this problem.
 * <p>
 * Instead of stack acrobatics, a pass-through send site is equipped with the {@link #bootstrap}
 * method below. The method first delegates to the original {@link MessageDispatcher#bootstrap}
 * to construct the standard {@link MessageSendSite}. Then we effectively wrap that site
 * with one that uses the {@link #call} method below to invoke the original site and then
 * discard the result, instead returning the argument.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class PassThroughMessageSendWrapper {

  private static final MethodType CALL_METHOD_TYPE = MethodType.methodType(Object.class,
      MethodHandle.class, int.class, Object.class, int.class, Object.class);

  public static Handle bootstrapHandle() {
    return new Handle(
        Opcodes.H_INVOKESTATIC,
        Descriptor.internalClassName(PassThroughMessageSendWrapper.class),
        "bootstrap",
        MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class)
            .toMethodDescriptorString(),
        false);
  }

  @SuppressWarnings("unused") // invokedynamic instruction bootstrapper
  public static CallSite bootstrap(MethodHandles.Lookup callSiteLookup, String name, MethodType callSiteType)
      throws NoSuchMethodException, IllegalAccessException
  {
    CallSite dispatchingSite = MessageDispatcher.bootstrap(callSiteLookup, name, callSiteType);
    MethodHandle wrapperCall = MethodHandles.lookup()
        .findStatic(PassThroughMessageSendWrapper.class, "call", CALL_METHOD_TYPE)
        .bindTo(dispatchingSite.dynamicInvoker());
    return new ConstantCallSite(wrapperCall);
  }

  @SuppressWarnings("unused") // linked to call sites
  public static Object call(MethodHandle target, int intReceiver, Object receiver, int intArg, Object arg) throws Throwable {
    Object ignored = target.invokeExact(intReceiver, receiver, intArg, arg);
    if (arg == NsObject.UNDEFINED) {
      IntReturnStack.push(intArg);
    }
    return arg;
  }
}
