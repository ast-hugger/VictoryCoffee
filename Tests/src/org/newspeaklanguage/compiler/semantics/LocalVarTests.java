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

package org.newspeaklanguage.compiler.semantics;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.Method;

public class LocalVarTests {

  @Test
  public void testMethodArgsAndTemps() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: arg1 and: arg2 = ( | temp1 temp2 |"
    + "  temp1: arg1 + arg2."
    + "  temp2: temp1 - arg2."
    + "  ^temp2)"
    + ")");
    CodeScope methodScope = ((Method) NodeFinder.find(Method.class, tree)).scope();
    LocalVariable arg1 = methodScope.ownVariableNamed("arg1").get();
    LocalVariable arg2 = methodScope.ownVariableNamed("arg2").get();
    LocalVariable temp1 = methodScope.ownVariableNamed("temp1").get();
    LocalVariable temp2 = methodScope.ownVariableNamed("temp2").get();   
    MessageSendNoReceiver arg1ref = NodeFinder.findLocalVarReference("arg1", tree);
    MessageSendNoReceiver arg2ref = NodeFinder.findLocalVarReference("arg2", tree);
    MessageSendNoReceiver temp1ref = NodeFinder.findLocalVarReference("temp1", tree);
    MessageSendNoReceiver temp2ref = NodeFinder.findLocalVarReference("temp2", tree);

    assertEquals(methodScope, varOf(arg1ref).definition().scope());
    assertEquals(methodScope, varOf(arg2ref).definition().scope());
    assertEquals(methodScope, varOf(temp1ref).definition().scope());
    assertEquals(methodScope, varOf(temp2ref).definition().scope());

    assertEquals(arg1, varOf(arg1ref).localVariable());
    assertEquals(arg2, varOf(arg2ref).localVariable());
    assertEquals(temp1, varOf(temp1ref).localVariable());
    assertEquals(temp2, varOf(temp2ref).localVariable());

    assertArrayEquals(
        new String[]{"arg1", "arg2", "temp1", "temp2"}, 
        methodScope.ownVariableNames().toArray());
    assertEquals(2, arg1.index());
    assertEquals(4, arg2.index());
    assertEquals(6, temp1.index());
    assertEquals(8, temp2.index());
    
