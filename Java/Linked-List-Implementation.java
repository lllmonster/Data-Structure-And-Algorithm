class MyLinkedList {

    class Node {
        int val;
        Node next;

        public Node() {
            next = null;
            val = 0;
        }
        public Node(int x) {val =x;}
        public void setNext(Node n) { next = n; }
        public int getData() { return val; }
        public Node getNext() { return next; }
    }

    Node head;
    int val;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
        val = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        System.out.println("getIndex, " +index);
        Node headBuffer = head;
        for(int i = 0; i < index; i++) {
            if (headBuffer == null) return -1;
            headBuffer = headBuffer.getNext();
        }
        // System.out.println("get, " + headBuffer.getData());
        if(headBuffer == null) return -1;
        else return headBuffer.getData();
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        System.out.println("addAtHead ," + val);
        Node headBuffer = head;
        Node cur = new Node(val);
        cur.setNext(headBuffer);
        head = cur;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        System.out.println("addAtTail ," + val);
        Node end = new Node(val);
        Node headBuffer = head;
        while (headBuffer.getNext() != null) {headBuffer = headBuffer.getNext();}
        headBuffer.setNext(end);
        end.setNext(null);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        System.out.println("addAtIndex, " + index + "," + val);
        Node headBuffer = head;
        Node prev = head;
        boolean ifInsert = true;
        if(index == 0) {
            Node cur = new Node(val);
            cur.setNext(headBuffer);
            head = cur;
        } else {
            for (int i = 0; i < index; i++) {
                if (headBuffer == null) {ifInsert = false; break;}
                prev = headBuffer;
                headBuffer = headBuffer.getNext();
            }
            if (ifInsert) {
                Node cur = new Node(val);
                prev.setNext(cur);
                cur.setNext(headBuffer);
            }
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        System.out.println("deleteAtIndex, " + index );
        if(index == 0) {
            head = head.getNext();
        } else {
            Node headBuffer = head;
            Node prev = head;
            for (int i = 0; i < index; i++) {
                if (headBuffer == null) break;
                prev = headBuffer;
                headBuffer = headBuffer.getNext();
            }
            if (headBuffer != null) {
                prev.setNext(headBuffer.getNext());
            }

        }

    }
}
