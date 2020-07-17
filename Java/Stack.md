# Stack
## Methods  
`Object push(Object element)`  
`Object pop()` - Removes and return the top element of the stack  
`Object peek()` - Returns the element on the top of the stack but not remove it  
`boolean empty()`  
`int search(Object element)` - If the element is found, it returns the position from the top of the stack. Else, it returns -1.  


## Reverse stack
Without extra memory, without using other data struture (like linked list), reverse a stack in place.
Time Complexity O(n^2).
```Java
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
