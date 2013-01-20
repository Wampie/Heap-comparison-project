/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrateht1;

/**
 *
 * @author Wampie
 */
public class Stack {
    
    StackNode top = null;
    
    void push(int value) {
        if (top == null) {
            StackNode a = new StackNode(null, value);
            top = a;
        } else {
            StackNode a = new StackNode(top, value);
            top = a;
        }
    }
    
    int pop() {
        if (top == null) {
            return -1;
        } else {
            int returnable = top.getValue();
            top = top.next;
            return returnable;
        }
    }
    
    class StackNode {
        int value;
        StackNode next;
        
        public StackNode(StackNode next, int value) {
            this.next = next;
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
        public StackNode getNext() {
            return next;
        }
    }
}
