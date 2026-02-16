class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxwater = 0;

        for(int i = 0;i < height.length; i++){
            int water = (right - left) * (Math.min(height[left] , height[right]));
            maxwater = Math.max(maxwater , water);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxwater;
    }
}