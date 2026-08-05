class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int ans = 0;
        int zeroes = 0;
        int n = nums.length;
        while(right < n){
            if(nums[right] == 0){
                zeroes++;
            }
            while(zeroes > k){
                if(nums[left] == 0){
                    zeroes--;
                }
                left++;
            }
            int len = right - left + 1;
            ans = Math.max(ans,len);
            right++;
        }
        return ans;
    }
}