class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int ele = -1;
        int ele2 = -1;
        int count1 = 0;
        int count2 = 0;
        for(int num : nums){
            if(num == ele){
                count1++;
            }else if(num == ele2){
                count2++;
            }else if(count1 == 0){
                ele = num;
                count1 = 1;
            }else if(count2 == 0){
                ele2 = num;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for(int num : nums){
            if(num == ele){
                count1++;
            }else if(num == ele2){
                count2++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if(count1 > n / 3){
            ans.add(ele);
        }
        if(count2 > n / 3){
            ans.add(ele2);
        }
        return ans;
    }
}