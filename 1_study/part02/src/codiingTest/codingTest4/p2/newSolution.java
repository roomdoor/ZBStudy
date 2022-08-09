package codiingTest.codingTest4.p2;

import java.util.*;

public class newSolution {
    private class Node {
        char st;
        Map<Character, Node> children;
        int wordCount;

        public Node(char st) {
            this.st = st;
            children = new HashMap<>();
            wordCount = 0;
        }
    }

    private class Trie {
        Node root = new Node('\n');

        public void addWords(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char next = word.charAt(i);
                if (!cur.children.containsKey(next)) {
                    cur.children.put(next, new Node(next));
                }
                cur = cur.children.get(next);
                cur.wordCount++;
            }
        }

        public int getResult(String query) {
            Node cur = root;

            for (int i = 0; i < query.length(); i++) {
                char next = query.charAt(i);
                if (!cur.children.containsKey(next)) {
                    return 0;
                }
                cur = cur.children.get(next);
            }
            return cur.wordCount;
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie trie = new Trie();
        Trie reversTrie = new Trie();

        for (String word : words) {
            trie.addWords(word);
        }

        for (String s : words) {
            StringBuilder word = new StringBuilder(s);
            reversTrie.addWords(word.reverse().toString());
        }

        int result = 0;
        int i = 0;
        for (String query : queries) {
            boolean isReversed = false;
            if (query.charAt(0) == '*') {
                StringBuilder q = new StringBuilder(query);
                query = q.reverse().toString();
                isReversed = true;
            }
            query = query.replace("*", "");

            if (isReversed) {
                result = reversTrie.getResult(query);
            } else {
                result = trie.getResult(query);
            }
            answer[i++] = result;
        }

        return answer;
    }


    public static void main(String[] args) {
        newSolution sol = new newSolution();
        String[] a = {"a", "b", "aa"};
        String[] b = {"*aa", "bb*", "a*"};
        System.out.println(Arrays.toString(sol.solution(a, b)));
    }
}
