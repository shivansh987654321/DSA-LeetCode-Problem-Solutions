
class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.next;
            count++;
        }
        int mid = count / 2;
        // if(count % 2 == 0){
        //     mid = mid + 1;
        // }
        ListNode temp = head;
        for(int i = 0; i < mid; i++){
            temp = temp.next;
        }
        return temp;
    }
}