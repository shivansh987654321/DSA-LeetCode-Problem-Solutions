class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        int count = 0;

        //step - 1 creating the first window 
        int windowsum = 0;
        double dwindowsum = 0.0;
        double dwwindowsum = 0.0;
        for(int i = 0;i< k;i++){
            windowsum = windowsum + arr[i];
            dwwindowsum = windowsum/k;
        }
        if(dwwindowsum >= threshold){
                count++;
        }

        // sliding the window and finding the count of the subarrays
        for(int i = k;i<n; i++){
            windowsum = windowsum - arr[i-k] + arr[i];
            dwindowsum = windowsum / k;
            //maxsum = Math.max(windowsum , maxsum);
            if(dwindowsum >= threshold){
                count++;
            }
        }
        return count;
    }
}