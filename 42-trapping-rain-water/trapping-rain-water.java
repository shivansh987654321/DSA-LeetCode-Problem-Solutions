class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int water = 0;

        while(left <= right){
            if(height[left] <= height[right]){
                if(height[left] >= maxLeft){
                    maxLeft = height[left];
                }else{
                    water = water + (maxLeft - height[left]);
                }
                left++;
            }
            else if (height[left] > height[right]){
                if(height[right] >= maxRight){
                    maxRight = height[right];
                }else{
                    water = water + (maxRight - height[right]);
                }
                right--;
            }
        }
        return water;
    }
}