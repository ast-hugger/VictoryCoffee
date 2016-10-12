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