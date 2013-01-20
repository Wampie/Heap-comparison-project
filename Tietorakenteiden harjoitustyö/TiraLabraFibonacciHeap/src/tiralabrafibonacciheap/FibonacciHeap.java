/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrafibonacciheap;

import java.util.*;

/**
 *
 * @author Wampie
 */
public class FibonacciHeap {
    // FiboNodes are stored in doubly linked list

    private FiboNode min;
    // Amount of elements in heap
    private int elements;
    // 1/log(golden ratio) used to count arraysize while reaordering the trees in the fibonacci heap
    private static final double goldenRatio = 1.0 / Math.log((1.0 + Math.sqrt(5.0)) / 2.0);

    /**
     * Creates empty Fibonacci heap
     */
    public FibonacciHeap() {
        min = null;
        elements = 0;
    }

    /**
     * Creates a new FiboNode with given key and inserts it to the heaps list of roots
     * @param key Key of the node to be created
     */
    public void insert(int key) {
        FiboNode node = new FiboNode(key);
        // if heap is empty, new node is the min node
        if (min == null) {
            min = node;
        } else { // otherwise we add the node next to min node
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            // Check if we need to move the min pointer to the new node
            if (key < min.key) {
                min = node;
            }
        }
        elements++;
    }

    /**
     * Finding minimal node
     * @return minimal node, null if heap is empty
     */
    public FiboNode min() {
        return min;
    }

    /**
     * Creates a union of two gives heaps by merging their lists of roots
     * @param heap1
     * @param heap2
     * @return union of the two heaps
     */
    public FibonacciHeap union(FibonacciHeap heap1, FibonacciHeap heap2) {
        FibonacciHeap result = new FibonacciHeap();
        // If one of the heaps is null, returns the other, if both are null, returns null
        if (heap1.min() == null) {
            return heap2;
        }
        if (heap2.min() == null) {
            return heap1;
        }
        // Merges the lists of root nodes from both heaps
        result.min = heap1.min;
        result.min.right.left = heap2.min;
        FiboNode temp = heap2.min.right;
        
        heap2.min.right = result.min.right;
        
        result.min.right = temp;
        
        // Checks if heap2 had smaller minimun than heap1 and moves min pointer
        if (heap2.min.key < heap1.min.key) {
            result.min = heap2.min;
        }
        // Updates amount of elements
        result.elements = heap1.elements + heap2.elements;

        return result;
    }

    /**
     * Extracts the min node, moves its children to the root list and then consolidates the heap
     * @return 
     */
    public FiboNode extractMin() {
        FiboNode removable = min;
        if (removable != null) {
            
            removeFromRootList(removable);
            // if its last node, min is set to null
            if (removable == removable.right) {
                min = null;
            } else {
                min = removable.right;
                consolidate();
            }


            elements--;
        }
        return removable;
    }

    protected void consolidate() {
        // Start by making arraylist for all possible degrees
        int arraySize = ((int) Math.floor(Math.log(elements) * goldenRatio)) + 1;
        List<FiboNode> degreeArray = new ArrayList<FiboNode>(arraySize);
        // Mark all degrees for null at present
        for (int i = 0; i < arraySize; i++) {
            degreeArray.add(null);
        }
        // Find the number of root nodes.
        int roots = countRoots();
        FiboNode current = min;

        // For each node in root list do...
        while (roots > 0) {
            int currentDegree = current.degree;
            FiboNode next = current.right;

            // check if we know another node with same degree
            for (;;) {
                FiboNode comp = degreeArray.get(currentDegree);
                if (comp == null) { // Seems not, so lets break no we can save this one
                    break;
                }
                // If there is, lets add the one with larger key as the child of the other.
                // This changes the degree of latter (obviosly)
                if (current.key > comp.key) {
                    FiboNode temp = comp;
                    comp = current;
                    current = temp;
                }
                link(comp, current);
                degreeArray.set(currentDegree, null);
                currentDegree++;
            }
            // Saving the node for reconstruction, and for comparison to latter nodes with same degree
            degreeArray.set(currentDegree, current);
            current = next;
            roots--;
        }
        // Reset the root list for recunstruction from degreeArray
        min = null;

        for (int i = 0; i < arraySize; i++) {
            FiboNode cur = degreeArray.get(i);
            if (cur == null) {
                continue;
            }
            // found a node, lets add it our new root list
            if (min != null) {
                // Remove current node from its own list
                cur.left.right = cur.right;
                cur.right.left = cur.left;

                // Adding it to list we are building
                cur.left = min;
                cur.right = min.right;
                min.right = cur;
                cur.right.left = cur;

                // Check if this is a new min.
                if (cur.key < min.key) {
                    min = cur;
                }
            } else {
                min = cur;
            }
        }
    }
    /**
     * Helper method that removes node from the root list, and adds all its
     * children to root list
     * @param removable 
     */
    private void removeFromRootList(FiboNode removable) {
        int childs = removable.degree;
            FiboNode currentChild = removable.child;
            FiboNode tempRight = null;
            // Go through the list of childs and move them to list of roots
            while (childs > 0) {
                tempRight = currentChild.right;
                currentChild.left.right = currentChild.right;
                currentChild.right.left = currentChild.left;

                currentChild.left = min;
                currentChild.right = min.right;
                min.right = currentChild;
                currentChild.right.left = currentChild;


                currentChild.parent = null;
                currentChild = tempRight;
                childs = childs - 1;
            }
            // remove the min node from the heap
            removable.left.right = removable.right;
            removable.right.left = removable.left;
    }

