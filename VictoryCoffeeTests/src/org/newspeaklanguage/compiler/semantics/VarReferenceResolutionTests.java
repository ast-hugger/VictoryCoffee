package org.newspeaklanguage.compiler.semantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.NameDefinition;
import org.newspeaklanguage.compiler.semantics.NameMeaning.LocalVarReference;
import org.newspeaklanguage.compiler.semantics.NameMeaning.SendToEnclosingObject;

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
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    NameDefinition barDef = NodeFinder.findLocalVarDefinition("bar", tree);
    NameDefinition bazDef = NodeFinder.findLocalVarDefinition("baz", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    MessageSendNoReceiver bazRef = NodeFinder.findLocalVarReference("baz", tree);
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertEquals(barDef, barRef.lexicalDefinition().astNode());
    assertEquals(bazDef, bazRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isSelfSend());
    assertTrue(barRef.meaning().isSelfSend());
    assertTrue(bazRef.meaning().isSelfSend());
  }

  @Test
  public void testOuterClassSlotReferences() {
    ClassDecl tree = (ClassDecl) Compiler.parseAndAnalyze("class Test = ( | foo bar baz | )"
    + "(class Inner = ()"
    + "  ('example'"
    + "  test = ("
    + "    foo: 3."
    + "    bar:: 4."
    + "    ^baz)"
    + "  )"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    NameDefinition barDef = NodeFinder.findLocalVarDefinition("bar", tree);
    NameDefinition bazDef = NodeFinder.findLocalVarDefinition("baz", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    MessageSendNoReceiver bazRef = NodeFinder.findLocalVarReference("baz", tree);
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertEquals(barDef, barRef.lexicalDefinition().astNode());
    assertEquals(bazDef, bazRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isSendToEnclosingObject());
    assertTrue(barRef.meaning().isSendToEnclosingObject());
    assertTrue(bazRef.meaning().isSendToEnclosingObject());
    SendToEnclosingObject fooMeaning = (SendToEnclosingObject) fooRef.meaning();
    SendToEnclosingObject barMeaning = (SendToEnclosingObject) barRef.meaning();
    SendToEnclosingObject bazMeaning = (SendToEnclosingObject) bazRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(barDef, barMeaning.definition().astNode());
    assertEquals(bazDef, bazMeaning.definition().astNode());
    assertEquals(tree.scope(), fooMeaning.definition().scope());
    assertEquals(tree.scope(), barMeaning.definition().scope());
    assertEquals(tree.scope(), bazMeaning.definition().scope());
  }

  @Test
  public void testMethodArgReferences() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: foo and: bar  = ("
    + "  ^foo + bar)"
    + ")");
    NameDefinition fooDef = NodeFinder.findLocalVarDefinition("foo", tree);
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isLocalVarReference());
    LocalVarReference fooMeaning = (LocalVarReference) fooRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(1, ((CodeScopeEntry) fooMeaning.definition()).index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(2, ((CodeScopeEntry) barRef.lexicalDefinition()).index());    
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
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isLocalVarReference());
    LocalVarReference fooMeaning = (LocalVarReference) fooRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(2, ((CodeScopeEntry) fooMeaning.definition()).index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, ((CodeScopeEntry) barRef.lexicalDefinition()).index());    
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
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isLocalVarReference());
    LocalVarReference fooMeaning = (LocalVarReference) fooRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(1, ((CodeScopeEntry) fooMeaning.definition()).index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(2, ((CodeScopeEntry) barRef.lexicalDefinition()).index());    
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
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isLocalVarReference());
    LocalVarReference fooMeaning = (LocalVarReference) fooRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(2, ((CodeScopeEntry) fooMeaning.definition()).index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, ((CodeScopeEntry) barRef.lexicalDefinition()).index());    
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
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isLocalVarReference());
    LocalVarReference fooMeaning = (LocalVarReference) fooRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(1, ((CodeScopeEntry) fooMeaning.definition()).index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(2, ((CodeScopeEntry) barRef.lexicalDefinition()).index());    
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
    assertEquals(fooDef, fooRef.lexicalDefinition().astNode());
    assertTrue(fooRef.meaning().isLocalVarReference());
    LocalVarReference fooMeaning = (LocalVarReference) fooRef.meaning();
    assertEquals(fooDef, fooMeaning.definition().astNode());
    assertEquals(2, ((CodeScopeEntry) fooMeaning.definition()).index());
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    assertEquals(3, ((CodeScopeEntry) barRef.lexicalDefinition()).index());    
  }
  
}
