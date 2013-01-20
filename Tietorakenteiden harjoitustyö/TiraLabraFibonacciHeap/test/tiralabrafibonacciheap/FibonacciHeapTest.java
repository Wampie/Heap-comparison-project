/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrafibonacciheap;

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
public class FibonacciHeapTest {
    
    public FibonacciHeapTest() {
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
     * Test of insert method, of class FibonacciHeap.
     */
    @Test
    public void testInsertEmpty() {
        int key = 0;
        FibonacciHeap instance = new FibonacciHeap();
        instance.insert(key);
        assertNotNull(instance.min());
    }

    /**
     * Test of min method, of class FibonacciHeap.
     */
    @Test
    public void testMin() {
        FibonacciHeap instance = new FibonacciHeap();
        instance.insert(2);
        FiboNode result = instance.min();
        assertEquals(2, result.key);
    }
    @Test
    public void testMinManyElem() {
        FibonacciHeap instance = new FibonacciHeap();
        instance.insert(2);
        instance.insert(3);
        instance.insert(5);
        instance.insert(4);
        instance.insert(1);
        FiboNode result = instance.min();
        assertEquals(1, result.key);
    }
    

    /**
     * Test of union method, of class FibonacciHeap.
     */
    @Test
    public void testUnion() {
        FibonacciHeap heap1 = new FibonacciHeap();
        heap1.insert(1);
        heap1.insert(2);
        heap1.insert(3);
        FibonacciHeap heap2 = new FibonacciHeap();
        heap2.insert(4);
        heap2.insert(5);
        heap2.insert(6);
        FibonacciHeap instance = new FibonacciHeap();
        FibonacciHeap result = instance.union(heap1, heap2);
        assertEquals("1 5 6 4 2 3", result.tester().trim());
    }

    /**
     * Test of extractMin method, of class FibonacciHeap. Check if min value updates correctly
     */
    @Test
    public void testExtractMinMinValue() {
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 0; i < 6; i++) {
            instance.insert(i);
        }
        FiboNode result = instance.extractMin();
        assertEquals(0, result.key);
    }
    /**
     * Tests that no elements are lost during extract
     */
    @Test 
    public void testExtractMinElements() {
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 0; i < 6; i++) {
            instance.insert(i);
        }
        FiboNode result = instance.extractMin();
        
        assertEquals(5, instance.getElem());
    }
    

    /**
     * Test of consolidate method, of class FibonacciHeap. Tests that the root
     * count appears correctly after consolidating
     */
    @Test
    public void testConsolidateRootCount() {
        System.out.println("consolidate");
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 1; i < 6; i++) {
            instance.insert(i);
        }
        instance.consolidate();
        assertEquals(2, instance.countRoots());
    }
    
    /**
     * Test of consolidate method, of class FibonacciHeap. Tests that after consolidating
     * the heap starts building its tree from the min node. And appears as seen in 
     * http://www.cse.yorku.ca/~aaw/Jason/FibonacciHeapAnimation.html
     */
    @Test
    public void testConsolidateChildren() {
        System.out.println("consolidate");
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 1; i < 6; i++) {
            instance.insert(i);
        }
        instance.consolidate();
        assertEquals(2, instance.min().degree);
    }
    /**
     * Testing that Consolidate wont lose any elements from the 
     */
    @Test
    public void testConsolidateElemCount() {
        System.out.println("consolidate");
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 1; i < 6; i++) {
            instance.insert(i);
        }
        instance.consolidate();
        assertEquals(5, instance.getElem());
    }
    /**
     * Test of countRoots method, of class FibonacciHeap.
     */
    @Test
    public void testCountRoots() {
        System.out.println("countRoots");
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 1; i < 6; i++) {
            instance.insert(i);
        }
        int result = instance.countRoots();
        assertEquals(5, result);
    }

    /**
     * Test of delete method, of class FibonacciHeap.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        FibonacciHeap instance = new FibonacciHeap();
        instance.insert(1);
        instance.insert(2);
        instance.delete(instance.min().left);
        assertEquals(instance.min().left, instance.min().right);
    }

    /**
     * Test of pisanoDelete method, of class FibonacciHeap. Test creates a fibo
     * heap, consolidates it and removes the lowest child out of heap
     */
    @Test
    public void testPisanoDelete() {
        System.out.println("pisanoDelete");
        FibonacciHeap instance = new FibonacciHeap();
        for (int i = 1; i < 6; i++) {
            instance.insert(i);
        }
        instance.consolidate();
        instance.pisanoDelete(instance.min().child.left.child);
        assertNull(instance.min().child.left.child);
    }

    /**
     * Test of decreaseKey method, of class FibonacciHeap. Tests that decreaseKey
     * decreases given key, and does not lose any elements.
     */
    @Test
    public void testDecreaseKey() {
        System.out.println("decreaseKey");
        
        int newKey = 0;
        FibonacciHeap instance = new FibonacciHeap();
        instance.insert(2);
        instance.insert(3);
        instance.insert(5);
        instance.decreaseKey(instance.min().left, 0);
        assertEquals("0 2 3 ", instance.tester());
    }

    /**
     * Test of toString method, of class FibonacciHeap.
     */
}
