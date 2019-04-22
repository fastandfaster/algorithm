package algorithm_2019_4;

import java.util.*;

public class AllNodesDistanceKInBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(1);
        root.left = left;
        root.right = right;
        TreeNode ll = new TreeNode(6);
        TreeNode lr = new TreeNode(2);
        left.left = ll;
        left.right = lr;
        TreeNode rl = new TreeNode(0);
        TreeNode rr = new TreeNode(8);
        right.left = rl;
        right.right = rr;
        TreeNode lrl = new TreeNode(7);
        TreeNode lrr = new TreeNode(4);
        lr.left = lrl;
        lr.right = lrr;
        System.out.println(root);

        TreeNode target = new TreeNode(5);
        int K = 2;

        AllNodesDistanceKInBinaryTree allNodesDistanceKInBinaryTree = new AllNodesDistanceKInBinaryTree();
        List<Integer> res = allNodesDistanceKInBinaryTree.distanceK(root, target, 2);
        System.out.println(res);
    }

    public static class TreeNode {
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

    /*
     * First try: inorder traversal the tree to build the edges hash
     *            then from the target to do the K step bfs
     * */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if(root == null || target == null) return res;
        Stack<TreeNode> treeNodeStack = new Stack<>();
        HashMap<Integer, List<Integer>> edgesMap = new HashMap<>();
        treeNodeStack.add(root);
        while (!treeNodeStack.isEmpty()) {
            TreeNode node = treeNodeStack.pop();
            if (node.right != null) {
                TreeNode right = node.right;
                buildEdgesMap(edgesMap, node, right);
                treeNodeStack.add(right);
            }
            if (node.left != null) {
                TreeNode left = node.left;
                buildEdgesMap(edgesMap, node, left);
                treeNodeStack.add(left);
            }
        }

        //bfs the target node
        int count = 0;
        boolean[] seen = new boolean[edgesMap.size()];

        Queue<Integer> queue = new LinkedList<>();
        if (edgesMap.containsKey(target.val)) {
            queue.add(target.val);
            seen[target.val] = true;
            if(K == 0){
                res.add(target.val);
                return res;
            }
            while (!queue.isEmpty() && count <= K - 1) {
                count++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Integer nodeVal = queue.poll();
                    List<Integer> list = edgesMap.get(nodeVal);
                    for (Integer val : list
                    ) {
                        if(!seen[val]){
                            queue.add(val);
                            seen[val] = true;
                        }

                    }
                }
            }
            //All the nodes inside the queue should be the result
            while(!queue.isEmpty())
                res.add(queue.poll());
        }
        return res;
    }

    private void buildEdgesMap(HashMap<Integer, List<Integer>> edgesMap, TreeNode rootNode, TreeNode childNode) {
        //root as key
        List<Integer> list = new ArrayList<>();
        if (edgesMap.containsKey(rootNode.val)) {
            list = edgesMap.get(rootNode.val);
        }
        list.add(childNode.val);
        edgesMap.put(rootNode.val, list);
        //right as key
        list = new ArrayList<>();
        if (edgesMap.containsKey(childNode.val))
            list = edgesMap.get(childNode.val);
        list.add(rootNode.val);
        edgesMap.put(childNode.val, list);
    }
}
