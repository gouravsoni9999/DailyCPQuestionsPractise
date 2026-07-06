class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // collision only happens when stack has +ve value and element is -ve
        // using stack
        // TC : O(n)
        // SC : O(n)

        int n = asteroids.length;

        List<Integer> stack = new ArrayList<>();

        int i = 0;
        while(i < n){
            if(stack.isEmpty() || !(stack.get(stack.size()-1) > 0 && asteroids[i] < 0)){
                // no collision condition
                stack.add(asteroids[i]);
                i++;
            }else {
                // collision
                if(Math.abs(stack.get(stack.size()-1)) == Math.abs(asteroids[i])){
                    // remove from stack
                    stack.remove(stack.size()-1);
                    i++; // process other elements
                }else if(Math.abs(stack.get(stack.size()-1)) > Math.abs(asteroids[i])){
                    i++; // skip that element
                }else{
                    // abs(stack.peek()) < abs(asteroids[i])
                    stack.remove(stack.size()-1);
                }
            }
        }

        int[] res = new int[stack.size()];
        for(i = 0;i < stack.size();i++){
            res[i] = stack.get(i);
        }
        return res;
    }
}

class Solution {
    private void reverse(int[] arr){
        int i = 0;
        int j = arr.length-1;
        while(i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
    public int[] asteroidCollision(int[] asteroids) {
        // collision only happens when stack has +ve value and element is -ve
        // using stack

        int n = asteroids.length;

        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while(i < n){
            if(stack.isEmpty() || !(stack.peek() > 0 && asteroids[i] < 0)){
                // no collision condition
                stack.push(asteroids[i]);
                i++;
            }else {
                // collision
                if(Math.abs(stack.peek()) == Math.abs(asteroids[i])){
                    // remove from stack
                    stack.pop();
                    i++; // process other elements
                }else if(Math.abs(stack.peek()) > Math.abs(asteroids[i])){
                    i++; // skip that element
                }else{
                    // abs(stack.peek()) < abs(asteroids[i])
                    stack.pop();
                }
            }
        }

        int[] res = new int[stack.size()];
        i = 0;
        while(!stack.isEmpty()){
            res[i++] = stack.pop();
        }
        reverse(res);
        return res;
    }
}