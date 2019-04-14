package algorithm_2019_4;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightView {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        // HashMap<Integer, List<Integer>> map = new HashMap<>();
        // List<Integer> resultList = new ArrayList<>();
        // helper(root, 0, map);
        // // System.out.println(map);
        // for (Integer key: map.keySet()
        //      ) {
        //     List<Integer> list = map.get(key);
        //     resultList.add(list.get(0));
        // }
        // return resultList;

        //Second try
//         HashMap<Integer, Integer> map = new HashMap<>();
//         List<Integer> resultList = new ArrayList<>();
//         helper(root, 0, map);
//         for (Integer key: map.keySet()
//              ) {
//             resultList.add(map.get(key));

//         }
//         return resultList;

        //Third try
        List<Integer> resultList = new ArrayList<>();
        helper(root, 0, resultList);
        return resultList;
    }

    private void helper(TreeNode root, int level, List<Integer> res) {
        //First try
        // if(root == null) return;
        // //Increase the level
        // level++;
        // //Add to res list and hash
        // List<Integer> list = new ArrayList<>();
        // if(hashMap.containsKey(level)){
        //     list = hashMap.get(level);
        // }
        // list.add(root.val);
        // hashMap.put(level, list);
        // helper(root.right, level, hashMap);
        // helper(root.left, level, hashMap);
        // if(root == null) return;
        //Increase the level

        //Second try
        //        if(root == null) return;
        // //Increase the level
        // level++;
        // //Add to res list and hash
        // if(!hashMap.containsKey(level)){
        //     hashMap.put(level, root.val);
        // }
        // helper(root.right, level, hashMap);
        // helper(root.left, level, hashMap);

        //Third try
        if (root == null) return;
        //Each level only has one value
        if (level == res.size())
            res.add(root.val);
        level++;
        helper(root.right, level, res);
        helper(root.left, level, res);
    }
}
