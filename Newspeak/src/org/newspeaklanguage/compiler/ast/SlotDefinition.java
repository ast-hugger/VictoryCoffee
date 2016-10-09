package org.newspeaklanguage.compiler.ast;

/**
 * A definition of a class slot or a method or block temporary variable.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class SlotDefinition extends AstNode implements NameDefinition {

  private final String name;
  private final boolean isMutable;
  private final AstNode initializer;

  SlotDefinition(String name, AstNode initializer, boolean isMutable) {
    this.name = name;
    this.initializer = initializer;
    this.isMutable = isMutable;
  }

  @Override
  public String name() {
    return name;
  }

  public AstNode initializer() {
    return initializer;
  }

  @Override
  public boolean isMutable() {
    return isMutable;
  }

  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitSlotDefinition(this);
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + name + ")"
        + (isMutable ? "mutable" : "immutable");
  }

}
