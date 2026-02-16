class Solution {
    public int maxProfit(int[] prices) {
        int maxP = 0;
        int minSoFar = prices[0];

        for(int i = 0;i<prices.length;i++){
            minSoFar = Math.min(minSoFar , prices[i]);
            int currP = prices[i] - minSoFar;
            maxP = Math.max(currP , maxP);
        }
        return maxP;
    }
}