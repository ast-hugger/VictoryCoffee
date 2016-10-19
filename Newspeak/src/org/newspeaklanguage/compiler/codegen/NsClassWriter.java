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

import org.newspeaklanguage.compiler.Descriptor;
import org.objectweb.asm.ClassWriter;

/**
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class NsClassWriter extends ClassWriter {

  public NsClassWriter(int flags) {
    super(flags);
  }

  @Override
  protected String getCommonSuperClass(String name1, String name2) {
    return Descriptor.OBJECT_INTERNAL_CLASS_NAME; // keep it simple, stupid
  }
}
