// Structure of Doubly Linked List
class Node {
    int data;
    Node next;
    Node prev;

    Node(int x) {
        data = x;
        next = null;
        prev = null;
    }
}

class Solution {
    static Node deleteAllOccurOfX(Node head, int x) {
        // TC : O(n)
        // SC : O(1)
        for(Node temp = head;temp != null;temp = temp.next){
            if(temp.data == x){
                if(temp == head){
                    // first node
                    head = head.next; // change the head;
                }
                Node prevNode = temp.prev;
                Node nextNode = temp.next;
                if(prevNode != null){
                    // only assign when prevNode is not null
                    prevNode.next = nextNode;
                }if(nextNode != null){
                    // only assign when nextNode is not null
                    nextNode.prev = prevNode;
                }
            }
        }
        return head;
    }
}