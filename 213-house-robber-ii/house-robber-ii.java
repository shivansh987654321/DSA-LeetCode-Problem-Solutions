// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length;
//         if(n == 1){
//             return nums[0];
//         }
//         int[] dp = new int[n];
//         Arrays.fill(dp , -1);
//         dp[0] = nums[0];
//         dp[1] = Math.max(dp[0], nums[1]);
//         int i = 2;
//         return solve(nums,dp,n-1);
//     }
//     int solve(int[] nums , int[]dp ,int i){
//         if(i <= 1){
//             return dp[i];
//         }
//         if(dp[i] != -1){
//             return dp[i];
//         }
//         int option1 = solve(nums,dp,i-1);
//         int option2 = solve(nums,dp,i-2) + nums[i];
//         dp[i] = Math.max(option1,option2);
//         return dp[i];
//     }
// }
class Solution {
    public int rob(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        // Case 1: Rob from 0 to n-2
        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        int ans1 = solve(nums, dp1, n - 2, 0);

        // Case 2: Rob from 1 to n-1
        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);
        dp2[1] = nums[1];
        if (n > 2) {
            dp2[2] = Math.max(nums[1], nums[2]);
        }
        int ans2 = solve(nums, dp2, n - 1, 1);

        return Math.max(ans1, ans2);
    }

    int solve(int[] nums, int[] dp, int i, int start) {

        if (i == start) {
            return dp[i];
        }

        if (i == start + 1) {
            return dp[i];
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int option1 = solve(nums, dp, i - 1, start);
        int option2 = solve(nums, dp, i - 2, start) + nums[i];

        dp[i] = Math.max(option1, option2);

        return dp[i];
    }
}