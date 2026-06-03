class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> li = new ArrayList<>();
        List<Integer> li1 = new ArrayList<>();
      
        helper(li ,li1,  nums, 0);
        return li;
    }
    public void helper(List<List<Integer>> li ,List<Integer> li1 , int[] nums, int i){
        if(i == nums.length){
            li.add(new ArrayList<>(li1));
            return;
        }
        helper(li, li1, nums, i+1);
        li1.add(nums[i]);
        helper(li,li1,nums,i+1);
        li1.remove(li1.size() -1);
    }
}