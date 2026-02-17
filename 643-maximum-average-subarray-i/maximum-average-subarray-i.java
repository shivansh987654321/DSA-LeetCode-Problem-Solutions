class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int windowsum = 0;
        //step -1 finding the sum of the first window
        for(int i = 0; i<k; i++){
            windowsum = windowsum + nums[i];
        }
        int maxsum = windowsum;
        //step -2 now we slide the window
        for(int i = k;i< n;i++){
            windowsum  = windowsum - nums[i-k] + nums[i];
            maxsum = Math.max(windowsum , maxsum);
        }
        return (double) maxsum/k;
        
    }
}