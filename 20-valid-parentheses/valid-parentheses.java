// class Solution {
//     public boolean isValid(String s) {
//         Stack <Character> stack = new Stack<>();
        
//         for(int i = 0; i < s.length(); i++){
//             if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{' ){
//                 stack.push(s.charAt(i));
//             }
//             else if(stack.isEmpty()){
//                 return false;
//             }else if (s.charAt(i) == ')' && stack.peek() != '(' || s.charAt(i) == ']' && stack.peek() != '[' ||s.charAt(i) == '}' && stack.peek() != '{'){
//                 return false;
//             }else{
//                 stack.pop();
//             }
//         }
//         return stack.isEmpty();
//     }
// }
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character>st=new Stack<>();

        for(char ch:s.toCharArray()){
            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
            } 
            else{
                if(st.isEmpty()) return false;

                char top=st.pop();

                if((ch==')' && top!='(') ||
                    (ch=='}' && top!='{') ||
                    (ch==']' && top!='[')) {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }
}