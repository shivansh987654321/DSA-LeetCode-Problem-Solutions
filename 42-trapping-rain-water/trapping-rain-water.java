class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n-1;

        int [] leftmax = new int [n];
        int [] rightmax = new int [n];


        //step -1 fill the left max array 
        leftmax[0] = height[0];
        for (int i = 1;i < n ;i++){
            leftmax[i] = Math.max(leftmax[i-1] , height[i]);
        }

        //step -2 fill the right max array
        rightmax[n-1] = height[n-1];
        for(int i = n-2; i>=0;i--){
            rightmax[i] = Math.max(rightmax[i+1] , height[i]);
        }

        int water = 0 ;
        for(int i = 0;i < n; i++){
            water = water + Math.min(leftmax[i] , rightmax[i]) - height[i];
        }
        return water;
    }
}