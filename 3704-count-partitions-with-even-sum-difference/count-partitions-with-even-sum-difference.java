class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n - 1; i++){
            int leftsum = sum(0,i,nums);
            int rightsum = sum(i+1, n - 1, nums);
            int temp = Math.abs(leftsum - rightsum);
            if(temp % 2 == 0){
                count++;
            }
        }
        return count;
    }
    private int sum (int left, int right,int[] nums){
        int v = 0;
        while(left <= right){
            v= v + nums[left];
            left++;
        }
        return v;
    }
}