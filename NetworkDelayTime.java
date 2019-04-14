package algorithm_2019_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetworkDelayTime {
    public static void main(String[] args) {
/*        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;*/


/*        int[][] times = {{1, 2, 1}, {2, 3, 2}, {1, 3, 2}};
        int N = 3;
        int K = 1;*/

        int[][] times = {{1,2,1},{2,3,7},{1,3,4},{2,1,2}};
        int N = 3;
        int K = 2;
        NetworkDelayTime networkDelayTime = new NetworkDelayTime();
        System.out.println(networkDelayTime.networkDelayTime(times, N, K));
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        //Build Hash on times array
        for (int[] time : times
        ) {
            int from = time[0];
            int to = time[1];
            int spend = time[2];
            List<int[]> list = new ArrayList<>();
            if (map.containsKey(from))
                list = map.get(from);
            int[] toTime = new int[2];
            toTime[0] = time[1];
            toTime[1] = time[2];
            list.add(toTime);
            map.put(from, list);
//            System.out.println(from + " " + Arrays.toString(toTime));
        }
        HashMap<Integer, Integer> shortestDistance = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            shortestDistance.put(i, Integer.MAX_VALUE);
        }
        int max = 0;
        dfs(map, K, shortestDistance, 0);
        System.out.println(shortestDistance);
        for (Integer key : shortestDistance.keySet()
        ) {
            if (shortestDistance.get(key) == Integer.MAX_VALUE) return -1;
            max = Math.max(max, shortestDistance.get(key));
        }
        return max;
    }

    private void dfs(HashMap<Integer, List<int[]>> map, int cur, HashMap<Integer, Integer> shortestDistance, int sum) {
        //Reduce the recursion: the sum is already longer, don't need to proceed
        //Use equal to avoid loop
        if (sum >= shortestDistance.get(cur)) return;
        shortestDistance.put(cur, sum);
        //Need to update the last one. Therefore the put is before
        if (!map.containsKey(cur)) return;
        List<int[]> toTimes = map.get(cur);

        for (int i = 0; i < toTimes.size(); i++) {
            int[] toTime = toTimes.get(i);
            dfs(map, toTime[0], shortestDistance, sum + toTime[1]);
        }
    }
}
