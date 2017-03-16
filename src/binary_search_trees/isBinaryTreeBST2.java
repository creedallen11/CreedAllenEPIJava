package binary_search_trees;

import binary_trees.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static binary_search_trees.IsBinaryTreeBST.unitTest;

/**
 * Created by Creed on 1/12/17.
 * Check if a Binary Tree is also a BST.
 */
public class isBinaryTreeBST2 {


    private static class QueueEntry {
        public BinaryTreeNode<Integer> treeNode;
        public Integer lowerBound, upperBound;

        public QueueEntry(BinaryTreeNode<Integer> treeNode, Integer lowerBound, Integer upperBound) {
            this.treeNode = treeNode;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        Queue<QueueEntry> BFSQueue = new LinkedList<>();
        BFSQueue.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

        QueueEntry headEntry;
        while ((headEntry = BFSQueue.poll()) != null) {
            if (headEntry.treeNode != null) {
                if (headEntry.treeNode.data < headEntry.lowerBound ||
                    headEntry.treeNode.data > headEntry.upperBound) {
                    return false;
                }
            }

            BFSQueue.add(new QueueEntry(headEntry.treeNode.left, headEntry.lowerBound, headEntry.treeNode.data));
            BFSQueue.add(new QueueEntry(headEntry.treeNode.right, headEntry.treeNode.data, headEntry.upperBound));
        }

        return true;
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
        tree.left = new BinaryTreeNode<>(2);
        tree.left.left = new BinaryTreeNode<>(1);
        tree.right = new BinaryTreeNode<>(5);
        tree.right.left = new BinaryTreeNode<>(4);
        tree.right.right = new BinaryTreeNode<>(6);
        assert(isBinaryTreeBST(tree));

        unitTest(tree, true);

        tree.data = 10;

        unitTest(tree, false);

    }

}
