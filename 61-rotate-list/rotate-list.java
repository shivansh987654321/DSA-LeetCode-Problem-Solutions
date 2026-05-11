
class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }
        // find the length of the whole ll
        int length = 1;
        ListNode curr = head;
        while(curr.next != null){
            length++;
            curr = curr.next;
        }

        // optimise the k
        k = k % length;
        if(k == 0){
            return head;
        }

        // make the ll cicle
        curr.next = head;

        // move to the node where we haev to end 
        ListNode curr2 = head;
        for(int i = 1 ; i < length - k; i++){
            curr2 = curr2.next;
        }
        head = curr2.next;;
        curr2.next = null;

        return head;

    }
}