package algorithm_2019_4;

public class FriendCircles {
    public static void main(String[] args) {
//        int[][] M = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        int[][] M = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        FriendCircles friendCircles = new FriendCircles();

        System.out.println(friendCircles.findCircleNum(M));
    }

    public int findCircleNum(int[][] M) {
        int count = 0;
        int[] visited = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            if (visited[i] != 1) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    /*
     * Start with the current row, if find the friend of the current row, dfs to find all the
     * friends of this friend
     * */
    private void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M[0].length; j++) {
            if (M[i][j] == 1 && visited[j] != 1) {
                visited[j] = 1;
                //If j is friend of i, find the friends of j
                dfs(M, visited, j);
            }
        }
    }
}
