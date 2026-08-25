package DSAProblemStmts;
import java.util.*;
class Stack{
    int top;
    String[] arr;
    int N;
    Stack(int N){
        arr = new String[N];
        top = -1;
        this.N = N;
    }
    public boolean isEmpty(){
        return (top == -1);
    }
    public void push(String x){
        top++;
        arr[top] = x;
    }
    public void pop(){
        top--;
    }
    public String top(){
        return arr[top];
    }
    public int size(){
        return top+1;
    }
}

public class July22StackImplementationUsingArray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt(); // n no of books
        scn.nextLine();
        Stack shelf = new Stack(N);
        for(int i = 0;i < N;i++){
            String str = scn.nextLine();
            if(!str.equals(""))
                shelf.push(str);
        }
        int M = scn.nextInt();
        while(M-- > 0){
            if(!shelf.isEmpty())
                shelf.pop();
            else
                System.out.print("None");// print "None" only if stack is empty and we are trying to remove  
        }
        if(!shelf.isEmpty()){
            System.out.print("False " + shelf.top()  + " ");
        }
        else{
            System.out.print("True ");
        }
        System.out.print(shelf.size());
    }
}
