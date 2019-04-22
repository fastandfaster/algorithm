package algorithm_2019_4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean left = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] level = new int[size];
            int count = 0;
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                level[count] = node.val;
                size--;
                count++;
            }
            //iterator the array according the left boolean
            List<Integer> levelList = new ArrayList<>();
            if (level.length > 0) {
                if (left) {
                    for (int i = 0; i < level.length; i++)
                        levelList.add(level[i]);
                    left = false;
                } else {
                    for (int i = level.length - 1; i >= 0; i--)
                        levelList.add(level[i]);
                    left = true;
                }
            }
            res.add(levelList);
        }
        return res;
    }
}
