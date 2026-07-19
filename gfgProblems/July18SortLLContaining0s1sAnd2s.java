
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class Solution {
    public Node segregate(Node head) {
        // brute force: store the counts of 0,1 and 2 in arr
        // and then replace in nodes
        // TC : O(n)
        // SC : O(1)

        int[] arr = new int[3];

        for (Node temp = head; temp != null; temp = temp.next) {
            arr[temp.data]++;
        }

        int i = 0;
        for (Node temp = head; temp != null; temp = temp.next) {
            while (arr[i] == 0) {
                i++;
            }
            temp.data = i;
            arr[i]--;
        }

        return head;
    }
}

class PointerManipulationSolution {
    private Node insert(Node list, Node iterator) {
        // insert after iterator
        while (list != null) {
            iterator.next = list;
            list = list.next; // move to the next node
            iterator = iterator.next; // move iterator for insertion
        }
        return iterator; // return last inserted node
    }

    public Node segregate(Node head) {
        // TC : O(n)
        // SC : O(1)
        // make 3 lists of 0s, 1s and 2s
        Node[] hash = new Node[3];

        for (int i = 0; i < 3; i++) {
            // store the nodes acc. to index values
            hash[i] = new Node(-1); // stores a linked list's first node
        }

        Node[] iterator = new Node[3];

        for (int i = 0; i < 3; i++) {
            iterator[i] = hash[i]; // stores the iterator of all 3 linked lists
        }

        while (head != null) {
            iterator[head.data].next = head; // store head node in
            Node next = head.next; // store the pointer to next node
            head.next = null; // make this node point to null
            iterator[head.data] = iterator[head.data].next; // move iterator one step ahead
            head = next; // head is updated to next node of the og linked list
        }

        Node finalNode = new Node(-1); // final linked list
        Node lastNode = finalNode; // iterator for this linked list

        for (int i = 0; i < 3; i++) {
            if (hash[i].next != null) {
                // list is not empty, then only insert nodes
                lastNode = insert(hash[i].next, lastNode); // lastNode stores info of last inserted node
            }
        }

        return finalNode.next; // final list is formed, return next of finalNode
    }
}

class LinkChangeSolution {
    // best approach
	public Node segregate(Node head) {
		// TC : O(n)
		// SC : O(1)
		// make 3 lists of 0s, 1s and 2s
		Node[] headNode = new Node[3];
		
		for (int i = 0; i < 3; i++) {
			// store the nodes acc. to index values
			headNode[i] = new Node(-1); // stores a linked list's first node
		}
		
		Node[] iterator = new Node[3];
		
		for (int i = 0; i < 3; i++) {
			iterator[i] = headNode[i]; // stores the iterator of all 3 linked lists
		}
		
		while (head != null) {
			iterator[head.data].next = head; // store head node in
			iterator[head.data] = iterator[head.data].next; // move iterator one step ahead
			head = head.next; // head is updated to next node of the og linked list
		}
		// link last node of 0th linked list to 1st linked list(if 1st linked list is not null) or to linked list2 (if linked list 1 is null)
		iterator[0].next = (headNode[1].next == null) ? headNode[2].next : headNode[1].next; 
		// link last node of linked list 1 to linked list 2
		iterator[1].next = headNode[2].next;
		// last node of linked list 2 is always pointed to null
		iterator[2].next = null;
		
		return headNode[0].next; // this is now the newHead of the resultant linked list
	}
}
