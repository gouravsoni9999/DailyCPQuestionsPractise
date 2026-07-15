class Solution {
    public int lengthOfLoop(Node head) {
        // TC : O(n)
        // SC : O(n)
        // we will be using a hasmap storing visited nodes <node,timer>
        // where timer is the second at which we visited that node
        int timer = 1;
        Map<Node, Integer> visitedMap = new HashMap<>();
        for(Node temp = head;temp != null;temp = temp.next){
            if(visitedMap.containsKey(temp)){
                // already visited
                int len = timer - visitedMap.get(temp);
                return len; // length of LL
            }else{
                visitedMap.put(temp, timer);
            }
            timer++;
        }
        return 0; // since no loop, there no length of loop
    }
}

class SolutionTortoiseAndHare {
    public int lengthOfLoop(Node head) {
        // TC : O(n)
        // SC : O(1)
        // using Tortoise and Hare Algo
        Node slow = head;
        Node fast = head;
        // first detect loop code
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            int cnt = 0;
            if(slow == fast){
                do{
                    fast = fast.next;
                    cnt++;
                }while(fast != slow);
                return cnt;
            }
        }
        return 0; // no loop
    }
}