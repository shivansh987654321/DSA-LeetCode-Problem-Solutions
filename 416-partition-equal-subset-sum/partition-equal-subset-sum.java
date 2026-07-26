class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];
        }
        if(sum % 2 != 0){
            return false;
        }
        int[][] dp = new int[nums.length][(sum/2) + 1];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return solve(nums.length - 1, sum/2, nums,dp);
    }
    private boolean solve(int ind, int sum1, int [] nums, int[][] dp){
        if(sum1 == 0){
            return true;
        }
        if(ind == 0){
            return  nums[ind] == sum1;
        }
        if(dp[ind][sum1] != -1){
            if(dp[ind][sum1] == sum1){
                return true;
            }else{
                return false;
            }
        }
        boolean nottake = solve(ind-1, sum1, nums, dp);
        boolean take = false;
        if(sum1 >= nums[ind]){
            take = solve(ind-1,sum1-nums[ind],nums,dp);
        }
        if(take || nottake){
            dp[ind][sum1] = 1;
        }else{
            dp[ind][sum1] = 0;
        }
        return take || nottake;
    }
}