package algorithm_2019_4;

import java.util.Arrays;

public class RegionsCutBySlashes {
    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        RegionsCutBySlashes regionsCutBySlashes = new RegionsCutBySlashes();
        System.out.println(regionsCutBySlashes.regionsBySlashes(grid));
    }

    public int regionsBySlashes(String[] grid) {
        //unionFind array
        int length = grid.length;
        int size = 4 * grid.length * grid.length;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < grid.length; i++) {
            String element = grid[i];
            char[] chars = element.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int pos = i * length + j;
                //inner union
                if (chars[j] == ' ') {
                    unionFind.union(0 + 4 * pos, 1 + 4 * pos);
//                    System.out.println("space left= " + (0 + 4 * pos) + " right=" + (1 + 4 * pos));
                    unionFind.union(1 + 4 * pos, 2 + 4 * pos);
//                    System.out.println("space left= " + (1 + 4 * pos) + " right=" + (2 + 4 * pos));
                    unionFind.union(2 + 4 * pos, 3 + 4 * pos);
//                    System.out.println("space left= " + (2 + 4 * pos) + " right=" + (3 + 4 * pos));
                } else if (chars[j] == '/') {
                    unionFind.union(0 + 4 * pos, 3 + 4 * pos);
//                    System.out.println("///left= " + (0 + 4 * pos) + " right=" + (3 + 4 * pos));
                    unionFind.union(1 + 4 * pos, 2 + 4 * pos);
//                    System.out.println("///left= " + (1 + 4 * pos) + " right=" + (2 + 4 * pos));
                } else if (chars[j] == '\\') {
                    unionFind.union(0 + 4 * pos, 1 + 4 * pos);
//                    System.out.println("\\\\left= " + (0 + 4 * pos) + " right=" + (1 + 4 * pos));
                    unionFind.union(2 + 4 * pos, 3 + 4 * pos);
//                    System.out.println("\\\\left= " + (2 + 4 * pos) + " right=" + (3 + 4 * pos));
                }

                //inter union
                if (j < length - 1) {
                    int rightPos = i * length + j + 1;
                    unionFind.union(1 + 4 * pos, 3 + 4 * rightPos);
//                    System.out.println("right left= " + (1 + 4 * pos) + " right=" + (3 + 4 * rightPos));
                }
                if (i < length - 1) {
                    int botPos = (i + 1) * length + j;
                    unionFind.union(2 + 4 * pos, 0 + 4 * botPos);
//                    System.out.println("bottom left= " + (2 + 4 * pos) + " right=" + (0 + 4 * botPos));
                }
            }
        }


        return unionFind.rootNum();
    }

    private class UnionFind {
        int[] id;
        int count = 0;

        public UnionFind(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
//            System.out.println(Arrays.toString(id));
        }

        private void union(int i, int j) {
            id[root(i)] = root(j);
        }

        private int rootNum() {
            System.out.println(Arrays.toString(id));
            for (int i = 0; i < id.length; i++) {
                if (id[i] == i) count++;
            }
            return count;
        }

        private int root(int i) {
            while (i != id[i]) i = id[i];
            return i;
        }
    }
}
