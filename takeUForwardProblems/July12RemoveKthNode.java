class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class July12RemoveKthNode {
    public static Node makeNode(int[] arr) {
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < n; i++) {
            temp.next = new Node(arr[i]);
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

    public static Node deleteKthNode(Node head,int k){
        if(k == 0 || head == null){
            return null;
        }
        Node temp = head;
        if(k == 1){
            return head.next;
        }
        int i = 0;
        while(temp != null && ++i != k-1){
            temp = temp.next;
        }
        if(temp == null || temp.next == null){
            return temp; // as we don't have k elements
        }
        temp.next = temp.next.next;
        return head;
    }
    public static void main(String[] args) {
        int[] arr = { 5, 2, 1, 8, 9, 10 };
        Node head = makeNode(arr);
        print(head);
        head = deleteKthNode(head, 1);
        print(head);
    }

}
