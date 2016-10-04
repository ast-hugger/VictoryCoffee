package org.newspeaklanguage.compiler.semantics;

/**
 * This is attached to a {@link MessageSendNoReceiver} which is also a name to
 * indicate the lexical meaning of the name. The name may be a variable
 * reference, a pseudo variable (nil, true, false, self, super, outer), a
 * lexical outer send, or a self send.
 * 
 * @author vassili
 *
 */
public abstract class NameMeaning {
  
  public abstract void accept(NameMeaningVisitor visitor);

}
