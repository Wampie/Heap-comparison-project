/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrateht1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabrateht1.BinHeap.HeapNode;

/**
 *
 * @author Wampie
 */
public class BinHeapTest {

    public BinHeapTest() {
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
     * Test of heapInsert method, of class BinHeap.
     */
    @Test
    public void testHeapInsertToEmptyHeap() {
        BinHeap instance = new BinHeap();
        instance.heapInsert(2);
        assertEquals("Humm printtaan", 2, instance.root.value);
    }

    /**
     * Test of deleteMin method, of class BinHeap.
     */
    @Test
    public void testDeleteMin() {
        System.out.println("Before delete");
        BinHeap instance = new BinHeap();
        instance.heapInsert(5);
        instance.heapInsert(2);
        instance.heapInsert(4);
        instance.heapInsert(1);
        instance.print(instance.root, 0);
        instance.deleteMin();
        System.out.println("After delete");
        instance.print(instance.root, 0);
        assertEquals("Humm printtaan", 2, instance.root.value);
    }

    /**
     * Test of findNode method, of class BinHeap.
     */
    @Test
    public void testBubbleUp() {
        BinHeap test = new BinHeap();
        test.heapInsert(6);
        test.heapInsert(5);
        test.heapInsert(3);
        test.heapInsert(2);
        test.heapInsert(1);

        assertEquals("BubbleUp toimii, jos viimeisenä lisätty pienin arvo löytää tiensä juureksi", 1, test.root.value);
    }

    @Test
    public void testHeapify() {
        BinHeap test = new BinHeap();
        test.heapInsert(1);
        test.heapInsert(5);
        test.heapInsert(3);
        test.heapInsert(2);

        test.root.value = 6;
        test.heapify(test.root);

        assertEquals("Heapifyn täytyy toimia", 2, test.root.value);
    }
}
