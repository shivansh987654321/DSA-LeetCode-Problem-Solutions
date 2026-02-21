import java.util.*;

class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        
        // Edge case
        if (k >= n) return "0";
        
        Stack<Character> stack = new Stack<>();
        
        // Phase 1: Greedy removal while scanning
        for (int i = 0; i < n; i++) {
            char current = num.charAt(i);
            
            while (!stack.isEmpty() && k > 0 && stack.peek() > current) {
                stack.pop();
                k--;
            }
            
            stack.push(current);
        }
        
        // Phase 2: Remove leftover k digits from the end
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        
        // Phase 3: Build result
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        sb.reverse();  // because stack gives reverse order
        
        // Phase 4: Remove leading zeros
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        
        String result = sb.substring(i);
        
        // Phase 5: Handle empty case
        return result.length() == 0 ? "0" : result;
    }
}