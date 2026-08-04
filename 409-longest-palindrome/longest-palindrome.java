class Solution {
    public int longestPalindrome(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            char c  = s.charAt(i);
            map.put(c,map.getOrDefault(c,0) + 1);
        }
        int ans = 0;
        boolean odd = false;
        for(int v : map.values()){
            if(v % 2 == 0){
                ans = ans + v;
            }else{
                ans = ans + v - 1;
                odd = true;
            }
        }
        if(odd){
            return ans + 1;
        }else{
            return ans;
        }
    }
}