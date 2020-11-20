package practice.linkedlists;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description</b> : Insert/delete/search into trie data structure
 *  Code from https://github.com/mission-peace/interview/blob/master/src/com/interview/suffixprefix/Trie.java
 *  https://www.youtube.com/watch?v=AXjmTQ8LEoI
 *
 * @author Vinod Akkepalli
 */

public class TrieImpl {

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public TrieImpl() {
        root = new TrieNode();
    }

    /**
     * Iterative implementation of insert into trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes endOfWord as true
        current.endOfWord = true;
    }

    /**
     * Recursive implementation of insert into trie
     */
    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }


    private void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //if end of word is reached then mark endOfWord as true on current node
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        //if node does not exists in map then create one and put it into map
        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRecursive(node, word, index + 1);
    }

    /**
     * Iterative implementation of search into trie.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                return false;
            }
            current = node;
        }
        //return true of current's endOfWord is true else return false.
        return current.endOfWord;
    }

    /**
     * Recursive implementation of search into trie.
     */
    public boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //return true of current's endOfWord is true else return false.
            return current.endOfWord;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        //if node does not exist for given char then return false
        if (node == null) {
            return false;
        }
        return searchRecursive(node, word, index + 1);
    }

    /**
     * Delete word from trie.
     */
    public void delete(String word) {
        delete(root, word, 0);
    }

    /**
     * Returns true if parent node should also be considered for deleting
     */
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if currrent.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        //shouldDeleteCurrentNode = true  ==> node should be considered for deletion
        //current.endOfWord = true ==> there is another word which ends at current node
        if (shouldDeleteCurrentNode && !current.endOfWord) {
            current.children.remove(ch);
            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        } else if (shouldDeleteCurrentNode) {
            //There exists a word that ends at current node
            current.children.remove(ch);
            return false;
        }
        return false;
    }

    private void printAutoSuggestions(String prefix) {

        int pfLen = prefix.length();
        TrieNode currNode = root;

        if(root.children.isEmpty()) {
            System.out.println("No suggestions");
        }

        //Get to the TrieNode of final character of prefix if exists
        for (int i = 0; i < pfLen; i++) {
            if(currNode.children.containsKey(prefix.charAt(i))) {
                currNode = currNode.children.get(prefix.charAt(i));
            } else {
                System.out.println("path available only up to: " + prefix.substring(0,i));
                System.out.println("No Suggestions");
                return;
            }
        }

        //Print all subtrees of TrieNode of the final character of prefix
        printSubtree(currNode, prefix);
    }

    //Recursively print all strings
    private void printSubtree(TrieNode currNode, String prefix) {
        if(currNode.children.isEmpty()) {
            System.out.println(prefix);
            return;
        }

        //If a TrieNode marks end of word, print the word
        if(currNode.endOfWord) {
            System.out.println(prefix);
        }

        for (Map.Entry<Character, TrieNode> entry: currNode.children.entrySet()) {
            printSubtree(entry.getValue(), prefix + entry.getKey());
        }
    }

    public static void main(String[] args) {
        TrieImpl trie = new TrieImpl();

        System.out.println("Inserting in the trie");
        trie.insert("vinod");
        trie.insert("vinodakkepalli");
        trie.insert("vinay");
        trie.insert("vanaja");
        trie.insert("lucky");
        trie.insert("loukya");


        System.out.println("vinod present: " +  trie.search("vinod"));
        trie.delete("vinod");
        System.out.println("Searching after deleting 'vinod'");
        System.out.println("vinod present: " +  trie.search("vinod"));
        System.out.println("vinodakkepalli present: " +  trie.search("vinodakkepalli"));
        System.out.println("Searching after inserting 'vinod'");
        trie.insert("vinod");
        System.out.println("vinod present: " +  trie.search("vinod"));
        System.out.println("Searching after deleting 'vinodakkepalli'");
        trie.delete("vinodakkepalli");
        System.out.println("vinodakkepalli present: " +  trie.search("vinodakkepalli"));
        System.out.println("vinod present: " +  trie.search("vinod"));



        trie.insert("vinodakkepalli");
        trie.insert("vimal");
        trie.insert("vi");

        System.out.println("****Auto suggestions are***");
        trie.printAutoSuggestions("vi");
    }
}