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

import org.junit.Test;
import org.newspeaklanguage.testsupport.Example;

public class ClassNestingTests {
  @Test
  public void testNestedClass() {
    Example test = Example.fullClass(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^'foo')"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(test.isResult("foo"));
  }

  @Test
  public void testHereSends() {
    Example test = Example.fullClass(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^here + there)"
        + "  here = (^'inner')"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + "there = (^'outer')"
        + ")");
    assertTrue(test.isResult("innerouter"));
  }

  @Test
  public void testHereSends2() {
    Example test = Example.fullClass(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^printString + self printString)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + "printString = (^'The Boss')"
        + ")");
    assertTrue(test.isResult("The Boss<a Nested>"));
  }

  @Test
  public void testOuterSend() {
    Example test = Example.fullClass(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^outer Test printString)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + "printString = (^'The Boss')"
        + ")");
    assertTrue(test.isResult("The Boss"));
  }

}
