import java.util.*;
//Definition for singly Link List Node
class Node
{
    int data;
    Node next,prev;

    Node(int x){
        data = x;
        next = null;
        prev = null;
    }
}

// You can also use the following for printing the link list.
// Node.printList(Node node);


class Solution {
    private static Node findTailInDLL(Node head){
        Node tail = head;
        while(tail.next != null){
            tail = tail.next;
        }
        return tail;
    }
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        // TC : O(n)
        // SC : O(1) auxilary space
        Node tail = findTailInDLL(head);
        Node temp1 = head, temp2 = tail;
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        while(temp1 != temp2 && temp2.next != temp1 && temp1.prev != temp2){
            // or while(temp1.val < temp2.val)
            // this loop will stop when both pointers either come at middle node
            // or have interchanged their positions
            int sum = temp1.data + temp2.data;
            if(sum == target){
                list.add(new ArrayList<>(Arrays.asList(temp1.data, temp2.data)));
                // move both the pointers
                temp1 = temp1.next;
                temp2 = temp2.prev;
            }else if(sum > target){
                // move temp2
                temp2 = temp2.prev;
            }else{
                // sum < target
                temp1 = temp1.next; // move temp1
            }
        }
        
        return list;
    }
}

class BruteSolution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        // brute force approach
        // TC : O(n^2)
        // SC : O(1)
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        for(Node temp1 = head;temp1 != null;temp1 = temp1.next){
            for(Node temp2 = temp1.next; temp2 != null; temp2 = temp2.next){
                // start temp2 from next node of temp1 and should be not null
                int sum = temp1.data + temp2.data;
                if(sum == target){
                    list.add(new ArrayList<>(Arrays.asList(temp1.data, temp2.data)));
                }else if(sum > target){
                    break; // no need to traverse further for temp2, now traverse for next temp1
                }
            }
        }
        return list;
    }
}