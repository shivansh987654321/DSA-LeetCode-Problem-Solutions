class Solution {
    public int hammingWeight(int n) {
        int temp = n;
        int count = 0;
        while(temp > 0){
            int val = temp % 2;
            temp = temp / 2;
            if(val == 1){
                count = count + 1;
            }
        }
        return count;
    }
}