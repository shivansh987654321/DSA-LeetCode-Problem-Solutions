class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(amount, coins, 0,dp);
    }
    private int solve(int amount, int[] coins, int i,int[][]dp){
        if(amount == 0){
            return 1;
        }

        if(i >= coins.length){
            return 0;
        }
        if(dp[i][amount] != -1){
            return dp[i][amount];
        }
        if(amount < coins[i]){
            return dp[i][amount] = solve(amount, coins, i + 1,dp);
        }
        int take = solve(amount - coins[i] , coins, i,dp);
        int skip = solve(amount ,coins, i + 1,dp);
        return dp[i][amount] = take + skip;
    }
}