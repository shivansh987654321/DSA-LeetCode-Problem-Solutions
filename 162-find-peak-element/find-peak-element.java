class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(mid > 0 && mid < nums.length){
                if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]){
                    return mid;
                }
                else if (nums[mid - 1] > nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else if(mid == 0){
                if(nums[0] > nums[1]){
                    return 0;
                }else{
                    return 1;
                }
            }else if(mid == nums.length - 1){
                if(nums[nums.length - 1] > nums[nums.length - 2]){
                    return nums.length - 1;
                }else{
                    return nums.length - 2;
                }
            }
        }
        return left;
    }
}