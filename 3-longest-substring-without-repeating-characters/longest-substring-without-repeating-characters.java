import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            
            // If character already seen, move left pointer
            if (map.containsKey(current)) {
                left = Math.max(left, map.get(current) + 1);
            }
            
            // Update character's latest index
            map.put(current, right);
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
