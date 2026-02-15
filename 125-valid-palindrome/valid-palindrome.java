class Solution {
    public boolean isPalindrome(String s) {
        // //Approach 
        // 1.remove all the non-alphanumeric characters
        // 2.convert all into lower case
        // 3.use two pointers and see these elements are same or not 
        // 4.if yes then its palindrome if not then its not palindrome
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}