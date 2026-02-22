class Solution {
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int n = s.length();
        int cn = 0;
        char po = '+';
        for(int i = 0; i< n; i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                cn = cn * 10 + (ch - '0');
            }
            if(!Character.isDigit(ch) && ch != ' ' || i == n-1){
                if(po == '+'){
                    st.push(cn);
                }
                if(po == '-'){
                    st.push(-cn);
                }
                if(po == '*'){
                    st.push(cn * st.pop());
                }
                if(po == '/'){
                    st.push(st.pop() / cn);
                }
                po = ch;
                cn = 0;
            }
        }
        int result = 0;
        while(!st.isEmpty()){
            result  = result + st.pop();
        }
        return result;
    }
}