class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer[][] dp = new Integer[n][n];
        int aliceScore = solve(piles, 0, n - 1, dp);
        return aliceScore > 0; // Alice jeetegi agar uska score difference positive hai
    }
    
    // returns: is range [left, right] mein, current player (jiska turn hai) 
    // apne aur opponent ke beech kitna "extra" score bana sakta hai (score difference)
    private int solve(int[] piles, int left, int right, Integer[][] dp) {
        if (left > right) return 0; // koi pile nahi bachi
        
        if (dp[left][right] != null) return dp[left][right];
        
        // agar current player left wali pile leta hai:
        // usse piles[left] milta hai, phir opponent ka turn (left+1, right) pe optimal khelega
        // opponent ka best score difference minus karna hoga (kyunki woh dusra player hai)
        int takeLeft = piles[left] - solve(piles, left + 1, right, dp);
        
        // agar current player right wali pile leta hai:
        int takeRight = piles[right] - solve(piles, left, right - 1, dp);
        
        dp[left][right] = Math.max(takeLeft, takeRight);
        return dp[left][right];
    }
}