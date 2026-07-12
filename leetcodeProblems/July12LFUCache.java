import java.util.*;
class Node{
    int key,val,cnt;
    Node prev,next;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
        this.cnt = 1;
    }
}
class LRUCacheList {
    Map<Integer, Node> map; // stores key, address to doubly LL node
    Node head;
    Node tail;
    int size;
    public LRUCacheList() {
        size = 0; // size of this list
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        // link head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    public void removeNode(Node node){
        // TC & SC : O(1)
        // remove the node from anywhere 
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }

    public void addFront(Node node){
        // TC & SC : O(1)
        // insert node at front : after head
        Node nextNode = head.next;
        node.next = nextNode;
        nextNode.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }
}

class LFUCache {

    int capacity; // capacity of LFUCache
    int minFreq; // stores the min. freq at present in this LFU Cache
    Map<Integer, LRUCacheList> freqListMap; // map of {freq, list (LRU Cache) of nodes having freq: freq}
    Map<Integer, Node> keyNodeMap; // map of {key, node}

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minFreq = 0;
        freqListMap = new HashMap<>();
        keyNodeMap = new HashMap<>();
    }

    public void updateFreqListMap(Node node){
        // TC & SC : O(1)
        int freq = node.cnt; // freq. of this node
        freqListMap.get(freq).removeNode(node);// remove node from that freqList
        if(freq == minFreq && freqListMap.get(freq).size == 0){
            // if the freqList became empty and that freq was actually minFreq, then minFreq is now +1
            minFreq++;
        }

        LRUCacheList nextHigherFreqList = new LRUCacheList();
        if(freqListMap.containsKey(freq+1)){
            // list already exist where node should now be inserted
            nextHigherFreqList = freqListMap.get(freq+1); // make this list point to existing list
        }
        node.cnt += 1; // now node's freq has increased by 1
        nextHigherFreqList.addFront(node); // add the node after the head in LRU Cache List
        freqListMap.put(node.cnt,nextHigherFreqList); // put back modified list in map
        // no need to update anything in keyNodeMap : as address would be same already
    }
    
    public int get(int key) {
        // TC & SC : O(1)
        if(!keyNodeMap.containsKey(key)){
            return -1; // key not found
        }
        // if present
        Node node = keyNodeMap.get(key);
        int val = node.val;
        updateFreqListMap(node); // now since it was called, we update it to other list of freq+1
        return val;
    }
    
    public void put(int key, int value) {
        // TC & SC : O(1)
        if(keyNodeMap.containsKey(key)){
            Node node = keyNodeMap.get(key);
            node.val = value; // update its value
            updateFreqListMap(node); // update freq list map
        }else{
            // doesnot exist
            if(keyNodeMap.size() == capacity){
                // already full, remove LFU
                LRUCacheList list = freqListMap.get(minFreq); // list of minFreq
                Node node = list.tail.prev; // node to be removed(before tail)
                list.removeNode(node); // remove from list
                keyNodeMap.remove(node.key); // also remove from keyNodeMap
            }
            // new value needs to be added now which was not present previously
            minFreq = 1; // this updates to 1 : as new node has now freq as 1
            LRUCacheList listFreq = new LRUCacheList();
            if(freqListMap.containsKey(minFreq)){
                // already present
                listFreq = freqListMap.get(minFreq);
            }
            // now add the node in listFreq
            Node node = new Node(key, value);
            listFreq.addFront(node); // add it to front (becoz it is Recently Used)
            keyNodeMap.put(key, node); // also add in key Node Map
            freqListMap.put(minFreq, listFreq); // update in map also
        }

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */