// class Solution {
//     public int[] dailyTemperatures(int[] temperatures) {
//         Stack<Integer> stack = new Stack<>();
//         int n = temperatures.length;
//         int [] result = new int[n];
//         for(int i = n - 1 ; i>=0 ; i--){
//             while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]){
//                 stack.pop();
//             }
//             if(!stack.isEmpty()){
//                 result[i] = stack.peek() - i;
//             }
//             stack.push(i);
//         }
//         return result;
//     }
// }
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();

        for(int i=0; i<temperatures.length;i++){
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int[] tempAndIndex = stack.pop();
                result[tempAndIndex[1]] = i - tempAndIndex[1];
            }
            stack.push(new int[]{temperatures[i], i});
        }

        return result;
    }
}