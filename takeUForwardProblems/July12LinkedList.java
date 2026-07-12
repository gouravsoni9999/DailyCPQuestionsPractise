class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
    }
}
public class July12LinkedList {
    public static void main(String[] args) {
        Node node = new Node(5);
        System.out.println(node);
        System.out.println(node.data);
        System.out.println(node.next);
    }
}
