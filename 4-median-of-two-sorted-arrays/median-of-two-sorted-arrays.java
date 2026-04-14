import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] arr = new int[m + n];

        // Merge nums1
        for (int i = 0; i < m; i++) {
            arr[i] = nums1[i];
        }

        // Merge nums2
        for (int i = 0; i < n; i++) {
            arr[m + i] = nums2[i];
        }

        // Sort merged array
        Arrays.sort(arr);

        int len = arr.length;

        // Median calculation
        if (len % 2 == 0) {
            return (arr[len/2 - 1] + arr[len/2]) / 2.0;
        } else {
            return arr[len/2];
        }
    }
}