package org.newspeaklanguage.compiler.ast;

/**
 * A class slot or a method or block temporary variable.
 * 
 * @author vassili
 *
 */
public class Slot extends AstNode {
  
  private final String name;
  private final boolean mutable;
  private final AstNode initializer;
  
  Slot(String name, AstNode initializer, boolean mutable) {
    this.name = name;
    this.initializer = initializer;
    this.mutable = mutable;
  }
  
  public String name() { return name; }
  public AstNode initializer() { return initializer; }
  public boolean mutable() { return mutable; }

}
