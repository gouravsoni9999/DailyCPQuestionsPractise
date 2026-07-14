
// Definition for singly-linked list.
 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
public class July14FindMiddleNodeOfLinkedList {
    private int findLength(ListNode head) {
        int n = 0;
        while(head != null){
            head = head.next;
            n++;
        }
        return n;
    }
    public ListNode middleNode(ListNode head) {
        int n = findLength(head);
        int midNode = (int)n/2;
        ListNode temp = head;
        while(midNode-- > 0){
            temp = temp.next;
        }
        return temp;
    }
}

class Solution {
    public ListNode middleNode(ListNode head) {
        // using Tortoise-Hair Algo
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}