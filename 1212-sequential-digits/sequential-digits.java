class Solution {
    public List<Integer> sequentialDigits(int low, int high) {

        List<Integer> ans = new ArrayList<>();

        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();

        for (int len = lowLen; len <= highLen; len++) {

            for (int start = 1; start <= 10 - len; start++) {

                int num = 0;

                for (int digit = start; digit < start + len; digit++) {
                    num = num * 10 + digit;
                }

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}