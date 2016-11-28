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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.MessageSendWithReceiver;
import org.newspeaklanguage.compiler.ast.NameDefinition;
import org.newspeaklanguage.compiler.ast.Self;

public class VarReferenceResolutionTests {

  @Test
  public void testClassSlotReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ( | foo bar baz | )"
    + "('testing'"
    + "test = ("
    + "  foo: 3."
    + "  bar:: 4."
    + "  ^baz)"
    + ")");
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    MessageSendNoReceiver bazRef = NodeFinder.findLocalVarReference("baz", tree);
    assertTrue(((MessageSendWithReceiver) fooRef.rewritten()).receiver() instanceof Self);
    assertTrue(((MessageSendWithReceiver) barRef.rewritten()).receiver() instanceof Self);
    assertTrue(((MessageSendWithReceiver) bazRef.rewritten()).receiver() instanceof Self);
  }

  // TODO reinstate
//  @Test
//  public void testOuterClassSlotReferences() {
//    ClassDecl tree = (ClassDecl) Compiler.parseAndAnalyze("class Test = ( | foo bar baz | )"
//    + "(class Inner = ()"
//    + "  ('example'"
//    + "  test = ("
//    + "    foo: 3."
//    + "    bar:: 4."
//    + "    ^baz)"
//    + "  )"
//    + ")");
//    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
//    NameDefinition barDef = NodeFinder.findLocalVarDefinition("bar", tree);
//    NameDefinition bazDef = NodeFinder.findLocalVarDefinition("baz", tree);
//    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
//    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
//    MessageSendNoReceiver bazRef = NodeFinder.findLocalVarReference("baz", tree);
//    assertEquals(fooDef, fooRef.meaning().lexicalDefinition().get().astNode());
//    assertEquals(barDef, barRef.meaning().lexicalDefinition().get().astNode());
//    assertEquals(bazDef, bazRef.meaning().lexicalDefinition().get().astNode());
//    assertTrue(fooRef.meaning().isSendToEnclosingObject());
//    assertTrue(barRef.meaning().isSendToEnclosingObject());
//    assertTrue(bazRef.meaning().isSendToEnclosingObject());
//    SendToEnclosingObject fooMeaning = (SendToEnclosingObject) fooRef.meaning();
//    SendToEnclosingObject barMeaning = (SendToEnclosingObject) barRef.meaning();
//    SendToEnclosingObject bazMeaning = (SendToEnclosingObject) bazRef.meaning();
//    assertEquals(fooDef, fooMeaning.definition().astNode());
//    assertEquals(barDef, barMeaning.definition().astNode());
//    assertEquals(bazDef, bazMeaning.definition().astNode());
//    assertEquals(tree.scope(), fooMeaning.definition().scope());
//    assertEquals(tree.scope(), barMeaning.definition().scope());
//    assertEquals(tree.scope(), bazMeaning.definition().scope());
//  }

  @Test
  public void testMethodArgReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: foo and: bar  = ("
    + "  ^foo + bar)"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, varOf(fooRef).definition().astNode());
    assertEquals(1, varOf(fooRef).localVariable().index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, varOf(barRef).localVariable().index());
  }

  @Test
  public void testMethodTempReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: quux = ("
    + "  | foo bar | "
    + "  ^foo + (bar: 5))"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, varOf(fooRef).definition().astNode());
    assertEquals(3, varOf(fooRef).localVariable().index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(5, varOf(barRef).localVariable().index());
  }

  @Test
  public void testBlockArgReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test = ("
    + "  ^[:foo :bar | foo + bar] value: 3 value: 4)"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, varOf(fooRef).definition().astNode());
    assertEquals(1, varOf(fooRef).localVariable().index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, varOf(barRef).localVariable().index());
  }

  @Test
  public void testBlockTempReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test = ("
    + "  ^[:quux | | foo bar | foo + bar] value: 3)"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, varOf(fooRef).definition().astNode());
    assertEquals(3, varOf(fooRef).localVariable().index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(5, varOf(barRef).localVariable().index());
  }

  @Test
  public void testNestedBlockArgReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test = ("
    + "  ^[:foo :bar | [foo + bar] value] value: 3 value: 4)"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, varOf(fooRef).definition().astNode());
    assertEquals(1, varOf(fooRef).localVariable().index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, varOf(barRef).localVariable().index());
  }


  @Test
  public void testNestedBlockTempReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test = ("
    + "  ^[:quux | | foo bar | [foo + bar] value] value: 3)"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, varOf(fooRef).definition().astNode());
    // foo and bar references are in an inner block, referencing an outer block
    // They are going to be copied locals of the inner block with indices 1 and 2
    assertEquals(1, varOf(fooRef).localVariable().index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, varOf(barRef).localVariable().index());
  }

  private VariableReference varOf(MessageSendNoReceiver node) {
    return (VariableReference) node.rewritten();
  }

}
