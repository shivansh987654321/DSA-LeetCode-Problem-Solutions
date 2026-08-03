// class Solution {
//     public int maxScore(int[] cardPoints, int k) {
//         int n = cardPoints.length;
//         int max = 0;
//         for(int i = 0; i <= k; i++){
//             int sum = 0;
//             int j = k - i;
//             for(int left = 0; left < i; left++){
//                 sum = sum + cardPoints[left];
//             }
//             for(int right = n - j; right < n; right++){
//                 sum = sum + cardPoints[right];
//             }
//             max = Math.max(max, sum);
//         }
//         return max;
//     }
// }

class Solution {
    /* Function to calculate the maximum
    score after picking k cards */
    public int maxScore(int[] cardPoints, int k) {
        int lSum = 0, rSum = 0, maxSum = 0;

        // Calculate the initial sum of the first k cards
        for (int i = 0; i < k; i++) {
            lSum += cardPoints[i];
            
            /* Initialize maxSum with the
            sum of the first k cards */
            maxSum = lSum;
        }

        // Initialize rightIndex to iterate array from last
        int rightIndex = cardPoints.length - 1;
        
        for (int i = k - 1; i >= 0; i--) {
            
            // Remove the score of the ith card from left sum
            lSum -= cardPoints[i];   
            
            /* Add the score of the card
            from the right to the right sum */
            rSum += cardPoints[rightIndex];  
            
            // Move to the next card from the right
            rightIndex--; 

            // Update maxSum with the maximum sum found so far
            maxSum = Math.max(maxSum, lSum + rSum);
        }

        // Return the maximum score found
        return maxSum; 
    }
}
