
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int start = 0;
        int end = Arrays.stream(bloomDay).max().getAsInt();
        int min = -1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(Get_Min_Days(bloomDay , mid , k) >= m){
                min = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return min;
    }

    private int Get_Min_Days(int [] bloomday , int mid , int k){
        int number = 0;
        int count = 0;
        for(int i = 0; i < bloomday.length; i++){
            if(bloomday[i] <= mid){
                count++;
            }else{
                count = 0;
            }
            if(count == k){
                number++;
                count = 0;
            }
        }
        return number;
    }
}