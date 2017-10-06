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

package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.testsupport.Example;

public class MethodTests {
  
  @Test
  public void testEmptyMethodReturnsSelf() {
    Example test = Example.testBody("");
    assertEquals(test.module(), test.result());
  }

  @Test
  public void testReceiverImplicitlyReturned() {
    Example test = Example.testBody("nil");
    assertEquals(test.module(), test.result());
  }

  @Test
  public void testReturnNil() {
    Example test = Example.testBody("^nil");
    assertEquals(null, test.result());
  }

  @Test
  public void testExplicitReturnSelf() {
    Example test = Example.testBody("^self");
    assertEquals("Test", test.resultClassName());
  }

  @Test
  public void testReturnTrue() {
    Example test = Example.testBody("^true");
    assertEquals(Builtins.TRUE, test.result());
  }

  @Test
  public void testReturnFalse() {
    Example test = Example.testBody("^false");
    assertEquals(Builtins.FALSE, test.result());
  }
  
  @Test
  public void testReturnLiteralString() {
    Example test = Example.testBody("^'foobar'");
    assertTrue(test.isResult("foobar"));
  }

  @Test
  public void testSequence() {
    Example test = Example.testMethod(
            "test = ("
            + "  'foo'."
            + "  'bar'."
            + "   ^'baz')");
    assertTrue(test.isResult("baz"));
  }
  
  @Test
  public void testTemps() {
    // FIXME the CPS code generator produces crazy code for the first two assignments,
    // which works but doesn't have to be so involved.
    Example test = Example.testMethod(
        "test = ("
        + "  | foo bar |"
        + "  foo:: 'foo'."
        + "  bar: 'bar'."
        + "  ^foo + bar)");
    assertTrue(test.isResult("foobar"));
  }

}
