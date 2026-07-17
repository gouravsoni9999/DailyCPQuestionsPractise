import java.util.*;
class ExtraSpaceSolution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return head;
        // brute-force approach
        // TC : O(n)
        // SC : O(n)
        List<Integer> list = new ArrayList<>();

        for(ListNode temp = head;temp != null;){
            list.add(temp.val);
            temp = (temp.next != null) ? temp.next.next : null;
        }

        for(ListNode temp = head.next; temp != null;){
            list.add(temp.val);
            temp = (temp.next != null) ? temp.next.next : null;
        }

        int i = 0;
        for(ListNode temp = head;temp != null;temp = temp.next){
            temp.val = list.get(i++);
        }

        return head;
    }
}

class ConstantSpaceSolution {
    public ListNode oddEvenList(ListNode head) {
        // TC : O(n)
        // SC : O(1) since we are only doing link changes
        if(head == null || head.next == null || head.next == null)
            return head;

        ListNode evenHead = head.next;
        ListNode even = head.next;

        ListNode odd = head;

        while(even != null && even.next != null){
            // since odd is always ahead of even, no need to check conditions for odd
            odd.next = odd.next.next;
            even.next = even.next.next;
            
            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}