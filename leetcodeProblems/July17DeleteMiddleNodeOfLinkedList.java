class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}

class MiddleSolution{
    public ListNode deleteMiddle(ListNode head) {
        // TC : O(n)
        // SC : O(1)
        if (head.next == null)
            return null;

        int n = 0;

        for (ListNode temp = head; temp != null; temp = temp.next) {
            n++;
        }

        int prevIdx = (int) Math.floor(n / 2);

        ListNode temp = head;

        while(temp != null){
            prevIdx--;
            if(prevIdx == 0){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }

        return head;
    }
} 

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // using Tortoise and Hare Algo
        // TC : O(n)
        // SC : O(1)
        if(head.next == null)
            return null;
        // delete the first middle of LL
        ListNode prev = head; // previous of middle
        ListNode slow = head; // will stop at middle
        ListNode fast = head;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = prev.next.next;
        return head;
    }
}