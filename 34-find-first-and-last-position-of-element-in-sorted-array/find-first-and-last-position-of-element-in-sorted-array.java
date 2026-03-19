class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftans = leftbound(nums, target);
        int rightans = rightbound(nums, target);
        return new int[]{leftans,rightans};
    }

    private int leftbound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                leftans = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return leftans;
    }

    private int rightbound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                rightans = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return rightans;
    }
}