class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        // TC : O(n)
        // SC : O(n)
        List<Integer> list = new ArrayList<>();
        for(ListNode temp = head;temp != null;temp = temp.next){
            list.add(temp.val);
        }
        if(list.reversed().equals(list)){
            // list.reversed() returns a new list(doesnot modify the existing list)
            // .equals() methods is used for : comparing actual values of list 
            return true;
        }
        return false;
    }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        // TC : O(n)
        // SC : O(n)
        // using stack DS 
        Stack<Integer> stack = new Stack<>();
        // store every value in stack
        for(ListNode temp = head;temp != null;temp = temp.next){
            stack.push(temp.val);
        }
        // now, compare from starting if stack's popped values are not same: linked list is not a palindrome
        for(ListNode temp = head;temp != null;temp = temp.next){
            if(temp.val != stack.pop()){
                return false;
            }
        }
        // else, every value matched
        return true;
    }
}

class Solution {
    private boolean isPalindrome(List<Integer> ls){
        int n = ls.size();
        int i = 0;
        int j = n-1;
        while(i < j){
            if (ls.get(i) != ls.get(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public boolean isPalindrome(ListNode head) {
        List<Integer> ls = new ArrayList<>();
        while(head != null){
            ls.add(head.val);
            head = head.next;
        }
        return isPalindrome(ls);
    }
}


class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        // using recursion
        if (head == null || head.next == null)
            return head;//0 or 1 node
        ListNode newHead = reverseList(head.next);//subproblem
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode middle = middleNode(head);
        ListNode newHead = reverseList(middle);
        while (newHead != null && head != newHead) {
            if (head.val != newHead.val)
                return false;
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode temp = slow.next;//store it
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        if (fast != null){
            // odd length
            slow = slow.next;
        }
        while(prev != null && slow != null){
            if (prev.val != slow.val){
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }
}


class Solution {
    ListNode curr;
    public boolean recurr(ListNode head) {
        if (head == null){
            return true;//empty LLs are palindromes
        }
        boolean ans = recurr(head.next);
        if (head.val != curr.val){
            return false;
        }
        else{
            curr = curr.next;
        }
        return ans;
    }
    public boolean isPalindrome(ListNode head){
        curr = head;
        return recurr(head);
    }
}