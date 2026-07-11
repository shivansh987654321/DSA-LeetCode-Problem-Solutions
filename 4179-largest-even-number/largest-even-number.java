class Solution {
    public String largestEven(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        if(s.charAt(n-1) == '2'){
            return s;
        }else if(n == 1 && s.charAt(n-1) == '1'){
            return "";
        }
        while(sb.charAt((sb.length())-1) == '1'){
            if(sb.length() > 1){
                sb.deleteCharAt(sb.length() - 1);
            }else{
                return "";
            }
            String str = sb.toString();
            if(str.charAt((str.length())-1) == '2'){
                return str;
            }
        }
        return s;
    }
}