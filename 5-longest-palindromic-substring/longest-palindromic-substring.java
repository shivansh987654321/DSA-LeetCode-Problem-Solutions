class Solution {
    public String longestPalindrome(String s) {
        // Transform: "babad" → "^#b#a#b#a#d#$"
        StringBuilder sb = new StringBuilder("^");
        for(char c : s.toCharArray()){
            sb.append("#").append(c);
        }
        sb.append("#$");
        String t = sb.toString();
        
        int n = t.length();
        int[] p = new int[n];  // palindrome radius array
        int center = 0, right = 0;
        
        for(int i = 1; i < n - 1; i++){
            int mirror = 2 * center - i;
            
            // Mirror property use karo
            if(i < right){
                p[i] = Math.min(right - i, p[mirror]);
            }
            
            // Expand around i
            while(t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)){
                p[i]++;
            }
            
            // Update center if expanded beyond right
            if(i + p[i] > right){
                center = i;
                right = i + p[i];
            }
        }
        
        // Find max in p[]
        int maxLen = 0, centerIdx = 0;
        for(int i = 1; i < n - 1; i++){
            if(p[i] > maxLen){
                maxLen = p[i];
                centerIdx = i;
            }
        }
        
        int start = (centerIdx - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
}