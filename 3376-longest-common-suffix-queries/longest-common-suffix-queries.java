
class Solution {
    static class Node {
        Node[] children = new Node[26];
        int idx = -1;
        int len = Integer.MAX_VALUE;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Node root = new Node();

        for (int i = 0; i < wordsContainer.length; i++) {
            String w = wordsContainer[i];
            Node cur = root;
            if (w.length() < cur.len) {
                cur.len = w.length();
                cur.idx = i;
            }
            for (int j = w.length() - 1; j >= 0; j--) {
                int c = w.charAt(j) - 'a';
                if (cur.children[c] == null) cur.children[c] = new Node();
                cur = cur.children[c];
                if (w.length() < cur.len) {
                    cur.len = w.length();
                    cur.idx = i;
                }
            }
        }

        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String q = wordsQuery[i];
            Node cur = root;
            int best = root.idx;
            for (int j = q.length() - 1; j >= 0; j--) {
                int c = q.charAt(j) - 'a';
                if (cur.children[c] == null) break;
                cur = cur.children[c];
                best = cur.idx;
            }
            ans[i] = best;
        }
        return ans;
    }
}
