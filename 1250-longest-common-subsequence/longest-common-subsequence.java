class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];
        for(int i = 0 ; i < m; i++){
            Arrays.fill(dp[i],-1);
        }
        return solve(text1,text2,0,0,dp);
    }
    private int solve(String text1, String text2 , int i, int j, int[][]dp){
        if(i >= text1.length() || j >= text2.length()){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            dp[i][j] =  (1 + solve(text1,text2,i+1,j+1,dp));
        }else{
            dp[i][j] =  Math.max(solve(text1,text2,i+1,j,dp) , solve(text1,text2,i,j+1,dp));
        }
        return dp[i][j];
    }
}