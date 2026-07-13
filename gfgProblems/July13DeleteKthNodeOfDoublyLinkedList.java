
//Structure of a Doubly LinkList
class Node {
    int data;
    Node next;
    Node prev;

    Node(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class Solution {
    public static Node deleteHead(Node head) {
        if(head == null || head.next == null){
            return null;
        }
        head = head.next;
        head.prev = null; 
        return head;
    }
    public static Node deleteTail(Node head) {
        if(head.next == null)
            return null;
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        // curr is now at last node
        Node prev = curr.prev;
        prev.next = null;
        curr.prev = null;
        return head;
    }
    public Node delPos(Node head, int x) {
        
        Node temp = head;
        int cnt = 0;
        while(temp != null){
            cnt++;
            if(cnt == x){
                break;
            }
            temp = temp.next;
        }
        
        // now I am at the node which needs to be deleted
        
        Node prev = temp.prev; // node prev to node to be deleted
        Node next = temp.next;  // node next to node to be deleted
        // 4 cases
        // 1st edge case
        if(prev == null && next == null){
            // only 1 element in doubly LL
            return null; // it deletes temp node automatically
        }
        
        // 2nd edge case
        else if(prev == null){
            // we need to delete the front of doubly linked list
            // as prev of head is null
            head = deleteHead(head);
            return head;
        }
        // 3rd edge case
        else if(next == null){
            // we need to delete the tail of doubly linked list
            // as next of tail is null
            head = deleteTail(head);
            return head;
        }else{
            // 4th case : when prev and next nodes are non-empty
            prev.next = next;
            next.prev = prev;
            // deletes temp node
            return head;
        }
        
    }
}