    /**
     * Helper method that counts the number of root nodes for consolidate()
     * @return amount of root nodes
     */
    public int countRoots() {
        int roots;
        if (min == null) {
            // oh no, empty heap, abort mission!
            return 0;
        } else { // counting roots
            roots = 1;
            FiboNode counter = min.right;
            while (counter != min) { // go through rootlist until back at min
                counter = counter.right;
                roots++;
            }
        }
        return roots;
    }

    /**
     * Helper method to make 1 node the root of other node;
     * @param child node to set as child
     * @param parent node to set as parent
     */
    private void link(FiboNode child, FiboNode parent) {
        // remove child from the doubly linked list
        child.left.right = child.right;
        child.right.left = child.left;

        // make it a child of parent
        child.parent = parent;

        // check if parent had other children
        if (parent.child != null) { // if so, lets add child to the list
            child.left = parent.child;
            child.right = parent.child.right;
            parent.child.right = child;
            child.right.left = child;
        } else { // if not, child makes the list
            parent.child = child;
            child.right = child;
            child.left = child;
        }

        // increase degree
        parent.degree++;

        // set mark[y] false
        child.mark = false;
    }
    /**
     * Deletes node from the heap by making it min and extracting
     * @param node node to delete
     */
    public void delete(FiboNode node) {
        decreaseKey(node, Integer.MIN_VALUE);
        extractMin();
    }
    /**
     * Deletes node from the heap from the place its currently is, implemented
     * from Introduction to Algorithms problem 20-1, mostly just for fun
     * and ofc. because it was good reason to make a helper method from
     * removeFromRootList(), no actual use gained according to the book.
     * @param node 
     */
    public void pisanoDelete(FiboNode node) {
        if (node == min) {
            extractMin();
        } else {           
            FiboNode y = node.parent;
            if (y != null) {
                cut(node, y);
                cascadingCut(y);
            }
            removeFromRootList(node);
        }
    }

    public void decreaseKey(FiboNode node, int newKey) {
        // If newKey is larger than current, just return without doing anything
        if (newKey > node.key) {
            return;
        }
        node.key = newKey;
        FiboNode temp = node.parent;
        // If node has smaller key than its parent, its time to cut and add it to root list
        if ((temp != null) && (node.key < temp.key)) {
            cut(node, temp);
            cascadingCut(temp);
        }

        if (node.key < min.key) {
            min = node;
        }
    }
    

    private void cut(FiboNode child, FiboNode parent) {
        // remove child from its list of siblings
        child.left.right = child.right;
        child.right.left = child.left;
        parent.degree--;

        // if child was first child, must fix it
        if (parent.child == child) {
            parent.child = child.right;
        }

        if (parent.degree == 0) {
            parent.child = null;
        }

        // Adding child to root list
        child.left = min;
        child.right = min.right;
        min.right = child;
        child.right.left = child;
        child.parent = null;
        child.mark = false;
    }

    private void cascadingCut(FiboNode y) {
        if (y.parent != null) {
            // if y is unmarked, set it marked
            if (!y.mark) {
                y.mark = true;
            } else {
                cut(y, y.parent);
                cascadingCut(y.parent);
            }
        }
    }
    /**
     * Returns a string with all key values in one line for easier testing
     * @return 
     */
    public String tester() {
        String result = "";
        FiboNode printer = min.right;
        result = result + min.testTree();
        while (printer != min) {
            result = result + printer.testTree();
            printer = printer.right;
        }
        return result;
    }
    /**
     * Returns amount of elements, for testing purposes
     * @return 
     */
    public int getElem() {
        return elements;
    }

    @Override
    public String toString() {
        String result = "";
        FiboNode printer = min.right;
        result = result + min.printTree(0);
        while (printer != min) {
            result = result + printer.printTree(0);
            printer = printer.right;
            System.out.println("");
        }
        return result;
    }
}
