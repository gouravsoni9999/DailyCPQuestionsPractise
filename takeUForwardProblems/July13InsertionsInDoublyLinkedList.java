class Node {
    // doubly linked list
    int data;
    Node next, prev;

    Node(int data) {
        this.data = data;
    }
}

public class July13InsertionsInDoublyLinkedList {
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

    public static Node insertAtHead(Node head, int val) {

        Node newNode = new Node(val);
        if (head == null) {
            return newNode;
        }
        newNode.next = head;
        head.prev = newNode;
        return newNode;
    }

    public static Node insertAtTail(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) {
            return newNode;
        }
        Node prev = head;
        while (prev.next != null) {
            prev = prev.next;
        }
        prev.next = newNode;
        newNode.prev = prev;
        return head;
    }

    public static Node insertAtPos(Node head, int val, int pos) {
        if (pos == 1) {
            return insertAtHead(head, val);
        }
        Node temp = head;

        int cnt = 0;

        while (temp != null) {
            cnt++;
            if (cnt == pos) {
                break;
            }
            temp = temp.next;
        }
        Node prev = temp.prev;
        Node newNode = new Node(val);
        newNode.next = temp;
        newNode.prev = prev;
        prev.next = newNode;
        temp.prev = newNode;
        return head;
    }

    public static void insertBeforeNode(Node node, int data) {
        // node will not be the head of the doubly linked list
        Node prev = node.prev;
        Node newNode = new Node(data);
        
        newNode.next = node;
        newNode.prev = prev;

        prev.next = node;
        node.prev = newNode;

    }

    public static void main(String[] args) {
        int[] arr = { 4, 7, 1, 8 };
        Node head = convertArray2DLL(arr);
        head = insertAtHead(head, 6);
        print(head);
        head = insertAtTail(head, 9);
        print(head);
        head = insertAtPos(head, 10, 3);
        print(head);
        insertBeforeNode(head.next.next, 12);
        print(head);
    }
}
