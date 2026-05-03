class Solution {
    public double myPow(double x, int n) {
        double ans = 1;
        long N = n;
        
        // if n is negative then reciprocal it with 1
        if(N < 0){
            x = 1 / x;
            N = - N;
        }
        
        // if n is zero then the ans must be 1 
        if(N == 0){
            return 1;
        }

        //for even x = x * x and for odd ans = ans * x
        while(N > 0){
            if(N % 2 == 1){    // for odd
                ans = ans * x;
            }

            x = x * x;  // for even
            N = N / 2;  // for even

        }
        return ans;
    }
}