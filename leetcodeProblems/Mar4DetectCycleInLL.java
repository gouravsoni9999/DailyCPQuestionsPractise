import java.util.*;
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class SetSolution {
    public boolean hasCycle(ListNode head) {
        // TC : O(n)
        // SC : O(n)
        // using set DS to mark those nodes which are already visited while traversing
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = head;
        while(temp != null){
            if(visited.contains(temp)){
                return true; // it has cycle, as it has come again while traversing
            }
            visited.add(temp); // add in visited set
            temp = temp.next; // move to next node in LL
        }
        return false; // no loop, as it has been broken (and it is a linear LL)
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        // floyd's tortoise and hare(2 pointer technique)
        // TC : O(n)
        // SC : O(1)
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // as if fast is last node or is null, then the list doesnot have cycle(it is linear)
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}

class RecursiveSolution {
    private boolean solve(ListNode slow, ListNode fast) {
        if (fast == null || fast.next == null)
            return false;// no cycle present
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast)
            return true;
        return solve(slow, fast);
    }

    public boolean hasCycle(ListNode head) {
        // recursive solution
        return solve(head, head);
    }
}
