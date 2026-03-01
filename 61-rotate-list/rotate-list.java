
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0){
            return head;
        }
        int length = 1;
        ListNode curr = head;
        while(curr.next != null){
            length++;
            curr = curr.next;
        }
        k = k % length;
        if (k == 0) {
            return head;
        }
        curr.next = head;
        ListNode curr2 = head;
        for (int i = 1 ; i < length - k; i++){
            curr2 = curr2.next;
        }
        head = curr2.next;
        curr2.next = null;
        return head;
    }
}