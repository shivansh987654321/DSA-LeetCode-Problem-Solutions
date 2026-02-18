class Solution {
    public int characterReplacement(String s, int k) {
        int[] arr = new int [26];
        int left = 0;
        int windowsize = 0;
        int maxf = 0;
        for(int right = 0; right < s.length() ; right++){
            arr[s.charAt(right) - 'A']++;
            maxf = Math.max( maxf , arr[s.charAt(right) - 'A']);
            int windowlength = right - left + 1;
            if(windowlength - maxf > k){
                arr[s.charAt(left) - 'A']--;
                left++;
            }
            windowlength = right - left + 1;
            windowsize = Math.max(windowsize , windowlength);
        }
        return windowsize;
    }
}