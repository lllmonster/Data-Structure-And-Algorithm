class Solution {
    /**
    Method 1 : HashMap, store new node as value, original node is key, easy
    Method 2: Iterate, copy another node and append into the original one.
                Assign random nodes and separte them. Hard to think
     */
    public Node copyRandomList(Node head) {
        //return iterate(head);
        return hashmap(head);
    }
    
    private Node hashmap(Node head) {
        Map<Node, Node> map = new HashMap<>();
        // copy all nodes
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        
        // assign next and random nodes
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
    
    private Node iterate(Node head) {
        // copy each node information
        Node cur = head;
        while (cur != null) {
            Node tmp = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = tmp;
            cur = tmp;
        }
        // transfer random node information
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // remove the original duplicate node, need to keep original, cannot modify it.
        Node dummy = new Node(0);
        Node copy = dummy;
        cur = head;
        while (cur != null) {
            // separte new copy node
            copy.next = cur.next;
            copy = copy.next;
            // recover the old node
            Node tmp = cur.next.next;
            cur.next = tmp;
            cur = tmp;
        }
        return dummy.next;
    }
}