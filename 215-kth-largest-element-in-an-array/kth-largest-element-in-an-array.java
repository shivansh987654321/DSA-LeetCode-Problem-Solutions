class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue <Integer> MinHeap = new PriorityQueue<>();

        int n = nums.length;

        for(int j = 0; j < k; j++){
            MinHeap.add(nums[j]);
        }

        for(int i = k; i<n ;i++){
             if(nums[i]>MinHeap.peek()){
                MinHeap.poll();
                MinHeap.add(nums[i]);
             }
        }
        int o = MinHeap.poll();
        return o;
    }
}