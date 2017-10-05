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

/**
 * Semantic analysis. Must be performed on an AST before code can be generated.
 * 
 * The analysis is performed by two AST visitors: {@link AnalyzerStage1}
 * and {@link AnalyzerStage2}. They annotate the AST tree with additional
 * information which is then used by the code generator.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
package org.newspeaklanguage.compiler.semantics;