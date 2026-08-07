class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        int left = 0;
        for(int right = 0; right < n; right++){
            map.put(fruits[right] , map.getOrDefault(fruits[right],0)+1);
            while(map.size() > 2){
                int value = map.get(fruits[left]);
                value = value - 1;
                if(value == 0){
                    map.remove(fruits[left]);
                }else{
                    map.put(fruits[left],value);
                }
                left++;
            }
            int len = right - left + 1;
            ans = Math.max(len,ans);
        }
        return ans;
    }
}