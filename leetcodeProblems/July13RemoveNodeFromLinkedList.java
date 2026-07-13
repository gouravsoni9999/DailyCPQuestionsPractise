// Definition for singly-linked list.
public class July13RemoveNodeFromLinkedList {
    int val;
    ListNode next;

    July13RemoveNodeFromLinkedList(int x) {
        val = x;
    }
}

class Solution {
    public void deleteNode(ListNode node) {
        // TC : O(n)
        // SC : O(1)
        ListNode next = node.next; // always next of node
        // shift the data of nodes to the left
        while (next.next != null) { // move until last node is next
            node.val = next.val; // assign prev node's value to next node's value
            // move both pointers simultaneously
            node = node.next;
            next = next.next;
        }
        // at last overwrite : node is at 2nd last node and next is at last node
        node.val = next.val;
        // delete the last node
        node.next = null; // assign 2nd last node's next ptr to null to delete the last node
    }
}

class Solution {
    public void deleteNode(ListNode node) {
        // TC : O(1)
        // SC : O(1)
        // optimized approach
        ListNode nxt = node.next;

        // first overwrite the data present in this node with next node's val
        node.val = nxt.val;

        // now, link node -> nxt.next
        node.next = nxt.next; // which deletes nxt automatically by garbage collector
    }
}