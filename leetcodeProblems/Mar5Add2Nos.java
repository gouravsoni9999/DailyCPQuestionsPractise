class Solution {
    private long findNum(ListNode curr){
        int i = 0;
        long num = 0;
        while (curr != null) {
            num += curr.val * Math.pow(10, i);
            i++;
            curr = curr.next;
        }
        return num;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	// this code not working for out of long ranged nos.
        long num1 = findNum(l1);
        long num2 = findNum(l2);
        long sum = num1 + num2;
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(sum);
        ListNode head;
        int rem = (int)(sum % 10);
        System.out.println(rem);
        sum = sum / 10;
        System.out.print(sum);
        ListNode curr = new ListNode(rem);
        head = curr;
        while (sum != 0) {
            rem = (int)(sum % 10);
            sum = sum / 10;
            curr.next = new ListNode(rem);
            curr = curr.next;
        }
        return head;
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {//O(max(m,n))
        ListNode curr;
        ListNode head;
        int rem = 0;
        int sum = l1.val + l2.val;
        curr = new ListNode(sum % 10);
        rem = sum / 10;
        head = curr;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null && l2 != null) {
            sum = (l1.val + l2.val + rem);
            curr.next = new ListNode(sum % 10);
            rem = sum / 10;
            curr = curr.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            sum = (l1.val + rem);
            rem = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            sum = (l2.val + rem);
            rem = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            l2 = l2.next;
        }
        while (rem != 0) {
            curr.next = new ListNode(rem % 10);
            rem = rem / 10;
            curr = curr.next;
        }
        return head;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // TC : O(max(n1,n2))
        // SC : O(max(n1,n2))
        ListNode res = new ListNode(-1);
        ListNode itr = res;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0){
            int sum = 0;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            } 
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            } 
            sum += carry;
            itr.next = new ListNode(sum%10);
            carry = (int) sum/10;
            itr = itr.next;
        }
        return res.next;
    }
}