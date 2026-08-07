class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int ans = 0;
        int zeroes = 0;
        int n = nums.length;
        for(int right = 0; right < n; right++){
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
            ans = Math.max(len,ans);
        }
        return ans;
    }
}