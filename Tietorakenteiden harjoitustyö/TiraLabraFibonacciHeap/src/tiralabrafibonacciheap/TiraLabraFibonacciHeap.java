/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrafibonacciheap;

/**
 *
 * @author Wampie
 */
public class TiraLabraFibonacciHeap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FibonacciHeap test = new FibonacciHeap();
        FibonacciHeap test2 = new FibonacciHeap();
        test.insert(1);
        test.insert(2);
        test.insert(3);
        
        System.out.println("Testataan yksinkertaista kekolisäämistä:");
        System.out.println(test);
        
        test2.insert(4);
        test2.insert(5);
        test2.insert(6);
        FibonacciHeap unionTest;
        unionTest = test.union(test, test2);
        
        System.out.println("Testataan unionia");
        System.out.println(unionTest);
        
        unionTest.extractMin();
        System.out.println("Testataan pienimmän alkion poistoa");
        System.out.println(unionTest);
        
        System.out.println("Poistetaan alin lapsi");

        unionTest.delete(unionTest.min().child.left.child);
        
        System.out.println(unionTest);
        
        
    }
}
