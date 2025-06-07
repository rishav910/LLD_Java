import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Node head;
    private Node tail;
    private int maxSize;
    private int currSize;
    private Map<Integer, Node> mp = new HashMap<>();

    // Adds node after head
    private void addNode(Node node) {
        Node p = head.next;
        // head - node - p
        head.next = node;
        node.prev = head;
        node.next = p;
        p.prev = node;
    }

    // Deletes node
    private void deleteNode(Node node) {
        Node p = node.prev, q = node.next;
        // p - node - q
        p.next = q;
        q.prev = p;
    }

    public LRUCache (int size) {
        this.maxSize = size;
        this.currSize = 0;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    void put (int key, int value) {
        if (mp.containsKey(key)) {
            // Update value
            Node curr = mp.get(key);
            curr.val = value;
            // Put it at the front
            deleteNode(curr);
            addNode(curr);
        } else {
            if(currSize+1 > maxSize) {
                mp.remove(tail.prev.key);
                deleteNode(tail.prev);
                currSize--;
            }
            // Entry a value
            Node curr = new Node(key, value);
            mp.put(key, curr);
            // Put the node at front
            addNode(curr);
            currSize++;
        }
    }

    int get (int key) {
        if (mp.containsKey(key)) {
            Node curr = mp.get(key);
            int value = curr.val;
            // Delete from current position
            deleteNode(curr);
            // Add to beginning
            addNode(curr);
            return value;
        }
        return -1;
    }
}
