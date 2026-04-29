class Solution {
    public void setZeroes(int[][] matrix) {
        int col0 = 1;
        // While traversing the 2D matrix, make the rows and column index to be 0 if we found 0 while iteration.
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // ye row ko zero bnayega 

                    if (j != 0) {
                        matrix[0][j] = 0; // ye col ko zero bnayega 
                    } else {
                        col0 = 0;
                    }
                }

            }
        }

        // Now traverse the matrix again and check if the row and column index is zero. If any one of it is zero, then make the matrix of i, j to be zero.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // In the last step, we will handle the edge case, like if zero comma zero in the matrix is zero, then everyone in the column will be zero. And similarly, if the column zero is zero, then everyone in the row will be zero.
        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }

        }
        if (col0 == 0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        
    }
}