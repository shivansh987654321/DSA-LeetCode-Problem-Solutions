class Solution {
    public void moveZeroes(int[] nums) {
        // first put all non - zero elements at the starting of the array 
        int NonZer0 = 0;
        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] != 0){
                nums[NonZer0] = nums[i];
                NonZer0++;
            }
        }

        // now put all the zeroes after the NonZer0
        for(int i = NonZer0; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}