class Solution {
    public String reverseWords(String s) {
        s = s.trim().replaceAll("\\s+", " ");
        char[] arr = s.toCharArray();
        int low = 0;
        int high = s.length() - 1;
        reverse(arr, low , high);
        int start = 0;
        for (int end = 0; end <= s.length(); end++) {
            if (end == s.length() || arr[end] == ' ') {
                reverse(arr, start, end - 1);
                start = end + 1;
            }
        }
        return new String(arr);
    }

    void reverse(char[] arr, int low, int high) {
        while (low < high) {
            char temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }
}