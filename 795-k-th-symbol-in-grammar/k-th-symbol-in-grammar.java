class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        int parent = kthGrammar(n - 1, (k + 1) / 2);
        boolean isLeftChild = (k % 2 == 1);
        if (parent == 0) return isLeftChild ? 0 : 1;
        else return isLeftChild ? 1 : 0;
    }
}