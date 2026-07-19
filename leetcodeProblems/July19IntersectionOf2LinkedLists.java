import java.util.*;
public class July19IntersectionOf2LinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // TC : O(n1 + n2)
        // SC : O(n1)
        // using set DS
        Set<ListNode> set = new HashSet<>();
        // store in set every node of LL A
        for(ListNode curr = headA;curr != null;curr = curr.next){
            set.add(curr);
        }

        // traverse in 2nd linked list
        for(ListNode curr = headB;curr != null;curr = curr.next){
            if(set.contains(curr))
                return curr; // if exists, return node
        }

        return null; // not existing, return null
    }
}

class Solution {
    private int findLength(ListNode head){
        int n = 0;
        while(head != null){
            head = head.next;
            n++;
        }
        return n;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // using traversal only
        // TC : O(n1 + n2)
        // SC : O(1)
        int n1 = findLength(headA);
        int n2 = findLength(headB);

        ListNode temp1 = headA;
        ListNode temp2 = headB;
        if(n1 > n2){
            int diff = n1-n2;
            while(diff-- > 0){
                temp1 = temp1.next; // move both the pointers to the same vertical level
            }
        }else if(n1 < n2){
            int diff = n2-n1;
            while(diff-- > 0){
                temp2 = temp2.next; // move both the pointers to the same vertical level
            }
        }
        
        // traverse both iterators until 1st intersection node is found out
        while(temp1 != null && temp2 != null){
            if(temp1 == temp2)
                return temp1; // found the intersection node
            temp1 = temp1.next; temp2 = temp2.next;
        }

        return null; // no intersection point
    }
}

class OptimalSolution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // TC : O(n1 + n2)
        // SC : O(1)
        // edge case, any one of them is null
        if(headA == null || headB == null)
            return null; // no intersection point

        ListNode temp1 = headA, temp2 = headB;
        while(temp1 != temp2){
            // while loop condition : temp1 == temp2 : first node check
            temp1 = temp1.next;
            temp2 = temp2.next;

            if(temp1 == temp2){
                // if both null, means no intersection point : that is also handled
                return temp1;
            }
        
            if(temp1 == null){
                temp1 = headB;
            }
            if(temp2 == null){
                temp2 = headA;
            }
        }

        return temp1;
    }
}