// Structure of a link list node
class Node {
    int data;  // value stored in node
    Node next;
    Node prev;

    Node(int value) {
        data = value;
        next = null;
        prev = null;
    }
}

class BetterSolution {
    Node removeDuplicates(Node head) {
        // TC : O(n)
        // SC : O(1)
        for(Node temp = head;temp != null && temp.next != null;temp = temp.next){
            Node nextNode = temp.next;
            // move nextNode until temp and nextNode are not having same data
            while(nextNode != null && temp.data == nextNode.data){
                nextNode = nextNode.next;
            }
            // now temp and nextNode are distinct elements
            if(nextNode != null){
                nextNode.prev = temp;
            }
            temp.next = nextNode;
        }
        return head;
    }
}

class Solution {
    Node removeDuplicates(Node head) {
        // TC : O(n)
        // SC : O(1)
        int prev = -1;
        for(Node temp = head;temp != null;temp = temp.next){
            if(temp.data == prev){
                // a duplicate node
                // remove it
                Node prevNode = temp.prev;
                Node nextNode = temp.next;
                if(prevNode != null)
                    prevNode.next = nextNode;
                if(nextNode != null)
                    nextNode.prev = prevNode;
            }
            prev = temp.data; // update prev
        }
        return head;
    }
}