class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int minsofar = nums[0];
        int maxsofar = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxsofar;
                maxsofar = minsofar;
                minsofar = temp;
            }
            maxsofar = Math.max(nums[i] , maxsofar * nums[i]);
            minsofar = Math.min(nums[i] , minsofar * nums[i]);
            result = Math.max(result , maxsofar);
        }
        return result;
    }
}