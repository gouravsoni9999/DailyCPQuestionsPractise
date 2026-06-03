class Solution {
    public ListNode sortList(ListNode head) {
        // TC : O(nlogn)
        // SC : O(n)
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while(temp != null){
            list.add(temp.val);
            temp = temp.next;
        }
        Collections.sort(list);
        temp = head;
        int i = 0;
        while(temp != null){
            temp.val = list.get(i++);
            temp = temp.next;
        }
        return head;
    }
}

class Solution {
    private ListNode merge(ListNode list1, ListNode list2){
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        // if not : then both are not null
        ListNode head = new ListNode(0);
        if(list1.val <= list2.val){
            head.next = list1;
            list1 = list1.next;
        }
        else{
            head.next = list2;
            list2 = list2.next;
        }
        ListNode nextNode = merge(list1, list2);
        head.next.next = nextNode;
        return head.next;
    }
    private ListNode findMid(ListNode head){
        // modified tortoise and heir algo
        ListNode slow = head;
        ListNode fast = head.next;// modified this for even no. of nodes in LL 
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public ListNode sortList(ListNode head) {
        // TC : O(nlogn)
        // SC : O(1) + O(log(n))(recursion stack-space)
        if(head == null || head.next == null) return head;
        // using Merge Sort : D&C algo
        ListNode middleNode = findMid(head);//O(n)
        ListNode nextToMiddle = middleNode.next;
        middleNode.next = null;
        ListNode leftHead = sortList(head);//-->T(n/2)
        ListNode rightHead = sortList(nextToMiddle);//-->T(n/2)
        return merge(leftHead, rightHead);//-->O(n)
    }
}
