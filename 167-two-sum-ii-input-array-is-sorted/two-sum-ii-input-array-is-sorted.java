class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Approach 
        // Two Sum II – Approach
        // 1.Use two pointers:left=0,right=n-1
        // 2.While(left<right):
        // sum=numbers[left]+numbers[right]
        // 3.If sum==target return[left+1,right+1]
        // 4.If sum<target left++
        // 5.If sum>target right--
        // Time:O(n)
        // Space:O(1)



        int left = 0;
        int right = numbers.length - 1;

        while (left < right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[] {left + 1 , right + 1};
            }
            
            else if(sum < target){
                left++;
            }

            else{
                right--;
            }
        }
        return new int[] {-1,-1};
    }
}