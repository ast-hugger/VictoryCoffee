package org.newspeaklanguage.compiler.semantics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;

public class BlockTypeAnalysisTests {

  @Test
  public void testCleanReferenceRecognition() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: foo = ("
    + "  ^[:bar :baz | | temp |"
    + "   self x: foo." // not clean because  foo is in the outer method
    + "   [:quux | bar] value." // not clean because bar is in the outer block  
    + "   baz + temp] " // both are clean because both are in the same scope
    + "     value: 3 value: 4. )"
    + ")");
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    MessageSendNoReceiver bazRef = NodeFinder.findLocalVarReference("baz", tree);
    MessageSendNoReceiver tempRef = NodeFinder.findLocalVarReference("temp", tree);
    assertFalse(fooRef.meaning().asLexicalVarReference().isClean());
    assertFalse(barRef.meaning().asLexicalVarReference().isClean());
    assertTrue(bazRef.meaning().asLexicalVarReference().isClean());
    assertTrue(tempRef.meaning().asLexicalVarReference().isClean());
  }

  @Test
  public void testCopiableReferenceRecognition() {
    AstNode tree = Compiler.parseAndAnalyze("class Test = ()"
    + "('testing'"
    + "test: foo and: boo = ( | temp1 |"
    + "  ^[:bar :baz | | temp2 |"
    + "   self x: foo." // copiable because foo is one level up and immutable
    + "   self x: temp1." // not copiable because temp1 is mutable
    + "   [:quux | bar] value." // copiable because bar is one level up and immutable  
    + "   [:frob | boo] value." // still copiable though boo is two levels up 
    + "   ^baz"  // baz is clean, not copiable
    + "]  value: 3 value: 4. )"
    + ")");
    MessageSendNoReceiver fooRef = NodeFinder.findLocalVarReference("foo", tree);
    MessageSendNoReceiver temp1Ref = NodeFinder.findLocalVarReference("temp1", tree);
    MessageSendNoReceiver barRef = NodeFinder.findLocalVarReference("bar", tree);
    MessageSendNoReceiver booRef = NodeFinder.findLocalVarReference("boo", tree);
    assertTrue(fooRef.meaning().asLexicalVarReference().isCopiable());
    assertFalse(temp1Ref.meaning().asLexicalVarReference().isCopiable());
    assertTrue(barRef.meaning().asLexicalVarReference().isCopiable());
    assertTrue(booRef.meaning().asLexicalVarReference().isCopiable());
  }

}
