class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        
        if ((long) m*k>bloomDay.length){
            return -1;
        }
        int low = bloomDay[0]; int high = bloomDay[0];
        for (int i=0; i<bloomDay.length; i++){
            if (bloomDay[i]<low){
                low = bloomDay[i];
            } else if (bloomDay[i]>high){
                high = bloomDay[i];
            }
        }

        int answer = -1;
        
        while (low<=high){
            int mid = low + (high-low)/2;
            if (isTrue(bloomDay, m, k, mid)){
                answer = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return answer;
    }

    private boolean isTrue(int[] bloomDay, int m, int k, int mid){
        int counter = 0; int bouquet = 0;
        for (int i=0; i<bloomDay.length; i++){
            if (bloomDay[i]<=mid){
                counter++;
                if (counter==k){
                    bouquet++;
                    counter=0;
                }
            } else {
                counter = 0;
            }
        }
        if (bouquet<m){
            return false;
        } else if (bouquet==m) {
            return true;
        }
        return true;
    }
}