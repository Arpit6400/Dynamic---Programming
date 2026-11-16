// GFG: https://www.geeksforgeeks.org/problems/minimal-cost/1
// LC 403: https://leetcode.com/problems/frog-jump/ (Stone jump variation)

import java.util.Arrays;

public class dp4_Frog_Jump_K_Distances {

    public static void main(String[] args) {
        int[] height = { 10, 30, 40, 50, 20 };
        int k = 3;
        System.out.println("Tabulation: " + minimizeCost(height, height.length, k));
        System.out.println("Space Opt: " + minimizeCostSpaceOpt(height, height.length, k));
    }

    // --- Striver DP 4: Frog Jump with K Distance (GFG Minimal Cost) ---

    // Memoization
    private static int solveMemo(int idx, int[] height, int k, int[] dp) {
        if (idx == 0)
            return 0;
        if (dp[idx] != -1)
            return dp[idx];

        int minCost = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (idx - j >= 0) {
                int jump = solveMemo(idx - j, height, k, dp) + Math.abs(height[idx] - height[idx - j]);
                minCost = Math.min(minCost, jump);
            }
        }
        return dp[idx] = minCost;
    }

    public static int minimizeCostMemo(int[] height, int n, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solveMemo(n - 1, height, k, dp);
    }

    // Tabulation - O(N * K) Time, O(N) Space
    public static int minimizeCost(int[] height, int n, int k) {
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minCost = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minCost = Math.min(minCost, jump);
                }
            }
            dp[i] = minCost;
        }
        return dp[n - 1];
    }

    // Space Optimization - O(N * K) Time, O(K) Space (Circular buffer of size K)
    public static int minimizeCostSpaceOpt(int[] height, int n, int k) {
        int[] buffer = new int[k];
        buffer[0] = 0;

        for (int i = 1; i < n; i++) {
            int minCost = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = buffer[(i - j) % k] + Math.abs(height[i] - height[i - j]);
                    minCost = Math.min(minCost, jump);
                }
            }
            buffer[i % k] = minCost;
        }
        return buffer[(n - 1) % k];
    }

    // --- LeetCode 403: Frog Jump (Stones Array) ---
    public static boolean canCross(int[] stones) {
        if (stones.length < 2 || stones[1] != 1)
            return false;
        return solve(1, 1, stones);
    }

    private static boolean solve(int idx, int k, int[] stones) {
        if (idx == stones.length - 1)
            return true;
        for (int i = idx + 1; i < stones.length; i++) {
            int jump = stones[i] - stones[idx];
            if (jump >= k - 1 && jump <= k + 1) {
                if (solve(i, jump, stones))
                    return true;
            }
        }
        return false;
    }
}
