class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
    }
}
public class July12ConvertArrayToLinkedList {

    static Node createLLFromArray(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1;i < n;i++){
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    static void printLL(Node head){
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    static int lengthOfLL(Node head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    static boolean checkIfPresent(Node head,int data){
        while(head != null){
            if(head.data == data){
                return true;
            }
            head = head.next;
        }
        return false;
    }
    static void main(String[] args) {
        int[] arr = {5,2,1,8};
        Node head = createLLFromArray(arr);
        // printLL(head);
        System.out.println(lengthOfLL(head));
        System.out.println(checkIfPresent(head, 69));
    }
}
