class Solution {
    public String minWindow(String s, String t) {
        int minlength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int n = s.length();
        int m = t.length();
        int[] hash = new int[256];
        int lastindex = -1;
        for(char c : t.toCharArray()){
            hash[c]++;
        }
        int count = 0;
        while(right < n){
            if(hash[s.charAt(right)] > 0){
                count++;
            }
            hash[s.charAt(right)]--;
            while(count == m){
                if(right - left + 1 < minlength){
                    minlength = right - left + 1;
                    lastindex = left;
                }
                hash[s.charAt(left)]++;
                if(hash[s.charAt(left)] > 0){
                    count--;
                }
                left++;
            }
            right++;
        }
        if(lastindex < 0){
            return "";
        }else{
            return s.substring(lastindex,lastindex + minlength);
        }
    }
}