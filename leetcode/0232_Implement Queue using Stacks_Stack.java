/**
my own solu */
class MyQueue {
    
    Stack<Integer> s1;
    Stack<Integer> s2;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int tmp = s2.pop();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return tmp;
    }
    
    public int peek() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int tmp = s2.peek();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return tmp;
    }
    
    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
solu 1 */
class MyQueue {
    
    Stack<Integer> s1;
    Stack<Integer> s2;
    int front;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        if (s1.isEmpty()) 
            front = x;
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(x);
        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
    
    public int pop() {
        int tmp = s1.pop();
        if (!s1.isEmpty()) {
            front = s1.peek();
        }
        return tmp;
    }
    
    public int peek() {
        return front;
    }
    
    public boolean empty() {
        return s1.isEmpty();
    }
}


/**
Optimal Solu */
class MyQueue {
    
    Stack<Integer> s1;
    Stack<Integer> s2;
    int front;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        if (s1.isEmpty()) 
            front = x;
        s1.push(x);
    }
    
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    public int peek() {
        if (!s2.isEmpty())
            return s2.peek();
        return front;
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
optimal 2 - greater without front*/
class MyQueue {
    
    Stack<Integer> s1;
    Stack<Integer> s2;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        peek();
        return s2.pop();
    }
    
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}