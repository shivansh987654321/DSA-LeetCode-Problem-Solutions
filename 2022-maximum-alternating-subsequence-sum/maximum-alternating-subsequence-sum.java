// class Solution {
//     public long maxAlternatingSum(int[] nums) {
//         int n = nums.length;
//         return solve(0 , nums, true);
//     }

//     public long solve(int idx, int[]nums, boolean flag){
//         int n = nums.length;
//         long[][] t = new long[100001][2];
//         if(idx >= n){
//             return 0;
//         }
//         if(t[idx][flag] != -1){
//             return t[idx][flag];
//         }
//         long skip = solve(idx + 1, nums, flag);
//         int val = nums[idx];
//         if(flag == false){
//             val = -val;
//         }
//         long take = solve(idx + 1, nums, !flag) + val;
//         t[idx][flag] = Math.max(skip,take);
//         return t[idx][flag];
//     }
// }
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int flag = 1; //1= +ve num, 0 = -ve num
        int indx = 0;
        
        long[][] dp = new long[nums.length+1][2]; // in each row we save the value and its flag value
        for(int i=0;i<=nums.length;i++)
            Arrays.fill(dp[i], -1);
        return solve(nums, indx, flag, dp);
    }

    public long solve(int[] nums, int indx, int flag, long[][] dp){
        if(indx>=nums.length)
            return 0;
        if(dp[indx][flag] != -1)
            return dp[indx][flag];
        long skip = solve(nums, indx+1, flag, dp);
        long val = (long)nums[indx];
        if(flag == 0)
            val = -val;
        
        long take = solve(nums, indx+1, 1-flag, dp) + val;

        return dp[indx][flag] = Math.max(take, skip);
    }
}

