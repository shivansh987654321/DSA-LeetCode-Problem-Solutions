class Solution {
    public int maxVowels(String s, int k) {
        int n = s.length();
        int max = 0;
        int count = 0;
        for(int i = 0; i<k ; i++){
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                count++;
            }
        }
        max = Math.max(count , max);
        for(int i = k; i < n ;i++){
            if(s.charAt(i-k) ==  'a' ||s.charAt(i-k) == 'e' ||s.charAt(i-k) == 'i' ||s.charAt(i-k) == 'o' || s.charAt(i-k) == 'u'){
                count--;
            }
            if(s.charAt(i) ==  'a' ||s.charAt(i) == 'e' ||s.charAt(i) == 'i' ||s.charAt(i) == 'o' || s.charAt(i) == 'u'){
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}