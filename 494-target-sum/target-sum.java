class Solution {
    private static final int MOD = 1000000007;
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];
        }
        target = sum + target;
        if(target % 2 != 0 || target < 0){
            return 0;
        }
        target = target / 2;
        int[][]dp = new int[nums.length][target + 1];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return solve(nums.length - 1, target, nums, dp);
    }
    private int solve(int ind ,int target, int[] nums, int[][]dp){
        if(ind == 0){
            if(target == 0 && nums[0] == 0){
                return 2;
            }
            if(target == 0 || nums[0] == target){
                return 1;
            }
            else{
                return 0;
            }
        }
        if(dp[ind][target] != -1){
            return dp[ind][target];
        }
        int nottake = solve(ind-1,target,nums,dp);
        int take = 0;
        if(target >= nums[ind]){
            take = solve(ind-1, target - nums[ind], nums, dp);
        }
        return dp[ind][target] = (take + nottake) % MOD;
    }
}