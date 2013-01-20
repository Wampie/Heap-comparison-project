/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrateht1;

/**
 *
 * @author Wampie
 */
public class BinHeap {

    int heapSize = 0;
    HeapNode root = null;

    public void heapInsert(int value) {
        if (heapSize == 0) {
            HeapNode newNode = new HeapNode(value);
            root = newNode;
            heapSize++;
        } else {
            Stack route = findNode(heapSize + 1);
            HeapNode newParent = root;
            int lastPopped = route.pop();
            while (lastPopped != -1) {
                if (newParent.left == null) {
                    HeapNode newNode = new HeapNode(value);
                    newNode.parent = newParent;
                    newParent.left = newNode;
                    heapSize++;
                    if ((newParent.parent != null) && (newParent.parent.value < newParent.value)) {
                        newParent = newParent.parent;
                    }
                    bubbleUp(newNode);
                    return;
                } else if (newParent.right == null) {
                    HeapNode newNode = new HeapNode(value);
                    newNode.parent = newParent;
                    newParent.right = newNode;
                    heapSize++;
                    bubbleUp(newNode);
                    return;
                }
                if (lastPopped == 0) {
                    newParent = newParent.left;
                } else {
                    newParent = newParent.right;
                }

            }
        }
    }

    public HeapNode deleteMin() {
        if (root == null) {
            return null;
        } else {
            HeapNode returnable = root;
            HeapNode last = root;
            Stack route = findNode(heapSize);
            int popped;
            while ((popped = route.pop()) != -1) {
                if (popped == 0) {
                    last = last.left;
                } else {
                    last = last.right;
                }
            }
            last.left = root.left;
            last.right = root.right;
            root = last;
            if (last.parent.left.value == last.value) {
                last.parent.left = null;
            } else {
                last.parent.right = null;
            }
            heapSize--;
            if (heapSize == 0) {
                root = null;
            }
            heapify(root);
            return returnable;

        }
    }

    public void print(HeapNode a, int depth) {
        if (a.left != null) {
            print(a.left, depth + 1);
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(a.value + "");
        if (a.right != null) {
            print(a.right, depth + 1);
        }
    }

    private Stack findNode(int nodeNum) {
        Stack result = new Stack();
        while (nodeNum > 1) {
            result.push(nodeNum % 2);
            nodeNum = nodeNum / 2;
        }
        return result;
    }

    public void heapify(HeapNode a) {
        if (a == null) {
            return;
        }
        int help = a.value;

        // If nodes childs have greater values than node, or node has no childs, return
        if (a.left == null) {
            return;
        }
        if ((a.left == null) && (a.right == null)) {
            return;
        }
        if ((a.value < a.left.value) && ((a.right == null) || (a.value < a.right.value))) {
            return;
        }
        if ((a.right == null) || (a.left.value < a.right.value)) {
            a.value = a.left.value;
            a.left.value = help;
            heapify(a.left);
        } else {
            a.value = a.right.value;
            a.right.value = help;
            heapify(a.left);
        }
    }

    public void bubbleUp(HeapNode a) {
        if (a.parent == null) {
            return;
        }
        if (a.parent.value < a.value) {
            return;
        } else {
            int help = a.value;
            a.value = a.parent.value;
            a.parent.value = help;
            bubbleUp(a.parent);
        }
    }

    public class HeapNode {

        HeapNode parent;
        HeapNode left;
        HeapNode right;
        int value;

        public HeapNode(int value) {
            parent = null;
            this.value = value;
            left = null;
            right = null;
        }
    }
}
