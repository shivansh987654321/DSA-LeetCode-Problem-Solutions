class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left  = Left(nums , target);
        int right = Right(nums , target);

        return new int[] {left , right};
    }
    private int Left(int[] nums , int target){
            int index = -1;
            int low = 0;
            int high = nums.length - 1;
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(nums[mid] == target){
                    index = mid;
                    high = mid - 1;
                }
                else if (nums[mid] < target){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
            return index;
        }
        private int Right(int[] nums , int target){
            int index = -1;
            int low = 0;
            int high = nums.length - 1;
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(nums[mid] == target){
                    index = mid;
                    low = mid + 1;
                }
                else if (nums[mid] < target){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
            return index;
        }
}