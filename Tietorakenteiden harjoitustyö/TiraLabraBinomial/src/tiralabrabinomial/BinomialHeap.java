/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrabinomial;


/** Implementation of a binomial heap from Cormen
 * 
 * @author Wampie
 */
public class BinomialHeap {

    private Node head;

    public BinomialHeap() {
        head = null;		// make an empty root list
    }

    /**
     * Returns the node with smallest value.
     * @return pointer to the min node in the heap.
     * @throws EmptyHeapException 
     */
    public Node minimum() throws EmptyHeapException {
        // checking if the heap has values
        if (head == null) {
            throw new EmptyHeapException("This Heap is empty");
        } else {
            Node min = head;
            Node x = min.sibling;

            while (x != null) {
                if (x.value < min.value) {
                    min = x;
                }
                x = x.sibling;
            }

            return min;
        }
    }

    private void binomialLink(Node y, Node z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.amountOfChilds++;
    }

    /**
     * Merges any given Binomial heap with the current heap.
     */
    public BinomialHeap union(BinomialHeap heapToMerge) {
        BinomialHeap h = new BinomialHeap();
        h.head = binomialHeapMerge(this, heapToMerge);
        head = null;
        heapToMerge.head = null;

        if (h.head == null) {
            return h;
        }

        Node prev = null;
        Node current = h.head;
        Node next = current.sibling;

        while (next != null) {
            if (current.amountOfChilds != next.amountOfChilds
                    || (next.sibling != null && next.sibling.amountOfChilds == current.amountOfChilds)) {
                // If current and next does not have equal amount of childs, or if current, and next two siblings have the same amount of childs
                prev = current;
                current = next;
            } else {
                if (current.value < next.value) {
                    // If current and next have equal amount of childs and 
                    // current has the smaller value, so next will be linked to current
                    current.sibling = next.sibling;
                    binomialLink(next, current);
                } else {
                    // In this case, next holds smaller value, so current will be
                    // linked to next.
                    if (prev == null) {
                        h.head = next;
                    } else {
                        prev.sibling = next;
                    }

                    binomialLink(current, next);
                    current = next;
                }
            }

            next = current.sibling;
        }

        return h;
    }

    /**
     * Merges the two binomial heaps root lists.
     */
    private static Node binomialHeapMerge(BinomialHeap heap1, BinomialHeap heap2) {
        // check if either heaps are empty.
        if (heap1.head == null) {
            return heap2.head;
        } else if (heap2.head == null) {
            return heap1.head;
        } else {
            Node head;
            Node tail;
            Node heap1Next = heap1.head;
            Node heap2Next = heap2.head;

            if (heap1.head.amountOfChilds <= heap2.head.amountOfChilds) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }

            tail = head;

            while (heap1Next != null && heap2Next != null) {
                if (heap1Next.amountOfChilds <= heap2Next.amountOfChilds) {
                    tail.sibling = heap1Next;
                    heap1Next = heap1Next.sibling;
                } else {
                    tail.sibling = heap2Next;
                    heap2Next = heap2Next.sibling;
                }
                tail = tail.sibling;
            }
            if (heap1Next != null) {
                tail.sibling = heap1Next;
            } else {
                tail.sibling = heap2Next;
            }
            return head;
        }
    }

    /**
     * Creates new node to empty binomial heap, then uses union to insert it 
     * into the existing heap.
     * @param value new value to be inserted 
     */
    public void insert(int value) {
        Node x = new Node(value);
        BinomialHeap hPrime = new BinomialHeap();
        hPrime.head = x;
        BinomialHeap newHeap = this.union(hPrime);
        head = newHeap.head;
    }
       /** Removes and returns the smallest value in the heap. */
    public int extractMin() throws EmptyHeapException {
        // checking if the heap has values
        if (head == null) {
            throw new EmptyHeapException("This Heap is empty");
        }

        // Find the root x with the minimum key in the root list.
        Node target = head;		// node with minimum key
        Node cur = target.sibling;	// current node being examined
        Node pred = target;		// y's predecessor
        Node tarPred = null;	// predecessor of target

        while (cur != null) {
            if (cur.value < target.value) {
                target = cur;
                tarPred = pred;
            }
            pred = cur;
            cur = cur.sibling;
        }

        removeFromRootList(target, tarPred);
        return target.value;
    }

    /**
     * Method to remove a node from the root list, 
     */
    private void removeFromRootList(Node target, Node pred) {
        // Splice out x.
        if (target == head) {
            head = target.sibling;
        } else {
            pred.sibling = target.sibling;
        }

        BinomialHeap hPrime = new BinomialHeap();

        // Reverse the order of x's children
        Node temp = target.child;
        while (temp != null) {
            Node next = temp.sibling;
            temp.sibling = hPrime.head;
            hPrime.head = temp;
            temp = next;
        }

        BinomialHeap newHeap = this.union(hPrime);
        head = newHeap.head;
    }

    public void decreaseKey(Node target, int newKey) {
        if (target.value < newKey) {
            System.out.println("New key greater than the old key, could not decrease.");
            return;
        }
        target.value = newKey;	// update x's key
        bubbleUp(target);	// bubble it up until it's in the right place
    }

    /**
     * Bubbles the value in node up to the right place the binomial heap.
     */
    public Node bubbleUp(Node x) {
        Node current = x.parent;

        while (current != null && (x.value < current.value)) {
            int yValue = x.value;
            x.value = current.value;
            current.value = yValue;

            x = current;
            current = x.parent;
        }
        return x;
    }

    /**
     * Deletes a node.
     * Sets a nodes value to minimun value of an integer (closest we can get to 
     * -infinity) and then extracts it from heap.
     */
    public void delete(Node target) throws EmptyHeapException {
        decreaseKey(target, Integer.MIN_VALUE);
        extractMin();

    }

    /**
     * Goes through every tree in the heap with printTree for the String representation
     * of the entire heap
     */
    @Override
    public String toString() {
        String result = "";

        Node x = head;
        while (x != null) {
            result += x.printTree(0);
            x = x.sibling;
        }

        return result;
    }
    /**
     * Testable String presentation for heap
     * 
     */
    public String testHeap() {
                String result = "";

        Node x = head;
        while (x != null) {
            result += x.testTree(0);
            x = x.sibling;
        }

        return result;
    }
}
