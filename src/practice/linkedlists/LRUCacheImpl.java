package practice.linkedlists;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description</b> : Design and implementation of LRU Cache
 * https://www.youtube.com/watch?v=S6IfqDXWa10
 *  https://www.geeksforgeeks.org/design-a-data-structure-for-lru-cache/
 *
 * @author Vinod Akkepalli
 */

class Node {
    int key;
    int value;
    Node next;
    Node previous;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

public class LRUCacheImpl {

    Map<Integer, Node> hm;
    Node head;
    Node tail;
    int capacity;
    int currentSize;

    public LRUCacheImpl(int capacity) {
        this.hm = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.previous = null;
        this.head.next = tail;
        this.tail.previous = head;
        this.tail.next = null;
        this.capacity = capacity;
        this.currentSize = 0;
    }

    void deleteNode (Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    void addHead (Node node) {
        node.previous = head;
        node.next = head.next;
        head.next.previous = node;
        head.next = node;
    }

    int get(int key) {

        if(hm.containsKey(key)) {
            Node node = hm.get(key);
            deleteNode(node);
            addHead(node);
            System.out.println("node found in cache: " + node.toString());
            return node.value;
        }
        System.out.println("key :" + key + " not found in cache");
        return -1;
    }


    void set(int key, int value) {
        if (hm.containsKey(key)) {
            Node node = hm.get(key);
            node.value = value;
            deleteNode(node);
            addHead(node);
        } else {
            Node node = new Node(key, value);
            hm.put(key, node);
            if(currentSize < capacity) {
                currentSize++;
            } else {
                hm.remove(tail.previous.key);
                deleteNode(tail.previous);
            }
            addHead(node);
        }
    }

    void printCache() {
        Node current = head.next;
        System.out.print("Cache content :" +  " { ");
        while (current != tail) {
            System.out.print(current.toString() + " ");
            current = current.next;
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        LRUCacheImpl lruCache = new LRUCacheImpl(5);

        lruCache.set(1, 11);
        lruCache.set(2, 22);
        lruCache.set(3, 33);
        lruCache.set(4, 44);
        lruCache.set(5, 55);
        lruCache.printCache();

        lruCache.get(4);
        lruCache.printCache();
        lruCache.get(9);

        if(lruCache.get(8) == -1) {
            System.out.println("adding node (8, 88) to cache");
            lruCache.set(8, 88);
            lruCache.printCache();
        }
    }
}
