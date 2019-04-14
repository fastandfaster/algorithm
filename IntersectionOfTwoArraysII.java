package algorithm_2019_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {};
        IntersectionOfTwoArraysII intersectionOfTwoArraysII = new IntersectionOfTwoArraysII();
        System.out.println(Arrays.toString(intersectionOfTwoArraysII.intersect(nums1, nums2)));

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return null;
        List<Integer> res;
        //Find the longer array to build the hash
        if(nums1.length > nums2.length)
            res = helper(nums1, nums2);
        else
            res = helper(nums2, nums1);
        int[] results = new int[res.size()];
        int i = 0;
        for (Integer r: res
        ) {
            results[i] = r;
            i++;
        }
        return results;

    }

    private List<Integer> helper(int[] longer, int[] shorter){
        List<Integer> intersections = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num: longer
        ) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
//        System.out.println(map);
        for (Integer num: shorter
        ) {
            if(map.containsKey(num) && map.get(num) != 0){
                intersections.add(num);
                map.put(num, map.get(num)-1);

            }
        }
        return intersections;
    }
}
