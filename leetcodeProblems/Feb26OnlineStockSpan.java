import java.util.*;
class StockSpanner {
    // brute force approach
    List<Integer> list; // use a dynamic array for storing all prices at any time
    public StockSpanner() {
        list = new ArrayList<>();
    }
    
    public int next(int price) {
        // TC : O(n)
        // SC : O(1)
        // add to list
        list.add(price);
        // cnt is the consecutive no. of values from i = n-1 to 0 where list[i] <= price
        int cnt = 0;
        for(int i = list.size()-1;i >= 0;i--){
            if(list.get(i) <= price){
                cnt++;
            }else{
                // not consecutive now: therefore break
                break;
            }
        }
        return cnt;
    }
}

class StockSpanner {
    // Monotonic stack approach : PGE(Previous Greater Element) finding
    // we need to find consecutive days(including curr day) which are <= curr day 

    // all calls TC and SC 
    // TC : O(2n) all elements are inserted and removed once
    // SC : O(n) stack 
    // we cannot find for each next() call 

    Stack<int[]> stack; // contains elements like {val, idx}
    int idx;
    public StockSpanner() {
        stack = new Stack<>(); // monotonic stack to find PGE
        idx = -1; // tracking elements
    }
    
    public int next(int price) {
        idx++; // new element
        // find in stack all elements <= price
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            // pop them out as this is a monotonically decreasing stack (to find PGE)
            stack.pop();
        }
        int cnt;
        int pge;
        if(stack.isEmpty()){
            // no PGE
            pge = -1;
        }else{
            // peek element is pge
            // find the idx of peek element
            pge = stack.peek()[1];
        }
        cnt = idx - pge;
        stack.push(new int[]{price, idx}); // push in stack
        return cnt; // return the cnt
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */