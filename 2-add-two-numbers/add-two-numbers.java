/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int sum = 0;
        int carry = 0;

        while(l1 != null || l2 != null || carry != 0){
            
            sum = carry;
            if(l1 != null){
                sum = sum + l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum = sum + l2.val;
                l2 = l2.next;
            }
            if(sum > 9){
                carry = sum / 10;
                int digit = sum % 10;
                curr.next = new ListNode(digit);
                curr = curr.next;
            }else{
                carry = 0;
                curr.next = new ListNode(sum);
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}