    assertFalse(arg1.isBoxed());
    assertFalse(arg2.isBoxed());
    assertFalse(temp1.isBoxed());
    assertFalse(temp2.isBoxed());
  }

  @Test
  public void testCleanBlockArgsAndTemps() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test = ("
    + "  ^[:arg1 :arg2 | | temp1 temp2 |"
    + "   temp1: arg1 + arg2."
    + "   temp2: temp1 - arg2."
    + "   temp2]"
    + "     value: 3 value: 4)"
    + ")");
    CodeScope blockScope = ((Block) NodeFinder.find(Block.class, tree)).scope();
    LocalVariable arg1 = blockScope.ownVariableNamed("arg1").get();
    LocalVariable arg2 = blockScope.ownVariableNamed("arg2").get();
    LocalVariable temp1 = blockScope.ownVariableNamed("temp1").get();
    LocalVariable temp2 = blockScope.ownVariableNamed("temp2").get();   
    MessageSendNoReceiver arg1ref = NodeFinder.findLocalVarReference("arg1", tree);
    MessageSendNoReceiver arg2ref = NodeFinder.findLocalVarReference("arg2", tree);
    MessageSendNoReceiver temp1ref = NodeFinder.findLocalVarReference("temp1", tree);
    MessageSendNoReceiver temp2ref = NodeFinder.findLocalVarReference("temp2", tree);

    assertEquals(blockScope, varOf(arg1ref).definition().scope());
    assertEquals(blockScope, varOf(arg2ref).definition().scope());
    assertEquals(blockScope, varOf(temp1ref).definition().scope());
    assertEquals(blockScope, varOf(temp2ref).definition().scope());

    assertEquals(arg1, varOf(arg1ref).localVariable());
    assertEquals(arg2, varOf(arg2ref).localVariable());
    assertEquals(temp1, varOf(temp1ref).localVariable());
    assertEquals(temp2, varOf(temp2ref).localVariable());

    assertArrayEquals(
        new String[]{"arg1", "arg2", "temp1", "temp2"}, 
        blockScope.ownVariableNames().toArray());
    assertEquals(1, arg1.index());
    assertEquals(3, arg2.index());
    assertEquals(5, temp1.index());
    assertEquals(7, temp2.index());
  }

  @Test
  public void testCopiedVar() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: arg1 = ("
    + "  ^[:arg2 | arg1 + arg2]"
    + "     value: 3)"
    + ")");
    CodeScope methodScope = ((Method) NodeFinder.find(Method.class, tree)).scope();
    BlockScope blockScope = (BlockScope) ((Block) NodeFinder.find(Block.class, tree)).scope();
    LocalVariable arg1 = methodScope.ownVariableNamed("arg1").get();
    LocalVariable arg2 = blockScope.ownVariableNamed("arg2").get();    
    MessageSendNoReceiver arg1ref = NodeFinder.findLocalVarReference("arg1", tree);
    MessageSendNoReceiver arg2ref = NodeFinder.findLocalVarReference("arg2", tree);

    assertTrue(varOf(arg1ref).isCopiable());
    assertTrue(varOf(arg2ref).isLocal());
    
    assertArrayEquals(new String[]{"arg1"}, methodScope.ownVariableNames().toArray());
    assertEquals(2, arg1.index());

    assertArrayEquals(new String[]{"arg2"}, blockScope.ownVariableNames().toArray());
    assertArrayEquals(new String[]{"arg1"}, blockScope.copiedVariableNames().toArray());
    assertEquals(1, blockScope.copiedVariableNamed("arg1").get().index());
    assertEquals(3, arg2.index());

    assertFalse(arg1.isBoxed());
    assertFalse(arg2.isBoxed());
  }
  
  @Test
  public void testCopiedBoxedVar() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test = ( | temp |"
    + "  ^[:arg2 | temp + arg2]"
    + "     value: 3)"
    + ")");
    CodeScope methodScope = ((Method) NodeFinder.find(Method.class, tree)).scope();
    BlockScope blockScope = (BlockScope) ((Block) NodeFinder.find(Block.class, tree)).scope();
    LocalVariable temp = methodScope.ownVariableNamed("temp").get();
    LocalVariable arg2 = blockScope.ownVariableNamed("arg2").get();    
    MessageSendNoReceiver tempRef = NodeFinder.findLocalVarReference("temp", tree);
    MessageSendNoReceiver arg2ref = NodeFinder.findLocalVarReference("arg2", tree);

    assertFalse(varOf(tempRef).isLocal());
    assertTrue(varOf(arg2ref).isLocal());
    
    assertArrayEquals(new String[]{"temp"}, methodScope.ownVariableNames().toArray());
    assertEquals(2, temp.index());

    LocalVariable copiedTemp = blockScope.copiedVariableNamed("temp").get();
    assertArrayEquals(new String[]{"arg2"}, blockScope.ownVariableNames().toArray());
    assertArrayEquals(new String[]{"temp"}, blockScope.copiedVariableNames().toArray());
    assertEquals(1, copiedTemp.index());
    assertEquals(3, arg2.index());

    assertTrue(temp.isBoxed());
    assertTrue(copiedTemp.isBoxed());
    assertFalse(arg2.isBoxed());
  }
  
  @Test
  public void testCopiedVarsWithInterveningScope() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: arg1 = ( | temp |"
    + "  ^[[:arg2 | arg1 + temp + arg2] value: 3]"
    + "     value)"
    + ")");
    CodeScope methodScope = ((Method) NodeFinder.find(Method.class, tree)).scope();
    Block outerBlock = (Block) NodeFinder.find(Block.class, tree);
    Block innerBlock = (Block) NodeFinder.find(Block.class, outerBlock.body().get(0));
    BlockScope outerBlockScope = (BlockScope) outerBlock.scope();
    BlockScope innerBlockScope = (BlockScope) innerBlock.scope();
    
    LocalVariable arg1 = methodScope.ownVariableNamed("arg1").get();
    LocalVariable temp = methodScope.ownVariableNamed("temp").get();
    LocalVariable arg2 = innerBlockScope.ownVariableNamed("arg2").get();    
    MessageSendNoReceiver arg1Ref = NodeFinder.findLocalVarReference("arg1", tree);
    MessageSendNoReceiver tempRef = NodeFinder.findLocalVarReference("temp", tree);
    MessageSendNoReceiver arg2ref = NodeFinder.findLocalVarReference("arg2", tree);

    assertFalse(varOf(arg1Ref).isLocal());
    assertFalse(varOf(tempRef).isLocal());
    assertTrue(varOf(arg2ref).isLocal());
    
    assertArrayEquals(new String[]{"arg1", "temp"}, methodScope.ownVariableNames().toArray());
    assertArrayEquals(new String[0], outerBlockScope.ownVariableNames().toArray());
    assertArrayEquals(new String[]{"arg2"}, innerBlockScope.ownVariableNames().toArray());

    assertArrayEquals(new String[]{"arg1", "temp"}, outerBlockScope.copiedVariableNames().toArray());
    assertArrayEquals(new String[]{"arg1", "temp"}, innerBlockScope.copiedVariableNames().toArray());    

    LocalVariable outerCopiedArg1 = outerBlockScope.copiedVariableNamed("arg1").get();
    LocalVariable outerCopiedTemp = outerBlockScope.copiedVariableNamed("temp").get();
    LocalVariable innerCopiedArg1 = innerBlockScope.copiedVariableNamed("arg1").get();
    LocalVariable innerCopiedTemp = innerBlockScope.copiedVariableNamed("temp").get();

    assertEquals(2, arg1.index());
    assertEquals(4, temp.index());
    assertEquals(1, outerCopiedArg1.index());
    assertEquals(3, outerCopiedTemp.index());
    assertEquals(1, innerCopiedArg1.index());
    assertEquals(3, innerCopiedTemp.index());
    assertEquals(5, arg2.index());

    assertFalse(arg1.isBoxed());
    assertTrue(temp.isBoxed());
    assertFalse(outerCopiedArg1.isBoxed());
    assertTrue(outerCopiedTemp.isBoxed());
    assertFalse(innerCopiedArg1.isBoxed());
    assertTrue(innerCopiedTemp.isBoxed());
    assertFalse(arg2.isBoxed());
  }

  private VariableReference varOf(MessageSendNoReceiver node) {
    return (VariableReference) node.rewritten();
  }

}
