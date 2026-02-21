class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        Stack<Character> stack = new Stack<>();
        if (k >= n) return "0";

        for(int i = 0;i < num.length(); i++){
            while(!stack.isEmpty() && k > 0 && stack.peek() > num.charAt(i)){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }

        while( k > 0 && !stack.isEmpty()){
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        sb.reverse();
        int i = 0;
        while(i < sb.length() && sb.charAt(i) == '0'){
            i++;
        }
        String result = sb.substring(i);
        return result.length() == 0 ? "0" : result;
    }
}