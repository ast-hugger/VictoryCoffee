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

package org.newspeaklanguage.apps;

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.codegen.CpsMonkey;
import org.newspeaklanguage.compiler.codegen.CpsSlice;

import java.util.List;

/**
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class TestCpsMonkey {

//  private static final String source = "fib: n = ((fib: n - 1) + (fib: n - 2))";
  private static final String source = "fib: n = (n < 2 ifTrue: [1] ifFalse: [(fib: n - 1) + (fib: n - 2)])";
//  private static final String source = "foo = (bar: self foo and: self bar)";
//  private static final String source = "foo: x = (self foo + self bar * x)";

  public static void main(String[] args) {
    ClassDecl tree = (ClassDecl) Compiler.parseAndAnalyze(fullClassSource(source));
    AstNode expr = tree.categories().get(0).methods().get(0).body().get(0);
    // set a breakpoint at the following line
    List<CpsSlice> slices = CpsMonkey.translate(expr);
    System.out.println(CpsMonkey.printSlices(slices));
  }

  private static String fullClassSource(String methodSource) {
    return "class Test = () (" +
        "'testing'" +
        methodSource + ")";
  }
}
