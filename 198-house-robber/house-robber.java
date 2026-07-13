
//Approach -2 using constant spcae
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }
        int prev = nums[0];
        int prevPrev = 0;

        for(int i = 2; i <= n; i++){
            int take = nums[i-1] + prevPrev;
            int skip = prev;
            int temp = Math.max(take,skip);
            prevPrev = prev;
            prev = temp;
        }
        return prev;
    }
}
