import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int cnt0 = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '0') cnt0++;
        }
        
        // If there are zero '0's left, we are already at the target
        if (cnt0 == 0) return 0;
        
        // TreeSets to keep track of unvisited counts of '0's (separated by parity)
        TreeSet<Integer> evenSet = new TreeSet<>();
        TreeSet<Integer> oddSet = new TreeSet<>();
        
        // Initially, all possible counts of '0's from 0 to n are unvisited
        for (int i = 0; i <= n; i++) {
            if (i % 2 == 0) evenSet.add(i);
            else oddSet.add(i);
        }
        
        // Remove the starting state from the unvisited sets
        if (cnt0 % 2 == 0) evenSet.remove(cnt0);
        else oddSet.remove(cnt0);
        
        // Queue for BFS
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(cnt0);
        
        int ans = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                
                if (cur == 0) return ans;
                
                // Calculate the minimum and maximum number of '0's we can flip
                int minZerosToFlip = Math.max(0, k - n + cur);
                int maxZerosToFlip = Math.min(cur, k);
                
                // The resulting counts of '0's will fall in this exact range [l, r]
                int l = cur + k - 2 * maxZerosToFlip;
                int r = cur + k - 2 * minZerosToFlip;
                
                // Because -2x changes the state by multiples of 2, 
                // all valid next states share the same parity as 'l'
                TreeSet<Integer> t = (l % 2 == 0) ? evenSet : oddSet;
                
                // Find and enqueue all unvisited states in the valid range
                Integer next = t.ceiling(l);
                while (next != null && next <= r) {
                    q.offer(next);
                    t.remove(next); // Mark as visited
                    next = t.ceiling(l); // Look for the next unvisited valid state
                }
            }
            ans++;
        }
        
        return -1;
    }
}