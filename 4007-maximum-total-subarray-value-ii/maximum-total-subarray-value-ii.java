import java.util.*;

class Solution {

    static class Node {
        long val;
        int l, r;

        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }

    int[][] mx;
    int[][] mn;
    int[] lg;

    private void build(int[] nums) {
        int n = nums.length;

        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i / 2] + 1;
        }

        int K = lg[n] + 1;

        mx = new int[K][n];
        mn = new int[K][n];

        for (int i = 0; i < n; i++) {
            mx[0][i] = nums[i];
            mn[0][i] = nums[i];
        }

        for (int k = 1; k < K; k++) {
            int len = 1 << k;

            for (int i = 0; i + len <= n; i++) {
                mx[k][i] = Math.max(
                    mx[k - 1][i],
                    mx[k - 1][i + (len >> 1)]
                );

                mn[k][i] = Math.min(
                    mn[k - 1][i],
                    mn[k - 1][i + (len >> 1)]
                );
            }
        }
    }

    private long value(int l, int r) {
        int k = lg[r - l + 1];

        int maxVal = Math.max(
            mx[k][l],
            mx[k][r - (1 << k) + 1]
        );

        int minVal = Math.min(
            mn[k][l],
            mn[k][r - (1 << k) + 1]
        );

        return (long) maxVal - minVal;
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        build(nums);

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));

        for (int l = 0; l < n; l++) {
            pq.offer(new Node(value(l, n - 1), l, n - 1));
        }

        long ans = 0;

        while (k-- > 0) {
            Node cur = pq.poll();

            ans += cur.val;

            if (cur.r > cur.l) {
                int nr = cur.r - 1;
                pq.offer(new Node(value(cur.l, nr), cur.l, nr));
            }
        }

        return ans;
    }
}