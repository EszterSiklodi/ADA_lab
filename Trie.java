import java.util.*;

public class Trie {
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        
        for(char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        
        current.isWord = true;
    }
    
    public void printWords() {
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        dfs(root, words, sb);
        
        Collections.sort(words);
        
        for(String word : words) {
            System.out.println(word);
        }
    }
    
    private void dfs(TrieNode node, List<String> words, StringBuilder sb) {
        if(node.isWord) {
            words.add(sb.toString());
        }
        
        for(Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            sb.append(entry.getKey());
            dfs(entry.getValue(), words, sb);
            sb.setLength(sb.length() - 1);
        }
    }
    
    private class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;
        
        public TrieNode() {
            isWord = false;
            children = new HashMap<>();
        }
    }
    public static void main(String[] args) {
    Trie trie = new Trie();

    trie.addWord("apple");
    trie.addWord("world");
    trie.addWord("bananas");
    trie.addWord("there");
    trie.addWord("table");

    trie.printWords();
    }
}

