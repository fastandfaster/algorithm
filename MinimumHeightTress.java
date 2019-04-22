package algorithm_2019_4;

import java.util.*;

/*
 * First try: Starting with thinking to find all the paths for one node using DFS, and choose the longest among
 * each set. The code will be very complicate.
 * Second try: However this is to find the shortest path, should use BFS. code is simple, but get TLE since this is
 *          brute force solution.
 * Third try: This is to find the middle nodes since middle notes have the shortest path to all leaves. This is again the one node to
 *          multiple nodes therefore choose the solution doing multiple nodes bfs. 这题很像卜洋葱从外面一成一成的直到中心点。
 *          First, find the outside layer -- which are leaves
 *          Second, remove these leaves one layer by layer, then find the middle piece
 *
 * */
public class MinimumHeightTress {

    public static void main(String[] args) {
/*        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};*/
        int n = 6;
        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        MinimumHeightTress minimumHeightTress = new MinimumHeightTress();
        List<Integer> result = minimumHeightTress.findMinHeightTrees2(n, edges);
        System.out.println(result);
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        //Build the edges hash
        HashMap<Integer, List<Integer>> edgesMap = new HashMap<>();
        buildEdgesMap(edgesMap, edges);
        //edge case: some nodes are not reachable
        if (edgesMap.size() < n) return res;

        //bfs for each node as root
        int min = Integer.MAX_VALUE;
        HashMap<Integer, Integer> skip = new HashMap<>();

        for (Integer root : edgesMap.keySet()
        ) {
            if (skip.containsKey(root)) continue;
            boolean[] seen = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            int count = 0;
            queue.add(root);
            while (!queue.isEmpty()) {
                count++;
                //remember the destinations
                if (count > min) {
                    while (!queue.isEmpty()) {
                        skip.put(queue.poll(), root);
                    }
                    break;
                }
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Integer node = queue.poll();
                    seen[node] = true;
                    //find all the destinations
                    List<Integer> destinations = edgesMap.get(node);
                    for (Integer dest : destinations
                    ) {
                        if (!seen[dest])
                            queue.add(dest);
                    }
                }
            }
            if (count < min) {
                min = count;
                res.clear();
            }
            if (count <= min) {
                res.add(root);
            }
        }
        return res;

    }

    private void buildEdgesMap(HashMap<Integer, List<Integer>> edgesMap, int[][] edges) {

        //To use n, we can use array instead of hashmap
        for (int i = 0; i < edges.length; i++) {
            int[] pairs = edges[i];
            int source = pairs[0];
            int dest = pairs[1];
            //pair0 as the source
            List<Integer> list = new ArrayList<>();
            if (edgesMap.containsKey(source)) {
                list = edgesMap.get(source);
            }
            list.add(dest);
            edgesMap.put(source, list);
            //pair1 as the source
            list = new ArrayList<>();
            if (edgesMap.containsKey(dest)) {
                list = edgesMap.get(dest);
            }
            list.add(source);
            edgesMap.put(dest, list);
        }
    }

    /*
     * use the third solution
     * */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        //Build the edges hash
        HashMap<Integer, List<Integer>> edgesMap = new HashMap<>();
        buildEdgesMap(edgesMap, edges);
//        System.out.println(edgesMap);
        int[] outdegree = new int[n];
        boolean[] seen = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (Integer key : edgesMap.keySet()
        ) {
            outdegree[key] = edgesMap.get(key).size();
            if (outdegree[key] == 1) queue.add(key);
        }
//        System.out.println(queue);
        while (!queue.isEmpty() && n > 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer node = queue.poll();
                n--;
                seen[node] = true;
                List<Integer> list = edgesMap.get(node);
                //should only has one dest
                Integer dest = list.get(0);
                if (!seen[dest]) {
                    //reduce the outdegree
                    List<Integer> destList = edgesMap.get(dest);
                    destList.remove(node);
                    outdegree[dest]--;
                    if (outdegree[dest] == 1) queue.add(dest);
                }
            }
        }
        while(!queue.isEmpty()){
            res.add(queue.poll());
        }
        return res;
    }

}
