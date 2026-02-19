class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i<tokens.length ;i++){

            if(tokens[i].equals("+")){
                int a = stack.pop();
                int b = stack.pop();
                int c = b + a;
                stack.push(c);                
            }else if(tokens[i].equals("-")){
                int a = stack.pop();
                int b = stack.pop();
                int c = b - a;
                stack.push(c);                
            }else if(tokens[i].equals("*")){
                int a = stack.pop();
                int b = stack.pop();
                int c = b * a;
                stack.push(c);                
            }else if(tokens[i].equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                int c = b / a;
                stack.push(c);                
            }else{
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}