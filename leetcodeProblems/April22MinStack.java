import java.util.*;
class MinStack {
    // SC : O(n)
    // TC : O(1)
    Stack<int[]> stack;// stores {val, min_val_till_now}

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        int[] arr = new int[2];
        int currentMin = stack.isEmpty() ? val : Math.min(val, stack.peek()[1]);
        arr[0] = val;
        arr[1] = currentMin;
        stack.push(arr);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

class MinStack {
    // TC : O(1)
    // SC : O(1)
    Stack<Long> stack;
    long mini;
    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        long curr = (long)val;
        if(stack.isEmpty()){
            stack.push(curr);
            mini = curr;
        }
        // if stack is not empty
        else if(curr < mini){
            // encode the value and store it
            long element = 2*curr-mini;
            stack.push(element);// insert the updated value
            mini = curr;
        }
        else{
            // if val >= mini
            // normal push
            stack.push(curr);
        }
    }
    
    public void pop() {
        if(stack.isEmpty()) return;
        long curr = stack.pop();
        if(curr < mini){
            // if curr < mini
            // decode the previous min
            long prevMin = 2*mini-curr;
            mini = prevMin;
        }
    }
    
    public int top() {
        if(stack.isEmpty()) return -1;
        long curr = stack.peek();
        if(curr < mini){
            // if encoded, the actual value is the current mini
            return (int)mini;
        }
        else{
            return (int)curr;
        }
    }
    
    public int getMin() {
        return stack.isEmpty()?-1:(int)mini;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
