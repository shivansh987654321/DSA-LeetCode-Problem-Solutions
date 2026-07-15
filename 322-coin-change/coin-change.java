class Solution {
    public int coinChange(int[] coins, int amount) {
        int[]dp = new int[amount + 1];
        Arrays.fill(dp,-1);
        int anss = solve(coins,amount,dp);
        if(anss == Integer.MAX_VALUE){
            return -1;
        }else{
            return anss;
        }
    }
    private int solve(int[] coins, int amount,int[]dp){
        if(amount == 0){
            return 0;
        }
        if(amount <= 0){
            return Integer.MAX_VALUE;
        }
        if(dp[amount] != -1){
            return dp[amount];
        }
        
        int n = coins.length;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int temp = solve(coins,amount-coins[i],dp);
            if(temp != Integer.MAX_VALUE){
                ans = Math.min(ans,1+temp);
            }
        }
        dp[amount] = ans;
        return ans;
    }
}