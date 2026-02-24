// step -1 Check the list is empty or not  && check if right and left are at same index 
// step -2 Make a dummy node and traverse the list upto just before the left 
// step -3 Reverse the LinkedList upto right from left 
// step -4 connect the node that was just before the left to the head of the reversed linked list 
// step -5 connect the tail of the reversed linked list to the node just after the right

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null ||right == left ){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevhead = dummy;
        ListNode curr = head;

        for(int i = 0; i< left - 1; i++){
            
            curr = curr.next;
            prevhead = prevhead.next;
        }

        ListNode sublisthead = curr;
        ListNode prev = null;
        for(int i = 0; i< right - left + 1; i++){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        prevhead.next = prev;
        sublisthead.next = curr;
        return dummy.next;

    }
}





















