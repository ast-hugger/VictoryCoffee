package org.newspeaklanguage.runtime;


public class MessageNotUnderstood extends Exception {

  private static final long serialVersionUID = 53410736054856682L;
  
  private final Object receiver;
  private final String selector;
  private final Object[] arguments;
  
  public MessageNotUnderstood(Object receiver, String selector, Object[] arguments) {
    this.receiver = receiver;
    this.selector = selector;
    this.arguments = arguments;
  }
  
  public Object receiver() { return receiver; }
  public String selector() { return selector; }
  public Object[] arguments() { return arguments; }
  
  @Override
  public String toString() {
    return "Message not understood: " + selector;
  }
}
