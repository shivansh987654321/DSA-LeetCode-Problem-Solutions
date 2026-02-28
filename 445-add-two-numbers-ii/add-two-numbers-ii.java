
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
        ListNode dummy1 = new ListNode (0);
        ListNode temp = dummy1;
        int carry = 0;
        while( l1 != null || l2 != null || carry != 0){
            int sum = carry;
            if( l1 != null){
                sum = sum + l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                sum = sum + l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            int digit = sum % 10;
            temp.next = new ListNode(digit);
            temp = temp.next;
        }
        return reverseLL (dummy1.next);
    }

    public ListNode reverseLL (ListNode head){
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode curr = head;
            ListNode prev = null;
            while(curr != null){
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev; 
        }
}//done