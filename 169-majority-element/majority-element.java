class Solution {
    public int majorityElement(int[] nums) {
        int ele = -1;
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(count == 0){
                ele = nums[i];
                count++;
            }else if(nums[i] == ele){
                count++;
            }else{
                count--;
            }
        }
        // int temp = 0;
        // for(int i = 0; i < n; i++){
        //     if(nums[i] == ele){
        //         temp++;
        //     }
        // }
        // if(temp > n/2){
        //     return ele;
        //}
        return ele;
    }
}