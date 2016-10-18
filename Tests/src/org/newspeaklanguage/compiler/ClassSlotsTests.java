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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.newspeaklanguage.testsupport.Example;

public class ClassSlotsTests {
  
  @Test
  public void testSlotReadAndWrite() {
    Example test = Example.fullClass(
        "class Test = (| foo |)"
        + "('testing'"
        + "test = ("
        + "  foo: 'hello'."
        + "  ^foo"
        + ")"
        + ")");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testSlotSetterReturnValue() {
    Example test = Example.fullClass(
        "class Test = (| foo bar |)"
        + "('testing'"
        + "test = ("
        + "  bar: (foo: 'hello')."
        + "  ^bar"
        + ")"
        + ")");
    assertEquals(test.module(), test.result());
  }

  @Test
  public void testSetterSend() {
    Example test = Example.fullClass(
        "class Test = (| foo |)"
        + "('testing'"
        + "test = ("
        + "  foo:: 'hello'."
        + "  ^foo"
        + ")"
        + ")");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testSetterSendReturnValue() {
    Example test = Example.fullClass(
        "class Test = (| foo bar |)"
        + "('testing'"
        + "test = ("
        + "  bar: (foo:: 'hello')."
        + "  ^bar"
        + ")"
        + ")");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testInnerAndOuterAccess() {
    Example test = Example.fullClass(
        "class Test = ( | outerSlot | ) ("
        + "class Nested = ( | innerSlot | ) ("
        + "  'accessing'"
        + "  foo = ("
        + "     innerSlot:: 'inner'."
        + "     outerSlot:: 'outer'. "
        + "     ^innerSlot + outerSlot)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(test.isResult("innerouter"));
  }

  @Test
  public void testInnerAndOuterShadowedAccess() {
    Example test = Example.fullClass(
        "class Test = ( | slot | ) ("
        + "class Nested = ( | slot | ) ("
        + "  'accessing'"
        + "  foo = ("
        + "     slot:: 'inner'."
        + "     outer Test slot: 'outer'. "
        + "     ^slot + outer Test slot)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(test.isResult("innerouter"));
  }


}
