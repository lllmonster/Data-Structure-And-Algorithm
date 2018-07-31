# Stack
## Reverse stack
Without extra memory, without using other data struture (like linked list), reverse a stack in place.  
Time Complexity O(n^2).  
```
import java.util.*;

public class ReverseStack{
    static Stack<Integer> stack = new Stack<>();
    
    static void insertAtBottom(int x) {
        if (stack.size() == 0) {
            stack.push(x);
        } else {
            int a = stack.pop();
            insertAtBottom(x);
            stack.push(a);
        }
    }
    
    static void reverse() {
        if (stack.size() > 0) {
            int x = stack.pop();
            reverse();
            insertAtBottom(x);
        }
    }
    
     public static void main(String []args){
        System.out.println("Hello World");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("Original Stack");
        System.out.println(stack);
        reverse();
        System.out.println("Reversed Stack");
        System.out.println(stack);
     }
}
```
