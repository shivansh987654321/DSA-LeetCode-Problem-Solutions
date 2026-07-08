class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(int count : map.values()){
            set.add(count);
        }
        if(map.size() != set.size()){
            return false;
        }else{
            return true;
        }
    }
}