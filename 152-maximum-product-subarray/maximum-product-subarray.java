class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }

        int minsofar = nums[0];
        int maxsofar = nums[0];
        int result = nums[0];
        for(int i = 1; i < n; i++){
            if(nums[i] < 0){
                int temp = maxsofar;
                maxsofar = minsofar;
                minsofar = temp;
            }
            maxsofar = Math.max(nums[i] , nums[i] * maxsofar);
            minsofar = Math.min(nums[i] , nums[i] * minsofar);
            result = Math.max(result , maxsofar);
        }
        return result;
    }
}