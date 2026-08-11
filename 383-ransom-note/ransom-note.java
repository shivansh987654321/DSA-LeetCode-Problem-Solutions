class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int n = ransomNote.length();
        int m = magazine.length();
        if(n > m){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < m; i++){
            char c = magazine.charAt(i);
            map.put(c , map.getOrDefault(c,0) + 1);
        }

        for(int i = 0; i < n; i++){
            char c = ransomNote.charAt(i);
            if(!map.containsKey(c)){
                return false;
            }
            int value = map.get(c);
            if(value > 0){
                value--;
                map.remove(c);
                map.put(c,value);
            }
            if(value == 0){
                map.remove(c);
            }
        }
        return true;
    }
}