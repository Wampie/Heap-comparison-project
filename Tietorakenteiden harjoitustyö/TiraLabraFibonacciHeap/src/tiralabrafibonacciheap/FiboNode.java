/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrafibonacciheap;

/**
 *
 * @author Wampie
 */
public class FiboNode {

    /**
     * First child, parent and siblings
     */
    FiboNode child;
    FiboNode parent;
    FiboNode left;
    FiboNode right;
    /**
     * Marker indicating removed child after adding this node to its parents
     */
    boolean mark;
    int key;
    int degree;

    public FiboNode(int key) {
        right = this;
        left = this;
        this.key = key;
        mark = false;
    }

    public final int getKey() {
        return key;
    }
    
    public String printTree(int depth) {
        String result = "";

            // prints spaces for depth of a node for easier reading
            for (int i = 0; i < depth; i++) {
                result += "  ";
            }

            result += toString() + "\n";
            if (child == null) {
                return result;
            }
            FiboNode x = child;
            result += x.printTree(depth + 1);
            FiboNode help = x;
            x = x.left;
            // goes recursively through every child of a given node
            while (x != help) {
                result += x.printTree(depth + 1);
                x = x.left;
            }

            return result;
    }
    public String testTree() {
        String result = "";

            

            result += key + " ";
            if (child == null) {
                return result;
            }
            FiboNode x = child;
            result += x.testTree();
            FiboNode help = x;
            x = x.left;
            // goes recursively through every child of a given node
            while (x != help) {
                result += x.testTree();
                x = x.left;
            }

            return result;
    }

    public String toString() {
        String result = "";
        result = result + "Key: " + key;
        if (parent != null) {
            result = result + ", Parent: " + parent.getKey();
        }
        result = result + ", Degree: " + degree;
        if (left != null) {
            result = result + ", Left: " + left.getKey();
        }
        if (right != null) {
            result = result + ", Right: " + right.getKey();
        }
        return result;
    }
}
