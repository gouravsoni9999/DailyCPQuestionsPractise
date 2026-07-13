class Node{
    // doubly linked list
    int data;
    Node next,prev;
    Node(int data){
        this.data = data;
    }
}

public class July13DoublyLinkedList {
    public static Node convertArray2DLL(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1;i < n;i++){
            Node node = new Node(arr[i]);
            temp.next = node;
            node.prev = temp;
            temp = temp.next;
        }
        return head;
    }
    public static void print(Node head){
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {6,2,1,8};
        Node head = convertArray2DLL(arr);
        print(head);
    }
}
