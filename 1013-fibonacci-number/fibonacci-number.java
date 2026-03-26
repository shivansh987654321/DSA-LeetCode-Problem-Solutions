class Solution {
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        int pre = fib(n-1);
        int nex = fib(n-2);

        return (pre + nex);
    }
}