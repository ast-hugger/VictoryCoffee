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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * A definition of a Newspeak class.
 * 
 * <p>A Newspeak class is implemented by a triplet of entities. Given a class
 * source code, the compiler produces two of them: a {@link ClassDefinition}
 * instance holding onto a subclass of {@link NsObject}. The subclass is what's
 * instantiated to produce an instance of the Newspeak class. There is always
 * one ClassDefinition and one subclass of NsObject per Newspeak class. The third
 * entity comes into play when Newspeak code retrieves a Newspeak class from a
 * particular containing object. The metaobject received is a {@link ObjectFactory}. It
 * holds onto the corresponding ClassDefinition and the enclosing instance of
 * its parent Newspeak class. Thus, there can be multiple Class instances
 * representing a particular Newspeak class, if that class has been referenced
 * in the context of different enclosing objects.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class ClassDefinition {
  
  public static final String INTERNAL_CLASS_NAME = ClassDefinition.class.getName().replace('.', '/');
  public static final String TYPE_DESCRIPTOR = "L" + INTERNAL_CLASS_NAME + ";";
  public static final String CONSTRUCTOR_DESCRIPTOR = "(Ljava/lang/String;Ljava/lang/Class;)V";

  public static ClassDefinition create(String name, java.lang.Class<? extends NsObject> implClass) {
    return new ClassDefinition(name, implClass);
  }
  
  /*
   * Instance side
   */
  
  private final String name;
  private final java.lang.Class<? extends NsObject> implementation;
  private final MethodHandle constructor;
  
  public ClassDefinition(String name, java.lang.Class<? extends NsObject> implementation)
  {
    this.name = name;
    this.implementation = implementation;
    try {
      this.constructor = MethodHandles.lookup().findConstructor(
          implementation,
          MethodType.methodType(void.class, ObjectFactory.class));
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw new RuntimeError("Failure initializing a class definition");
    }
  }
  
  public String name() {
    return name;
  }
  
  public java.lang.Class<? extends NsObject> implementation() {
    return implementation;
  }
  
  public StandardObject makeInstance(ObjectFactory nsClass) throws Throwable {
    return (StandardObject) constructor.invoke(nsClass);
  }
  
}
