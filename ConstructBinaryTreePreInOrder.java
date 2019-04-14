package algorithm_2019_4;

import java.util.HashMap;

public class ConstructBinaryTreePreInOrder {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        ConstructBinaryTreePreInOrder constructBinaryTreePreInOrder = new ConstructBinaryTreePreInOrder();

        System.out.println(constructBinaryTreePreInOrder.buildTree(preorder, inorder));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //Build hash on the inorder array
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = helper(preorder, inorder, 0, 0, inorder.length - 1, inorderMap);
        return root;

    }

    private TreeNode helper(int[] preorder, int[] inorder, int preOrderRootPos, int treeLeftBoundary,
                            int treeRightBoundary, HashMap<Integer, Integer> inorderMap) {
 /*       if (treeLeftBoundary < 0 || treeRightBoundary < 0 || treeLeftBoundary >= inorder.length ||
                treeRightBoundary >= inorder.length || preOrderRootPos < 0 || preOrderRootPos >= inorder.length)
            return null;*/
        if (preOrderRootPos >= inorder.length || treeLeftBoundary > treeRightBoundary) return null;

        int inOrderRootPos = inorderMap.get(preorder[preOrderRootPos]);
        System.out.println(preOrderRootPos);
        //Build the root of the tree
        TreeNode node = new TreeNode(preorder[preOrderRootPos]);
        //The left of the inorder is left tree and build the left tree
        TreeNode left = helper(preorder, inorder, preOrderRootPos + 1, treeLeftBoundary, inOrderRootPos - 1, inorderMap);
        //The right of the inorder is right tree and build the right tree
        TreeNode right = helper(preorder, inorder, preOrderRootPos + (inOrderRootPos - treeLeftBoundary) + 1,
                inOrderRootPos + 1, treeRightBoundary, inorderMap);
        node.left = left;
        node.right = right;
//        System.out.println(node);
        return node;
    }
}
