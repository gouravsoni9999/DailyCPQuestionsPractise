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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        /*
            TC : O(n)
            SC : O(right-left+1)
        */
        int pos = 1;
        ListNode dummy = head;
        Stack<Integer> stack = new Stack<>();
        while(dummy != null){
            if(pos >= left && pos <= right){
                stack.push(dummy.val);
            }
            pos++;
            dummy = dummy.next;
        }

        pos = 1;
        dummy = head;
        while(dummy != null){
            if(pos >= left && pos <= right){
                dummy.val = stack.pop();
            }
            pos++;
            dummy = dummy.next;
        }
        return head;
    }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // TC : O(n)
        // SC : O(1)
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for(int i = 1;i < left;i++){
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for(int i = 1;i <= right-left;i++){
            ListNode temp = prev.next;
            prev.next = curr.next;
            curr.next = curr.next.next;
            prev.next.next = temp;
        }
        return dummy.next;
    }
}
