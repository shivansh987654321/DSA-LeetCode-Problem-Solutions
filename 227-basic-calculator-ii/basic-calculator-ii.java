import java.util.*;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char previousOperator = '+';
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // Build the current number
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            // If operator OR end of string → process
            if ((!Character.isDigit(ch) && ch != ' ') || i == n - 1) {

                if (previousOperator == '+') {
                    stack.push(currentNumber);
                } 
                else if (previousOperator == '-') {
                    stack.push(-currentNumber);
                } 
                else if (previousOperator == '*') {
                    stack.push(stack.pop() * currentNumber);
                } 
                else if (previousOperator == '/') {
                    stack.push(stack.pop() / currentNumber);
                }

                previousOperator = ch;
                currentNumber = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}