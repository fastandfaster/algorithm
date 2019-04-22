package algorithm_2019_4;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftTreeValue {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return res;
    }
}
