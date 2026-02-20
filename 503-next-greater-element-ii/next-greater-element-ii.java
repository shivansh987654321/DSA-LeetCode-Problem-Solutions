class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int [] ans = new int[n];
        Arrays.fill(ans,-1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 2 * n - 1; i>=0 ;i--){
            while(!stack.isEmpty() &&  nums[stack.peek()] <= nums[i%n]){
                stack.pop();
            }
            if(i < n){
                if(!stack.isEmpty()){
                    ans[i % n] = nums[stack.peek()];
                }
            }
            stack.push(i % n);
        }
        return ans;
    }   
}