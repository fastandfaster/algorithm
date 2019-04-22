package algorithm_2019_4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
    public static void main(String[] args) {
/*        int[][] matrix = {{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};*/

        int[][] matrix = {{0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}};
        Matrix01 matrix01 = new Matrix01();
        int[][] result = matrix01.updateMatrix(matrix);
        for (int[] res : result
        ) {
            System.out.println(Arrays.toString(res));
        }
    }

    /*
     * Find the closed or shortest, should use bfs.
     * Compare bfs and dfs: for such closed questions, dfs needs
     * to update the values again and again whenever the path meets it.
     * dfs is hard to stop as well because the same pos which already
     * has value need to go through again.
     * */
    public int[][] updateMatrix(int[][] matrix) {
        int[][] matrixCopy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0)
                    matrixCopy[i][j] = Integer.MAX_VALUE;
            }
        }
/*        for (int i = 0; i < matrixCopy.length; i++) {
            for (int j = 0; j < matrixCopy[0].length; j++) {
                if (matrixCopy[i][j] == Integer.MAX_VALUE) {
                    System.out.println("i=" + i + "j=" + j);
                    dfs(matrixCopy, i, j, 0);
                }
            }
        }*/

        //bfs starts from val = 0 level, update all the pos one step to it
        //then go to next level val = 1.
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrixCopy.length; i++) {
            for (int j = 0; j < matrixCopy[0].length; j++) {
                if (matrix[i][j] == 0) {
                    int[] pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                    queue.add(pos);

                }
            }
        }
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            System.out.println("x=" + x + " y=" + y);

            bfs(matrixCopy, x - 1, y, matrixCopy[x][y], queue);
            bfs(matrixCopy, x + 1, y, matrixCopy[x][y], queue);
            bfs(matrixCopy, x, y - 1, matrixCopy[x][y], queue);
            bfs(matrixCopy, x, y + 1, matrixCopy[x][y], queue);
        }

        return matrixCopy;
    }

    private void bfs(int[][] matrixCopy, int x, int y, int distance, Queue<int[]> queue) {
        //The last condition is to mark this pos already proceed and should not be proceed again to avoid the loop
        if (x < 0 || x >= matrixCopy.length || y < 0 || y >= matrixCopy[0].length || matrixCopy[x][y] == 0
        || matrixCopy[x][y] != Integer.MAX_VALUE) return;
//        System.out.println("x=" + x + " y=" + y);
        //bfs guarantee the closest, therefore no need to check the existing value
        matrixCopy[x][y] = distance + 1;
        int[] pos = new int[2];
        pos[0] = x;
        pos[1] = y;
        queue.add(pos);
    }

    private int dfs(int[][] matrixCopy, int x, int y, int distance) {
        if (x < 0 || x >= matrixCopy.length || y < 0 || y >= matrixCopy[0].length) return distance;
        if (matrixCopy[x][y] == 0) return distance;
        //If it is seen before
        if (matrixCopy[x][y] < Integer.MAX_VALUE) return matrixCopy[x][y] + distance;
        distance++;
        int count1 = dfs(matrixCopy, x - 1, y, distance);

        System.out.println("count1=" + count1 + " x=" + x + " y=" + y);
        if (count1 == 1) {
            matrixCopy[x][y] = count1;
            return count1;
        }
        int count2 = dfs(matrixCopy, x + 1, y, distance);
        System.out.println("count2=" + count2 + " x=" + x + " y=" + y);
        if (count2 == 1) {
            matrixCopy[x][y] = count2;
            return count2;
        }
        int count3 = dfs(matrixCopy, x, y - 1, distance);
        System.out.println("count3=" + count3 + " x=" + x + " y=" + y);
        if (count3 == 1) {
            matrixCopy[x][y] = count3;
            return count3;
        }
        int count4 = dfs(matrixCopy, x, y + 1, distance);
        System.out.println("count4=" + count4 + " x=" + x + " y=" + y);
        if (count4 == 1) {
            matrixCopy[x][y] = count4;
            return count4;
        }
        return Math.min(Math.min(Math.min(count1, count2), count3), count4);

    }

}
