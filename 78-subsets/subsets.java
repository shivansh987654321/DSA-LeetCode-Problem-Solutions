class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        rec(arr, ans, nums, 0);
        return arr;
    }
    private void rec(List<List<Integer>> arr , List<Integer> ans, int[] nums, int i){
        if(i == nums.length){
            arr.add(new ArrayList<>(ans));
            return;
        }
        rec(arr, ans, nums, i + 1);
        ans.add(nums[i]);
        rec(arr, ans, nums, i + 1);
        ans.remove(ans.size() - 1);

    }
}