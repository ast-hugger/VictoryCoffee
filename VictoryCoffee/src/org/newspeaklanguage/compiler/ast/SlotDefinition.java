package org.newspeaklanguage.compiler.ast;

/**
 * A definition of a class slot or a method or block temporary variable.
 * 
 * @author vassili
 *
 */
public class SlotDefinition extends AstNode {
  
  private final String name;
  private final boolean mutable;
  private final AstNode initializer;
  
  SlotDefinition(String name, AstNode initializer, boolean mutable) {
    this.name = name;
    this.initializer = initializer;
    this.mutable = mutable;
  }
  
  public String name() { return name; }
  public AstNode initializer() { return initializer; }
  public boolean mutable() { return mutable; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitSlotDefinition(this);
  }

}
