package algorithm_2019_4;

public class MaxAreaOfIsland {

    public static void main(String[] args) {
/*        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};*/

        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }


    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !seen[i][j]) {
                    seen[i][j] = true;
                    int count = dfs(grid, i, j, seen, 1);
                    max = Math.max(count, max);
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int x, int y, boolean[][] seen, int count) {
//        if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0) return count;
        //Move down
        if (x + 1 < grid.length && grid[x + 1][y] == 1 && !seen[x + 1][y]) {
            seen[x + 1][y] = true;
            count = dfs(grid, x + 1, y, seen, ++count);
        }
        //Move up
        if (x - 1 >= 0 && grid[x - 1][y] == 1 && !seen[x - 1][y]) {
            seen[x - 1][y] = true;
            count = dfs(grid, x - 1, y, seen, ++count);
        }
        //Move right
        if (y + 1 < grid[0].length && grid[x][y + 1] == 1 && !seen[x][y + 1]) {
            seen[x][y + 1] = true;
            count = dfs(grid, x, y + 1, seen, ++count);
        }
        //Move left
        if (y - 1 >= 0 && grid[x][y - 1] == 1 && !seen[x][y - 1]) {
            seen[x][y - 1] = true;
            count = dfs(grid, x, y - 1, seen, ++count);
        }
        return count;
    }

    /*
     * Use one condition to check for the four inner conditions
     * */
    private int dfs2(int[][] grid, int x, int y, boolean[][] seen, int count) {
        if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0 || seen[x][y]) return count;
        count++;
        seen[x][y] = true;
        count = dfs2(grid, x + 1, y, seen, count);
        count = dfs2(grid, x - 1, y, seen, count);
        count = dfs2(grid, x, y + 1, seen, count);
        count = dfs2(grid, x, y - 1, seen, count);
        return count;
    }
}
