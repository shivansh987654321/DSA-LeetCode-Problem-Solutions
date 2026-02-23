
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode LeftPrev = dummy;
        ListNode curr = head;
        for(int i = 0; i < left - 1 ; i++){
            LeftPrev = LeftPrev.next;
            curr = curr.next;
        }

        ListNode SubListHead = curr;
        ListNode prev = null;
        for(int i = 0; i < right - left + 1; i++){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        LeftPrev.next = prev;
        SubListHead.next = curr;
        return dummy.next;
    }
}