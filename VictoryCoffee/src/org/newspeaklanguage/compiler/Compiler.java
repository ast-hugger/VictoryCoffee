package org.newspeaklanguage.compiler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.newspeaklanguage.compiler.ast.AstBuilder;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.codegen.ClassGenerator;
import org.newspeaklanguage.compiler.parser.NewspeakLexer;
import org.newspeaklanguage.compiler.parser.NewspeakParser;
import org.newspeaklanguage.compiler.parser.NewspeakParser.SourceUnitContext;
import org.newspeaklanguage.compiler.semantics.Stage1Analyzer;
import org.newspeaklanguage.compiler.semantics.Stage2Analyzer;

public class Compiler {

  public static class Result {
    private ClassDecl ast;
    private final byte[] bytecode;
    
    private Result(ClassDecl classNode, byte[] bytecode) {
      this.ast = classNode;
      this.bytecode = bytecode;
    }
    
    public String className() { return ast.name(); }
    public String implementationClassName() { return ast.implementationClassName(); }
    public ClassDecl ast() { return ast; }
    public byte[] bytecode() { return bytecode; }
  }
  
  public static List<Result> compile(String source) {
    Compiler compiler = new Compiler(source);
    compiler.compile();
    return compiler.results();
  }
  
  /*
   * Instance side 
   */
  
  private final String source;
  private ClassDecl ast;
  private final List<Result> results = new ArrayList<Result>();
  
  private Compiler(String source) {
    this.source = source;
  }

  public String source() { return source; }
  public ClassDecl ast() { return ast; }
  public List<Result> results() { return results; }
  
  public void compile() {
    ast = (ClassDecl) parse(source);
    analyze(ast);
    generate(ast);
  }
  
  private void generate(ClassDecl node) {
    generateSingleClass(node);
    node.nestedClasses().forEach(this::generate);
  }
  
  private void generateSingleClass(ClassDecl node) {
    byte[] bytecode = ClassGenerator.generate(node);
    results.add(new Result(node, bytecode));
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
    Stage1Analyzer.analyze(ast);
    Stage2Analyzer.analyze(ast);
  }
  
}
