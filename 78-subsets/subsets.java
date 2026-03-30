class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        //return arr;
        rec(nums , arr , ans , 0);
        return arr;
    }
    private void rec(int[] nums , List<List<Integer>> arr ,List<Integer> ans , int i){
        if(i == nums.length){
            arr.add(new ArrayList<>(ans));
            return;
        }
        
        rec(nums , arr , ans, i + 1);
        ans.add(nums[i]);
        rec(nums , arr , ans, i + 1);
        ans.remove(ans.size() - 1);
    }
}