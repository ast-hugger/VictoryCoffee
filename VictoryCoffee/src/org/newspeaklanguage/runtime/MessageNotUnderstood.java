package org.newspeaklanguage.runtime;


public class MessageNotUnderstood extends Exception {

  private final Object receiver;
  private final String selector;
  private final Object[] arguments;
  
  public MessageNotUnderstood(Object receiver, String selector, Object[] arguments) {
    this.receiver = receiver;
    this.selector = selector;
    this.arguments = arguments;
  }
  
  @Override
  public String toString() {
    return "Message not understood: " + selector;
  }
}
