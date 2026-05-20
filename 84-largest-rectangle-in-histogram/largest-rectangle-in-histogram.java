class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            if(st.isEmpty()){
                ans[i] = n;
            }else{
                ans[i] = st.peek();
            }
            st.push(i);
        }
        int[] ans2 = new int[n];
        Stack<Integer> st2 = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!st2.isEmpty() && heights[st2.peek()] >= heights[i]){
                st2.pop();
            }
            if(st2.isEmpty()){
                ans2[i] = -1;
            }else{
                ans2[i] = st2.peek();
            }
            st2.push(i);
        }
        int max = 0;
        for(int i = 0; i < n; i++){
            int curr = heights[i] * (ans[i] - ans2[i] - 1);
            max = Math.max(max , curr);
        }
        return max;
    }
}