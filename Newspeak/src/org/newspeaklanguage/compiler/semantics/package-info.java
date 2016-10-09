/**
 * Semantic analysis is performed by two AST visitors: {@link Stage1Analyzer}
 * and {@link Stage2Analyzer}. They annotate the AST tree with additional
 * information which is then used by the code generator.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
package org.newspeaklanguage.compiler.semantics;