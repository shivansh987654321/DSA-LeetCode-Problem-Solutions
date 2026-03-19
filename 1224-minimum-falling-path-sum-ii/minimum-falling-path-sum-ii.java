class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;

        int min1 = 0, min2 = 0;
        int min1Col = -1;

        for (int i = 0; i < n; i++) {
            int currMin1 = Integer.MAX_VALUE;
            int currMin2 = Integer.MAX_VALUE;
            int currMin1Col = -1;

            for (int j = 0; j < n; j++) {
                int val = grid[i][j];

                if (j == min1Col) {
                    val += min2;
                } else {
                    val += min1;
                }

                if (val < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = val;
                    currMin1Col = j;
                } else if (val < currMin2) {
                    currMin2 = val;
                }
            }

            min1 = currMin1;
            min2 = currMin2;
            min1Col = currMin1Col;
        }

        return min1;
    }
}