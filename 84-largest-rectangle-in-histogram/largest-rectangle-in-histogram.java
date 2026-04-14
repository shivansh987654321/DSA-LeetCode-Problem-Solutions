class Solution {
    public int largestRectangleArea(int[] heights) {
        
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            int h;
            if (i == n) {
                h = 0;
            } else {
                h = heights[i];
            }

            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];

                int right = i;
                int left;

                if (stack.isEmpty()) {
                    left = -1;
                } else {
                    left = stack.peek();
                }

                int width = right - left - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}