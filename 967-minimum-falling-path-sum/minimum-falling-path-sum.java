class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for(int[] arr : dp){
            Arrays.fill(arr,Integer.MIN_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++){
            int ans = solve(n-1,i,m,matrix,dp);
            min = Math.min(ans,min);
        }
        return min;
    }
    private int solve(int i, int j, int m, int[][] matrix, int[][] dp){
        if(j < 0 || j >= m){
            return (int)1e9;
        }
        if(i == 0){
            return matrix[0][j];
        }
        
        if(dp[i][j] != Integer.MIN_VALUE){
            return dp[i][j];
        }
        int up = matrix[i][j] + solve(i-1,j,m,matrix,dp);
        int upleft = matrix[i][j] + solve(i-1,j-1,m,matrix,dp);
        int upright = matrix[i][j] + solve(i-1,j+1,m,matrix,dp);
        return dp[i][j] = Math.min(up,Math.min(upleft,upright));
    }
}