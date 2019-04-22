package algorithm_2019_4;

import java.util.*;

/*
 * DFS: search for path, stop condition, backtracking and seen.
 * My first version: brute force dfs for each node and find one path.
 *   The stop condition -- the path contains both "P" and "A" - determine if the node is either "P" or "A" or both.
 *   The stop condition has many conditions inside, therefore make the code not easy understand
 *
 *   BackTracking -- other than stop condition, backtracking and seen array are also required. backTracking causes longer
 *       running time because same node needs to visited again and again. The better solution does not need backtracking, if the
 *       node is visited, no need to be visited again. At each level, all the branches of the node has been proceed.
 *
 *   Better solution:
 *   那么就是典型的搜索问题，我最开始想的是对于每个点都来搜索是否能到达边缘，只不过搜索的目标点不再是一个单点，而是所有的边缘点，
 *   照这种思路写出的代码无法通过 OJ 大数据集，那么就要想办法来优化代码，优化的方法跟之前那道 Surrounded Regions 很类似
 *   那么我们就把所有边缘点当作起点开始遍历搜索，然后标记能到达的点为 true，分别标记出 pacific 和 atlantic 能到达的点，那么最终能返回的点就是二者均为 true 的点。
 *
 *   Conclusion: For this type of question related to side of the matrix, we can use the same idea searching from the side of the matrix.
 *       by using this way, only the side nodes of the matrix are the searching node, all the nodes are only need to be visited once (or 4 times).
 * */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}};
        PacificAtlanticWaterFlow pacificAtlanticWaterFlow = new PacificAtlanticWaterFlow();
        List<int[]> result = pacificAtlanticWaterFlow.pacificAtlantic2(matrix);
        for (int[] element: result
             ) {
            System.out.println(Arrays.toString(element));
        }
    }

    /*
     * This needs to find the path, therefore use dfs
     * */
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<List<int[]>> paths = new ArrayList<>();
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                boolean[][] seen = new boolean[matrix.length][matrix[0].length];
                List<int[]> path = new ArrayList<>();
                Set<String> continent = new HashSet<>();
                dfs(matrix, seen, path, paths, i, j, matrix[i][j], continent);
            }
        }
        //Find the first element in each list
        for (List<int[]> p : paths
        ) {
            result.add(p.get(0));
        }
        return result;
    }

    public List<int[]> pacificAtlantic2(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if(matrix.length == 0 || matrix[0].length == 0) return res;
        int width = matrix.length;
        int length = matrix[0].length;
        boolean[][] pacific = new boolean[width][length];
        boolean[][] atlantic = new boolean[width][length];
        for (int i = 0; i < width; i++) {
            dfs2(matrix, pacific, i, 0, matrix[i][0]);
            dfs2(matrix, atlantic, i, length - 1, matrix[i][length - 1]);
        }
        for (int j = 0; j < length; j++) {
            dfs2(matrix, pacific, 0, j, matrix[0][j]);
            dfs2(matrix, atlantic, width - 1, j, matrix[width - 1][j]);
        }
        //Check if these two matrix have match
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    int[] node = new int[2];
                    node[0] = i;
                    node[1] = j;
                    res.add(node);
                }
            }
        }
        return res;
    }

    /*
     * Scan from side P/A nodes and mark all the nodes as P/A when dfs searching.
     * pay attention to the value comparison: the next one should be bigger.
     * */
    private void dfs2(int[][] matrix, boolean[][] visited, int x, int y, int val) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y]) return;
        if (matrix[x][y] < val) return;
        visited[x][y] = true;
        val = matrix[x][y];
        dfs2(matrix, visited, x, y + 1, val);
        dfs2(matrix, visited, x, y - 1, val);
        dfs2(matrix, visited, x - 1, y, val);
        dfs2(matrix, visited, x + 1, y, val);
    }

    private boolean dfs(int[][] matrix, boolean[][] seen, List<int[]> path, List<List<int[]>> paths, int x, int y, int val,
                        Set<String> continent) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || seen[x][y]) return false;
        if (val < matrix[x][y]) {
            return false;
        }
        //The comparision is between current node and next node
        val = matrix[x][y];
        //Add cur position
        int[] cell = new int[2];
        cell[0] = x;
        cell[1] = y;
        path.add(cell);
        seen[x][y] = true;

        //The condition is the node goes to two edges of the matrix
        if (x == 0 || x == matrix.length - 1 || y == 0 || y == matrix[0].length - 1) {
            if ((x == matrix.length - 1 && y == 0) || (x == 0 && y == matrix[0].length - 1)) {
                if (!continent.contains("p")) {
                    continent.add("p");
                } else if (!continent.contains("a"))
                    continent.add("a");
            } else if (x == 0 || y == 0) {
                if (continent.contains("p")) {
                    seen[x][y] = false;
                    if (path.size() > 0) path.remove(path.size() - 1);
                    return false;
                }
                continent.add("p");
            } else if (x == matrix.length - 1 || y == matrix[0].length - 1) {
                if (continent.contains("a")) {
                    seen[x][y] = false;
                    if (path.size() > 0) path.remove(path.size() - 1);
                    return false;
                }
                continent.add("a");
            }
            if (continent.size() == 2) {
                paths.add(new ArrayList<>(path));
                return true;
            }
        }


        //Based on condition go to 4 directions: bigger than all directions
        if (dfs(matrix, seen, path, paths, x, y + 1, val, continent)) return true;
        if (dfs(matrix, seen, path, paths, x, y - 1, val, continent)) return true;
        if (dfs(matrix, seen, path, paths, x - 1, y, val, continent)) return true;
        if (dfs(matrix, seen, path, paths, x + 1, y, val, continent)) return true;
        return false;

    }
}
