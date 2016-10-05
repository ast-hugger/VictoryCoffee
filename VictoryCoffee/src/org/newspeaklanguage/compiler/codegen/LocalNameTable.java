package org.newspeaklanguage.compiler.codegen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.newspeaklanguage.compiler.ast.NamedNode;

public class LocalNameTable {

  private final List<NamedNode> nameDefinitions = new LinkedList<NamedNode>();
  private final Map<String, Integer> nameOffsets = new HashMap<String, Integer>();
  private boolean open = true;
  
  public void close() {
    int offset = 1;
    for (NamedNode node : nameDefinitions) {
      nameOffsets.put(node.name(), offset++);
    }
    open = false;
  }
  
  public void add(NamedNode node) {
    if (!open) {
      throw new IllegalStateException("the name table has already been populated");
    }
    nameDefinitions.add(node);
  }
  
  public int offsetOf(String name) {
    if (open) {
      throw new IllegalStateException("the name table is still being populated");
    }
    return nameOffsets.get(name);
  }
  
}
