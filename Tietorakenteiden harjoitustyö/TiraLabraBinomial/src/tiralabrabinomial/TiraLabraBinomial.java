/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrabinomial;

/**
 *
 * @author Wampie
 */
public class TiraLabraBinomial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BinomialHeap test = new BinomialHeap();
        for (int i = 0; i < 3; i++) {
            test.insert(i);            
        }
        System.out.println("Testataan että keko rullaa");
        System.out.println(test);
        
        BinomialHeap unionTest = new BinomialHeap();
        unionTest.insert(6);
        unionTest.insert(7);
        unionTest.insert(8);
        
        unionTest = unionTest.union(test);
        
        System.out.println("Testataan Unionia:");
        System.out.println(unionTest);
        
        unionTest.extractMin();
        System.out.println("Testataan pienimmän poistoa");
        System.out.println(unionTest);
        
        
        
        
    }
}
