class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        // step -1 find the first window sum 
        int windowsum = 0;
        for(int i = 0 ;i < k; i++){
            windowsum = windowsum + nums[i];
        }

        //step -2
        int maxsum = windowsum;
        for(int i = k ; i< n; i++){
            windowsum = windowsum - nums[i-k] + nums[i];
            maxsum = Math.max(maxsum , windowsum);
        }
        //step -3 return average
        return (double) maxsum / k;
    }
}