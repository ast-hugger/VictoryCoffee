package org.newspeaklanguage.runtime;


public class RuntimeError extends RuntimeException {

  private static final long serialVersionUID = -3383478822310803225L;
  
  public RuntimeError(String message) {
    super(message);
  }
  
  public RuntimeError(String message, Throwable cause) {
    super(message, cause);
  }

}
