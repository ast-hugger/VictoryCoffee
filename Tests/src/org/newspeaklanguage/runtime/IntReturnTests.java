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

import org.junit.Test;
import org.newspeaklanguage.testsupport.Example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class IntReturnTests {

  @Test
  public void testReturnInt() {
    Example test = Example.testBody("^3");
    assertEquals(NsObject.UNDEFINED, test.result());
    assertFalse(IntReturnStack.isEmpty());
    assertEquals(3, IntReturnStack.pop());
    assertTrue(IntReturnStack.isEmpty());
  }

  @Test
  public void testReturnedIntManipulation() {
    Example test = Example.fullClass(
        "class Test = () ('testing'" +
            "test = (^self a + self b)" +
            "a = (^3)" +
            "b = (^4)" +
            ")"
    );
    assertEquals(NsObject.UNDEFINED, test.result());
    assertFalse(IntReturnStack.isEmpty());
    assertEquals(7, IntReturnStack.pop());
    assertTrue(IntReturnStack.isEmpty());
  }

}
