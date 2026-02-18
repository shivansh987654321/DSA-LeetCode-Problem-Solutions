class Solution {
    public boolean checkInclusion(String s1, String s2) {
    // step -1 we will count the frquency of the characters in the String s1
    // step -2 then in the string 2 we will find the contigious subarray that contain the same frequency of characters in String -1 
        if(s1.length() > s2.length()){
            return false;
        }
        int [] farr = new int [26];
        int [] window = new int [26];

        for( char c : s1.toCharArray()){
            farr[c - 'a']++;
        }
        int left = 0;
        for(int right  = 0; right < s2.length() ; right++){
            window[s2.charAt(right) - 'a']++;
            if(right - left + 1 > s1.length()){
                window[s2.charAt(left) - 'a']--;
                left++;
            }

            if((right - left + 1) == s1.length()){
                if(matches(farr , window)){
                    return true ;
                }
            }
        }
        return false;
    }

    private boolean matches(int[] a , int[] b){
        for(int i = 0;i < 26; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}