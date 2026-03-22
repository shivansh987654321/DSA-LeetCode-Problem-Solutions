class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for(int w : weights){
            left = Math.max(left , w);
            right = right + w;
        }
        while(left < right){
            int mid = left + (right - left) / 2;
            if(possible(weights , days , mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean possible(int [] weights , int daysallowed , int capacity){
        int days = 1;
        int current = 0;
        for(int w : weights){
            if(current + w <= capacity){
                current = current + w;
            }else{
                days++;
                current = w;
            }
        }
        return (days <= daysallowed);
    }
}