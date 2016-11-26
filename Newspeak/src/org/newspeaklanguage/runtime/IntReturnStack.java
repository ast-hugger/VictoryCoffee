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

/**
 * A stack capable of storing primitive int values without wrapping them,
 * in a thread-local storage. Used by the method return mechanism when a
 * method needs to return a primitive int. In that case the int is pushed
 * on the current thread's stack using {@link #push(int)} while the method
 * returns {@code NsObject.Undefined}, which the caller will recognize and
 * know to get the actual returned value using {@link #pop()}.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class IntReturnStack {

  public static final int STACK_SIZE = 10000;
  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(IntReturnStack.class);
  public static final String PUSH_DESCRIPTOR = Descriptor.ofMethod(void.class, int.class);
  public static final String POP_DESCRIPTOR = Descriptor.ofMethod(int.class);

  /**
   * The thread-local stack represented as an int[]. The first element of
   * the array is used the stack pointer, so we don't have to maintain
   * a separate thread-local holder with its primitive int value.
   */
  private static final ThreadLocal<int[]> stacks = new ThreadLocal<int[]>() {
    @Override
    protected int[] initialValue() {
      return new int[STACK_SIZE + 1];
    }
  };

  @SuppressWarnings("unused") // called by generated code
  public static void push(int value) {
    int[] stack = stacks.get();
    stack[++stack[0]] = value;
  }

  @SuppressWarnings("unused") // called by generated code
  public static int pop() {
    int[] stack = stacks.get();
    return stack[stack[0]--];
  }

  @SuppressWarnings("unused") // called by generated code
  public static int peek() {
    int[] stack = stacks.get();
    return stack[stack[0]];
  }

  @SuppressWarnings("unused") // called by generated code
  public static boolean isEmpty() {
    return (stacks.get())[0] == 0;
  }
}
