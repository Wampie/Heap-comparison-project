/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrateht1;

/**
 *
 * @author Wampie
 */
public class TiraLabraTeht1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("MOIIIII");
        BinHeap letsTest = new BinHeap();
        letsTest.heapInsert(5);
        letsTest.heapInsert(2);
        letsTest.heapInsert(4);
        letsTest.heapInsert(1);
        letsTest.heapInsert(9);
        letsTest.heapInsert(7);
        letsTest.heapInsert(6);
        letsTest.heapInsert(8);
        letsTest.print(letsTest.root, 0);
        
        letsTest.deleteMin();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        letsTest.print(letsTest.root, 0);
    }
}
