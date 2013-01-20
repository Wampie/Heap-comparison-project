/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrabinomial;

import tiralabrabinomial.Node;
import tiralabrabinomial.BinomialHeap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wampie
 */
public class BinomialHeapTest {
    
    public BinomialHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of minimum method, of class BinomialHeap. Tests if minimum works with
     * a single element heap
     */
    @Test
    public void testMinimumOnOneNode() throws Exception {
        System.out.println("minimum");
        BinomialHeap instance = new BinomialHeap();
        instance.insert(1);
        assertEquals(1, instance.minimum().value);
    }
    /**
     * Test of minimum method, of class BinomialHeap. Tests if minimum works with
     * a single element heap
     */
    @Test
    public void testMinimumOnOneSeveral() throws Exception {
        System.out.println("minimum");
        BinomialHeap instance = new BinomialHeap();
        instance.insert(2);
        instance.insert(3);
        instance.insert(1);
        assertEquals(1, instance.minimum().value);
    }
    
    /**
     * Test of insert method, of class BinomialHeap. Tests insert on empty heap
     */
    @Test
    public void testInsertEmpty() throws Exception {
        System.out.println("insert");
        int value = 0;
        BinomialHeap instance = new BinomialHeap();
        instance.insert(value);
        assertEquals(0, instance.minimum().value);
    }
    /**
     * Test of insert method, of class BinomialHeap. Tests that all the elements inserted
     * are saved
     */
    @Test
    public void testInsertElements() {
        System.out.println("insert");
        BinomialHeap instance = new BinomialHeap();
        for (int i = 0; i < 6; i++) {
            instance.insert(i);
        }
        System.out.println(instance);
        assertEquals("4 5 0 2 3 1", instance.testHeap().trim());
    }

    /**
     * Test of union method, of class BinomialHeap. Testing that the union works
     * properly
     */
    @Test
    public void testUnion() {
        System.out.println("union");
        BinomialHeap instance = new BinomialHeap();
        for (int i = 0; i < 6; i++) {
            instance.insert(i);
        }
        
        BinomialHeap unionTest = new BinomialHeap();
        unionTest.insert(6);
        unionTest.insert(7);
        unionTest.insert(8);
        
        unionTest = unionTest.union(instance);
        System.out.println(unionTest);
        
        assertEquals("8 0 4 6 7 5 2 3 1", unionTest.testHeap().trim());
    }
    /**
     * Tests that extractMin returns the minimum
     * @throws Exception 
     */
    @Test
    public void testExtractMinReturn() throws Exception {
        BinomialHeap instance = new BinomialHeap();
        for (int i = 0; i < 6; i++) {
            instance.insert(i);
        }
        assertEquals(0, instance.extractMin());
    }

    /**
     * Test of extractMin method, of class BinomialHeap. Tests that extract minimum
     * moves the pointer to minimum to next smallest value
     */
    @Test
    public void testExtractMinAfter() throws Exception {
        System.out.println("extractMin");
        BinomialHeap instance = new BinomialHeap();
        for (int i = 0; i < 6; i++) {
            instance.insert(i);
        }
        instance.extractMin();
        System.out.println(instance);
        assertEquals(1, instance.minimum().value);
    }

    /**
     * Test of decreaseKey method, of class BinomialHeap. Tests that decrease key
     * decreases key and sets it as minimum if needed
     */
    @Test
    public void testDecreaseKey() throws Exception {
        BinomialHeap instance = new BinomialHeap();
        instance.insert(1);
        instance.insert(2);
        instance.insert(3);
        
        instance.decreaseKey(instance.minimum().child, 0);
        
        assertEquals(0, instance.minimum().value);
    }

    /**
     * Test of bubbleUp method, of class BinomialHeap. Testing if bubbleUp works
     * accordingly
     */
    @Test
    public void testBubbleUp() throws Exception {
        BinomialHeap instance = new BinomialHeap();
        instance.insert(0);
        instance.insert(1);
        instance.insert(2);
        instance.minimum().child.value = -1;
        instance.bubbleUp(instance.minimum().child);
        assertEquals(-1, instance.minimum().value);
    }

    /**
     * Test of delete method, of class BinomialHeap. Tests that delete really deletes 
     * a given node
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("Before delete:");
        BinomialHeap instance = new BinomialHeap();
        instance.insert(0);
        instance.insert(1);
        instance.insert(2);
        System.out.println(instance);
        instance.delete(instance.minimum().child);
        System.out.println("After Delete:");
        System.out.println(instance);
        assertEquals("0 2", instance.testHeap().trim());
    }
}
