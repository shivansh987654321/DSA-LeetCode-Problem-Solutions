class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1]) {
                high = mid;       // peak is on left (including mid)
            } else {
                low = mid + 1;    // peak is on right
            }
        }

        return low; // or high (both same)
    }
}