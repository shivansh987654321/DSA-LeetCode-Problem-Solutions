class Solution {
    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[2501][2501];
        for(int i = 0; i < nums.length; i++){
            Arrays.fill(dp[i],-1);
        }
        return solve(nums,0,-1,dp);
    }
    private int solve(int [] nums, int i, int p,int[][]dp){
        int n = nums.length;
        if(i >= n){
            return 0;
        }
        if(p != -1 && dp[i][p] != -1){
            return dp[i][p];
        }
        int take = 0;
        if(p == -1 || nums[p] < nums[i]){
            take = 1 + solve(nums,i+1, i,dp);
        }
        int skip = solve(nums,i + 1, p,dp);
        if(p != -1){
            dp[i][p] = Math.max(take,skip);
        }
        return Math.max(take,skip);
    }
}