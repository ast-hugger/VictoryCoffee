package org.newspeaklanguage.apps;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.newspeaklanguage.compiler.ast.AstBuilder;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.parser.NewspeakLexer;
import org.newspeaklanguage.compiler.parser.NewspeakParser;
import org.newspeaklanguage.compiler.parser.NewspeakParser.SourceUnitContext;

public class TryParser {

  private static final String source =
"class Foo name: x = NsObject name: x (\n"
+ "  | foo = \'foo\'. \n"
+ "    bar | \n"
+ ")\n"
+ "('accesing'\n"
+ "foo = (\n"
+ ")\n"
+ ")";
  
  public static void main(String[] args) {
    ANTLRInputStream in = new ANTLRInputStream(source);
    NewspeakLexer lexer = new NewspeakLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    NewspeakParser parser = new NewspeakParser(tokens);
    SourceUnitContext sourceContext = parser.sourceUnit();
    AstBuilder builder = new AstBuilder();
    AstNode ast = builder.visit(sourceContext);
    System.out.println(ast);
    System.out.println(sourceContext.toStringTree());
  }

}
