class Solution {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for(int x : nums){
            total = total + x;
        }
        int left = 0;
        for(int i = 0;i < nums.length; i++){
            total = total - nums[i];
            if(total == left) return i;
            left = left + nums[i];
        }
        return -1;
    }
}