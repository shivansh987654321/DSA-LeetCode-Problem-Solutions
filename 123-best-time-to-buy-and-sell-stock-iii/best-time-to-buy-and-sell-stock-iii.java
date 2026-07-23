class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        int[][][] dp = new int [n][2][3];
        for(int[][] arr : dp){
            for(int[] arr2 : arr){
                Arrays.fill(arr2,Integer.MIN_VALUE);
            }
        }
        return solve(0, 1, prices, 2, dp);
    }
    private int solve(int ind, int buy, int[] prices, int cap, int[][][] dp){
        int n = prices.length;
        if(cap == 0){
            return 0;
        }
        if(ind == n){
            return 0;
        }
        if(dp[ind][buy][cap] != Integer.MIN_VALUE){
            return dp[ind][buy][cap];
        }
        int profit = 0;
        if(buy == 1){
            profit = Math.max(-prices[ind] + solve(ind + 1,0,prices,cap,dp),
                                0 + solve(ind + 1, 1,prices, cap,dp ));
        }else{
            profit = Math.max(prices[ind] + solve(ind + 1,1,prices,cap-1,dp),
                                0 + solve(ind + 1, 0,prices, cap,dp ));
        }
        return dp[ind][buy][cap] = profit;
    }
}