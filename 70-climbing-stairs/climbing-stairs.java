class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        if (n == 0) {
            return 0;
        }
        dp[0] = 1;
        dp[1] = 1;
        int ans = solve(n, dp);
        return ans;
    }

    int solve(int n, int[] dp) {
        if (n <= 1) {
            return dp[n];
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
        return dp[n];
    }
}