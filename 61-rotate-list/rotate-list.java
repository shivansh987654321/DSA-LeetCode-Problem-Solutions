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
    public ListNode rotateRight(ListNode head, int k) {
        // Base cases: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Find the length and the old tail
        int length = 1;
        ListNode curr = head;
        while (curr.next != null) {
            length++;
            curr = curr.next;
        }

        // Optimize k
        k = k % length;
        if (k == 0) {
            return head;
        }

        // 2. Make it a circle
        curr.next = head;

        // 3. Find the new tail and break the circle
        ListNode curr2 = head;
        for (int i = 1; i < length - k; i++) {
            curr2 = curr2.next;
        }

        head = curr2.next; // The node after the new tail is the new head
        curr2.next = null; // Break the circle

        return head; // Don't forget to return the result!
    }
}