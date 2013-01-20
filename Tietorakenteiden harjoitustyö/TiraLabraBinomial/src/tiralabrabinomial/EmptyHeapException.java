/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrabinomial;

/**
 *
 * @author Wampie
 */
public class EmptyHeapException extends Exception {
    
    public EmptyHeapException(String msg){ 
      super(msg); 
    } 

    public EmptyHeapException(String msg, Throwable t){ 
      super(msg,t); 
    } 
} 
