class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int left = 0;
        int ans = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int right = 0; right < n; right++){
            map.put(fruits[right], map.getOrDefault(fruits[right],0) + 1);

            while(map.size() > 2){
                int value = map.get(fruits[left]);
                value = value - 1;
                if(value == 0){
                    map.remove(fruits[left]);
                } else {
                    map.put(fruits[left], value);
                }
                left++;
            }
            int len = right - left + 1;
            ans = Math.max(len,ans);
        }
        return ans;
    }
}