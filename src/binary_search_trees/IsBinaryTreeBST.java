package binary_search_trees;

import binary_trees.BinaryTreePrototypeTemplate.BinaryTreeNode;

/**
 * Created by Creed on 1/12/17.
 */
public class IsBinaryTreeBST {

    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    private static boolean areKeysInRange(BinaryTreeNode<Integer> tree, Integer lower, Integer upper) {
        if (tree == null) {
            return true;
        } else if (Integer.compare(tree.data, lower) < 0 || Integer.compare(tree.data, upper) > 0) {
            return false;
        }

        return areKeysInRange(tree.left, lower, tree.data) && areKeysInRange(tree.right, tree.data, upper);
    }

    /* Simple test encapsulation / assert w/output */
    protected static void unitTest(BinaryTreeNode<Integer> root, boolean expected) {
        if (isBinaryTreeBST(root) != expected) {
            System.err.println("Wrong output; got " + !expected + " should be " + expected);
            System.err.println("Tree is: " + root);
            System.exit(-1);
        }
    }
}
