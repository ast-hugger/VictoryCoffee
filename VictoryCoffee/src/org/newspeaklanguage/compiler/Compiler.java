package org.newspeaklanguage.compiler;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.newspeaklanguage.compiler.ast.AstBuilder;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.codegen.ClassGenerator;
import org.newspeaklanguage.compiler.parser.NewspeakLexer;
import org.newspeaklanguage.compiler.parser.NewspeakParser;
import org.newspeaklanguage.compiler.parser.NewspeakParser.SourceUnitContext;
import org.newspeaklanguage.compiler.semantics.NameAnalyzer;
import org.newspeaklanguage.compiler.semantics.ScopeBuilder;

public class Compiler {

  public static class Result {
    public final String className;
    public final byte[] bytecode;
    
    private Result(String className, byte[] bytecode) {
      this.className = className;
      this.bytecode = bytecode;
    }
  }
  
  public static Result compile(String source) {
    Compiler compiler = new Compiler(source);
    compiler.compile();
    return new Result(compiler.className(), compiler.bytecode());
  }
  
  /*
   * Instance side 
   */
  
  private final String source;
  private ClassDecl ast;
  // Compilation results
  private String className;
  private byte[] bytecode;
  
  private Compiler(String source) {
    this.source = source;
  }

  public String source() { return source; }
  public ClassDecl ast() { return ast; }
  public String className() { return className; }
  public byte[] bytecode() { return bytecode; }
  
  public void compile() {
    ast = (ClassDecl) parse(source);
    className = ast.name();
    analyze(ast);
    bytecode = generate(ast);
  }
  
  private AstNode parse(String sourceCode) {
    ANTLRInputStream in = new ANTLRInputStream(sourceCode);
    NewspeakLexer lexer = new NewspeakLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    NewspeakParser parser = new NewspeakParser(tokens);
    SourceUnitContext sourceContext = parser.sourceUnit();
    AstBuilder builder = new AstBuilder();
    return builder.visit(sourceContext);
  }
  
  private void analyze(ClassDecl classDecl) {
    ScopeBuilder.buildScopeForClass(ast);
    NameAnalyzer.resolveNames(ast);
  }
  
  private byte[] generate(ClassDecl classDecl) {
    return ClassGenerator.generate(classDecl);
  }
  
}
