class Solution {
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null)
            return head;
        
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    private ListNode removeFirstNode(ListNode newHead){
        return newHead.next;
    }

    private ListNode removeNode(ListNode newHead,int n){
        if(n == 1){
            return removeFirstNode(newHead);
        }
        // remove from start
        ListNode curr = newHead;
        ListNode prev = newHead;
        while(--n > 0){
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        return newHead;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // TC : O(n) here n means no. of nodes
        // SC : O(1) + O(n) recursion stack space
        // reverse the linked list
        ListNode newHead = reverse(head);
        // remove nth node from starting
        newHead = removeNode(newHead, n);
        // return reversed linked list
        return reverse(newHead);
    }
}

class Solution {
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null)
            return head;
        
        ListNode prev = null;
        ListNode next;
        for(ListNode curr = head;curr != null;curr = next){
            next = curr.next;
            curr.next = prev;
            prev = curr;
        }
        return prev;
    }

    private ListNode removeFirstNode(ListNode newHead){
        return newHead.next;
    }

    private ListNode removeNode(ListNode newHead,int n){
        if(n == 1){
            return removeFirstNode(newHead);
        }
        // remove from start
        ListNode curr = newHead;
        ListNode prev = newHead;
        while(--n > 0){
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        return newHead;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // TC : O(n) here n means no. of nodes
        // SC : O(1)
        // reverse the linked list
        ListNode newHead = reverse(head);
        // remove nth node from starting
        newHead = removeNode(newHead, n);
        // return reversed linked list
        return reverse(newHead);
    }
}

class Solution {
    private int findSize(ListNode head){
        int size = 0;
        while(head != null){
            size++;
            head = head.next;
        }
        return size;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = findSize(head);
        int removeIdx = size-n;
        ListNode prev = head;
        ListNode curr = head;
        if (removeIdx == 0) return head.next;//if node to delete is 1st node
        while(removeIdx != 0){
            prev = curr;
            curr = curr.next;
            removeIdx--;
        }
        prev.next = curr.next;
        return head;
    }
}
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1-pass solution
        ListNode curr = head;
        ListNode prev = head;
        for(int i = 0;i<n;i++){
            curr = curr.next;
        }
        if (curr == null) return head.next;
        while(curr!=null && curr.next!=null){
            prev = prev.next;
            curr = curr.next;
        }
        prev.next = prev.next.next;
        return head;
    }
}
