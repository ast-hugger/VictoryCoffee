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

package org.newspeaklanguage.compiler.codegen;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * We often need to add a static field to the class being generated,
 * and initialize the field in a certain way. This is necessary for
 * things such as literals, blocks, or runtime bookkeeping. The
 * {@link ClassGenerator} keeps track of those static fields using
 * subtypes of this type.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public interface StaticFieldDefiner {

  /**
   * Produce the static field for the value using the supplied {@link ClassWriter}.
   */
  void generateField(ClassWriter classWriter);

  /**
   * Generate a sequence of instructions that will initialize the static field
   * to the desired value.
   */
  void generateClinitFragment(MethodVisitor methodWriter);

}
