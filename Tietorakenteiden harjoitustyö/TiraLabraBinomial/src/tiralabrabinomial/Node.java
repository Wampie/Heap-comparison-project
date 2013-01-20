/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrabinomial;

/**
 *
 * @author Wampie
 */
public class Node {
    
        public int value;
        public Node parent;
        /** This node's leftmost child*/
        public Node child;
        /** This node's right sibling */
        public Node sibling;
        public int amountOfChilds;

        public Node(int v) {
            value = v;
            parent = null;
            child = null;
            sibling = null;
            amountOfChilds = 0;
        }

        @Override
        public String toString() {
            return "key = " + value + ", has " + amountOfChilds + " children";
        }

        /**
         * Returns the String representation of a tree in the binomial heap.
         * Each key in the representation is preceeded by two spaces per level of depth.
         *
         * @param depth Depth of this node.
         */
        public String printTree(int depth) {
            String result = "";

            // prints spaces for depth of a node for easier reading
            for (int i = 0; i < depth; i++) {
                result += "  ";
            }

            result += toString() + "\n";

            Node x = child;
            // goes recursively through every child of a given node
            while (x != null) {
                result += x.printTree(depth + 1);
                x = x.sibling;
            }

            return result;
        }

        public String testTree(int depth) {
            String result = "";

            result += value + " ";

            Node x = child;
            // goes recursively through every child of a given node
            while (x != null) {
                result += x.testTree(depth + 1);
                x = x.sibling;
            }

            return result;
        }
}
