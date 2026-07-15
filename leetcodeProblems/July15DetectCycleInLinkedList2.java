public class July15DetectCycleInLinkedList2 {
    public ListNode detectCycle(ListNode head) {
        // TC : O(n)
        // SC : O(n) // visited node
        ListNode temp = head;
        Set<ListNode> visited = new HashSet<>();
        // first node to be revisited is the starting of looop
        while(temp != null){
            if(visited.contains(temp)){
                return temp; // starting of loop
            }else{
                visited.add(temp);
            }
            temp = temp.next;
        }
        return null;
    }
}

class July15DetectCycleInLinkedList2UsingTortoiseAndHareAlgo {
    public ListNode detectCycle(ListNode head) {
        // TC : O(n)
        // SC : O(1)
        // using tortoise and hare algo
        ListNode slow = head, fast = head;
        // detect cycle
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                // loop detected
                slow = head; // move 1 pointer to the head
                // start traversing slow and fast one at a time
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // both slow and fast now point to starting of loop
            }
        }
        return null; // no loop found
    }
}