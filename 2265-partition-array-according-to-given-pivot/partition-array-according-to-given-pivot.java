class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] arr = new int[n];
        int a = 0;
        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] < pivot){
                
                arr[a] = nums[i];
                a++;
            }
        }
        int b = a;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == pivot){
                arr[b] = nums[i];
                b++;
            }
        }
        int c = b;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > pivot){
                arr[c] = nums[i];
                c++;
            }
        }
        return arr;
    }
}