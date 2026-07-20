/* Structure of linked list Node
class Node {
	int data;
	Node next;
	
	Node(int x) {
		data = x;
		next = null;
	}
}
*/
class Solution {
    private Node reverse(Node head){
        if(head == null || head.next == null)
            return head;
        Node prev = null;
        for(Node curr = head;curr != null;){
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
	public Node addOne(Node head) {
        // TC : O(d) + O(d) + O(d) where d is the no. of digits
        // SC : O(1)
		Node newHead = reverse(head);
		Node temp = newHead;
		int carry = 1;
		
		while(temp.next != null){
		    int sum = temp.data + carry;
		    temp.data = sum%10;
		    carry = (int)sum/10;
		    temp = temp.next;
		}
		
		// process last node also
		int sum = temp.data + carry;
		temp.data = sum % 10;
		carry = (int) sum/10;
		
		
		if(carry > 0){
		    temp.next = new Node(carry);
		}
		
		return reverse(newHead);
	}
}

class RecursiveSolution {
    private int solve(Node curr){
        if(curr == null){
            return 1; // base case : reached null, end of LL
        }
        
        int sum = solve(curr.next) + curr.data;
        
        curr.data = sum % 10;
        return (int) sum / 10;
    }
    public Node addOne(Node head) {
        // using recursion + backtracking (without reversal of Linked List)
        // TC : O(n)
        // SC : O(n)
        int carry = solve(head);
        if(carry == 1){
            Node newNode = new Node(1);
            newNode.next = head;
            return newNode;
        }
        return head;
    }
}