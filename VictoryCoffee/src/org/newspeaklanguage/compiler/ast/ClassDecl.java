package org.newspeaklanguage.compiler.ast;

import java.util.List;

public class ClassDecl extends AstNode {

  private final String name;
  private final MessagePattern initMessage;
  private final String superclassName;
  private final MessagePattern superInitMessage;
  private final List<Slot> slots;
  private final List<ClassDecl> nestedClasses;
  private final List<Category> categories;
  private final List<Category> classCategories;
  
  ClassDecl(String name, MessagePattern initMessage, String superclassName, MessagePattern superInitMessage,
      List<Slot> slots, List<ClassDecl> nestedClasses, List<Category> categories, List<Category> classCategories)
  {
    this.name = name;
    this.initMessage = initMessage;
    this.superclassName = superclassName;
    this.superInitMessage = superInitMessage;
    this.slots = slots;
    this.nestedClasses = nestedClasses;
    this.categories = categories;
    this.classCategories = classCategories;
  }
  
  public String name() { return name; }
  public MessagePattern initMessage() { return initMessage; }
  public String superclassName() { return superclassName; }
  public MessagePattern superInitMessage() { return superInitMessage; }
  public List<Slot> slots() { return slots; }
  public List<ClassDecl> nestedClasses() { return nestedClasses; }
  public List<Category> categories() { return categories; }
  public List<Category> classCategories() { return classCategories; }
  
}
