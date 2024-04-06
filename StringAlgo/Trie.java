package StringAlgo;
/*A trie is a tree-like data structure that stores strings. Each node is a string, and each edge is a character.

The root is the empty string, and every node is represented by
the characters along the path from the root to that node.
This means that every prefix of a string is an ancestor of that string's node.

aka digital tree/ prefix tree

used for pattern searching and when there are a lot of searching queries.

insert, search both take O(n) time and also space optimisation is there as it stores in a compact space.
for insertion the space complexity is O(m*n*26) m words of n length.

*/
public class Trie {
    final private Node root;
    private class Node{
        Node[] childs = new Node[26];
        boolean isEnd = false;
    }

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        int n = word.length();
        Node curr = root;
        for (int i = 0; i < n; i++){
            if (curr.childs[word.charAt(i) - 'a'] == null) curr.childs[word.charAt(i) - 'a'] = new Node();
            curr = curr.childs[word.charAt(i) - 'a'];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        int n = word.length();
        Node curr = root;
        for (int i = 0; i < n; i++){
            int ch = word.charAt(i) - 'a';
            if (curr.childs[ch] == null) return false;
            curr = curr.childs[ch];
        }
        return curr.isEnd;
    }

    public boolean startsWith(String prefix) {
        int n = prefix.length();
        Node curr = root;
        for (int i = 0; i < n; i++){
            int ch = prefix.charAt(i) - 'a';
            if (curr.childs[ch] == null) return false;
            curr = curr.childs[ch];
        }
        return true;
    }
}