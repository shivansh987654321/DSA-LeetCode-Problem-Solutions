class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()){
            return false;
        }
        int [] freq = new int [26];
        int [] freq2 = new int [26];

        for(char c : s1.toCharArray()){
            freq[c - 'a']++;
        }

        int left = 0;
        for(int right = 0 ;right < s2.length() ; right++){
            freq2[s2.charAt(right) - 'a']++;
            if((right - left + 1) > s1.length()){
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }

            if((right - left + 1 ) == s1.length()){
                if(matches(freq , freq2)){
                    return true;
                }
            }
        }
        return false ;
    }
    private boolean matches(int a[] , int b[]){
        for(int i = 0; i< 26 ;i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}