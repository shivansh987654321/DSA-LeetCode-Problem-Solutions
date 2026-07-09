
class Solution {

    public int vowelCount(String word) {
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }

        return count;
    }

    public String reverseWords(String s) {
        String[] words = s.split(" ");

        int target = vowelCount(words[0]);

        // Start from the SECOND word
        for (int i = 1; i < words.length; i++) {
            if (vowelCount(words[i]) == target) {
                words[i] = new StringBuilder(words[i]).reverse().toString();
            }
        }

        return String.join(" ", words);
    }
}