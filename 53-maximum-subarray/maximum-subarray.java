class Solution {
    public int maxSubArray(int[] nums) {
        int currsum = nums[0];
        int n = nums.length;
        int maxsum = nums[0];
        for(int i = 1; i < n ;i ++){
             currsum = Math.max(nums[i] ,nums[i] + currsum );
             maxsum = Math.max(currsum , maxsum);
        }
        return maxsum;
    }
}