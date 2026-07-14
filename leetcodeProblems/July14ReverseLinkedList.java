import java.util.*;
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class July14ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        // using stack DS
        // TC : O(n)
        // SC : O(n)
        Stack<Integer> stack = new Stack<>();
        for (ListNode temp = head; temp != null; temp = temp.next) {
            stack.push(temp.val);
        }
        for (ListNode temp = head; temp != null; temp = temp.next) {
            temp.val = stack.pop();
        }
        return head;
    }
}

class IterativeSolution {
    public ListNode reverseList(ListNode head) {
        // iterative approach
        // TC : O(n)
        // SC : O(1)
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            // next node should be stored
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr; // prev becomes the curr node
            curr = next; // in next iteration, process the next node which was stored
        }
        return prev; // the new head
    }
}

class RecursiveSolution {
    public ListNode reverseList(ListNode head) {
        // TC : O(n)
        // SC : O(n) recursive stack space
        if(head == null || head.next == null){
            // if a LL has 0 or 1 node : already reversed
            return head;
        }
        // using recursion
        ListNode front = head.next; // next node of head
        ListNode newHead = reverseList(front); // recursively call for smaller subproblem
        // move pointers to make it actually reversed
        front.next = head;
        head.next = null;
        return newHead; // this is our new head now
    }
}