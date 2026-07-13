/* Structure of doubly linked list node
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
*/
class Solution {
    private void swap(Node curr){
        if(curr == null){
            return;
        }
        Node temp = curr.next;
        curr.next = curr.prev;
        curr.prev = temp;
    }
    public Node reverse(Node head) {

        // TC : O(n)
        // SC : O(1)
        
        if(head.next == null)
            return head; // no need to do anything, only one node in LL
            
        // one-pass solution
        Node curr = head;
        
        while(curr != null && curr.next != null){
            // swap both links
            swap(curr);
            
            curr = curr.prev; // next node is now on prev
        }
        
        // now curr is at last node(links havenot been reversed)
        swap(curr);
        
        head = curr;
        return head; 
    }
}