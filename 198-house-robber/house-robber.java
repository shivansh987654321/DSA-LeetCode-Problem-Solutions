class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);
        int i = 2;
        int ans = solve(dp,nums,n-1);
        return ans;
    }
    
    int solve(int[]dp, int[] nums, int i){
        if(i <= 1){
            return dp[i];
        }
        if(dp[i] != -1){
            return dp[i];
        }
        int option1 = solve(dp, nums, i-1);
        int option2 = solve(dp, nums, i-2) + nums[i];
        int ans = Math.max(option1 , option2);
        dp[i] = ans;
        return dp[i];
    }
}