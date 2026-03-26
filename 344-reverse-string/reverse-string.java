class Solution {
    public void reverseString(char[] s) {
        temp(s , 0 , s.length - 1);
    }
    private void temp(char[] s , int i, int j){
        if(i >= j){
            return;
        }
        char temp1 = s[i];
        s[i] = s[j];
        s[j] = temp1;
        temp(s , i + 1 , j - 1);
    }
}