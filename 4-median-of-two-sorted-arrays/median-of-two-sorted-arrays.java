class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int p = nums2.length;
        int[] arr = new int[m + p];
        
        for(int i = 0;i < nums1.length;i++){
            arr[i] = nums1[i];
        }
        for(int i = 0;i < nums2.length;i++){
            arr[m + i] = nums2[i];
        }
        Arrays.sort(arr);
        int n = arr.length;
        if(n % 2 == 0){
            return (arr[n/2 - 1] + arr[n/2]) / 2.0;
        }else{
            return arr[n/2];
        }
    }
}