// doubly linked list Node
class Node {
    int data;
    Node prev, next;

    Node(int data) {
        this.data = data;
    }
}

public class July13DeleteGivenNodeInDoublyLinkedList {
    public static Node convertArray2DLL(int[] arr) {
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < n; i++) {
            Node node = new Node(arr[i]);
            temp.next = node;
            node.prev = temp;
            temp = temp.next;
        }
        return head;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void deleteNode(Node temp) {
        // condition: we will not try to delete head is the only constraint
        Node prevNode = temp.prev;
        Node nextNode = temp.next;

        if (nextNode == null) {
            // at the last node's next null
            prevNode.next = null;
            temp.prev = null;
            // frees temp
        } else {
            // both nextNode and prevNode are not null
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
            temp.next = null;
            temp.prev = null;
            // frees temp
        }
    }

    public static void main(String[] args) {
        int[] arr = { 2, 7, 1, 8 };
        Node head = convertArray2DLL(arr);
        Node temp = head.next.next.next;
        deleteNode(temp);
        print(head);
    }
}