import java.util.*;
// double linked list
class Node{
    int key,val;
    Node prev,next;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
    }
}
class LRUCache {
    Map<Integer, Node> map; // stores key, address to doubly LL node
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        // link head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node){
        // TC & SC : O(1)
        // remove the node from anywhere 
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void insert(Node node){
        // TC & SC : O(1)
        // insert node at front : after head
        Node nextNode = head.next;
        node.next = nextNode;
        nextNode.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    public int get(int key) {
        // TC & SC : O(1)
        if(!map.containsKey(key)){
            // key doesnot exist
            return -1;
        }
        Node node = map.get(key);
        remove(node); // remove the node from b/w
        insert(node); // insert after head, as it is now became Recently used
        return node.val;
    }
    
    public void put(int key, int value) {
        // TC & SC : O(1)
        if(map.containsKey(key)){
            // we need to update value of node and make it recently used also
            Node node = map.get(key);
            remove(node);
            insert(node); // inserted at beginning
            node.val = value; // value updated 
        }else{
            // a new node now
            if(map.size() == this.capacity){
                // capacity full of cache
                // remove LRU to insert new node
                Node node = tail.prev;
                remove(node);
                // also remove from map
                map.remove(node.key);
            }
            // now insert the new node
            Node node = new Node(key,value);
            insert(node);
            // also insert in map
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */