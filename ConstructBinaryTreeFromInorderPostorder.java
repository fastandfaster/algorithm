package algorithm_2019_4;

import java.util.HashMap;

public class ConstructBinaryTreeFromInorderPostorder {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> inorderPos = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderPos.put(inorder[i], i);
        }
        TreeNode root = dfs(postorder.length - 1, 0, postorder.length,
                inorder, postorder, inorderPos);
        return root;
    }

    private TreeNode dfs(int rootPos, int treeLeftBoundary, int treeRightBoundary,
                         int[] inorder, int[] postorder, HashMap<Integer, Integer> inorderPos) {
        if (rootPos < 0 || treeLeftBoundary > treeRightBoundary) return null;
        int rootVal = postorder[rootPos];
        TreeNode root = new TreeNode(rootVal);
        int posInorder = inorderPos.get(rootVal);
        //Right tree
        //Find rootPos of right branch tree from postorder
        int rightRootPos = rootPos - 1;
        //Find the branch tree boundary from inorder
        int rightTreeLeftBoundary = posInorder + 1;
        int rightTreeRightBoundary = treeRightBoundary;
        root.right = dfs(rightRootPos, rightTreeLeftBoundary, rightTreeRightBoundary,
                inorder, postorder, inorderPos);
        int rightTreeSize = rightTreeRightBoundary - rightTreeLeftBoundary + 1;
        //Left tree
        //Find rootPos of left branch tree from postorder and inorder
        int leftRootPos = rootPos - rightTreeSize - 1;
        //Find the branch tree boundary from inorder
        int leftTreeLeftBoundary = treeLeftBoundary;
        int leftTreeRightBoundary = posInorder - 1;
        root.left = dfs(leftRootPos, leftTreeLeftBoundary, leftTreeRightBoundary,
                inorder, postorder, inorderPos);
        return root;

    }
}
