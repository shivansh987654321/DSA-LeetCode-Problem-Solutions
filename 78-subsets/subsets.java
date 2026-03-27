class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ans1 = new ArrayList<>();
        sub(ans, ans1, nums, 0);
        return ans;
    }

    private void sub(List<List<Integer>> ans, List<Integer> ans1, int[] nums, int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(ans1));
            return;
        }

        // not pick
        sub(ans, ans1, nums, i + 1);

        // pick
        ans1.add(nums[i]);
        sub(ans, ans1, nums, i + 1);

        // backtrack
        ans1.remove(ans1.size() - 1);
    }
}