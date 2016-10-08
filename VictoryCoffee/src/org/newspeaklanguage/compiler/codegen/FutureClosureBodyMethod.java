package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.runtime.Object;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class FutureClosureBodyMethod {
  
  public static final String BOGUS_CLOSURE_CALL_TYPE_DESCRIPTOR = "()" + Object.TYPE_DESCRIPTOR;
      
  
  private final MethodGenerator hostMethodGenerator;
  private final Block blockNode;
  private final String methodName;
  
  FutureClosureBodyMethod(MethodGenerator hostMethodGenerator, Block blockNode, String methodName) {
    this.hostMethodGenerator = hostMethodGenerator;
    this.blockNode = blockNode;
    this.methodName = methodName;
  }
  
  public Block blockNode() { return blockNode; }
  public String methodName() { return methodName; }

  public void generate() {
    MethodVisitor writer = hostMethodGenerator.classWriter().visitMethod(
        Opcodes.ACC_PUBLIC, 
        methodName, 
        // TODO the type descriptor should be computed based on the closure arity
        BOGUS_CLOSURE_CALL_TYPE_DESCRIPTOR, 
        null, null);
    CodeGenerator generator = new ClosureBodyGenerator(hostMethodGenerator, blockNode, writer);
    generator.generate();
  }

}
