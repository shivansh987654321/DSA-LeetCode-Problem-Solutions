class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer , Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        int count = 0;
        for(int i = 0; i < n; i++){
            sum = sum + nums[i];
            int need = sum - k;
            if(map.containsKey(need)){
                int value = map.get(need);
                count = count + value;
            }
            if(map.containsKey(sum)){
                int temp = map.get(sum);
                temp++;
                map.remove(sum);
                map.put(sum , temp);
            }else{
                map.put(sum , 1);
            }
        }
        return count;
    }
}