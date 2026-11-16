// GFG: https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1

import java.util.Arrays;

public class dp23_Unbounded_Knapsack {
    public static void main(String[] args) {
    }

    // Recursion - Unbounded Knapsack (items can be picked any number of times)
    public int unBoundedKnapsack(int idx, int wt, int[] val, int[] w) {
        if (idx == 0)
            return (wt / w[0]) * val[0];

        int not_take = 0 + unBoundedKnapsack(idx - 1, wt, val, w);
        int take = Integer.MIN_VALUE;
        if (w[idx] <= wt)
            take = val[idx] + unBoundedKnapsack(idx, wt - w[idx], val, w);
        return Math.max(not_take, take);
    }

    // Memoization
    private int solveMemo(int idx, int wt, int[] val, int[] w, int[][] dp) {
        if (idx == 0)
            return (wt / w[0]) * val[0];
        if (dp[idx][wt] != -1)
            return dp[idx][wt];

        int not_take = solveMemo(idx - 1, wt, val, w, dp);
        int take = Integer.MIN_VALUE;
        if (w[idx] <= wt)
            take = val[idx] + solveMemo(idx, wt - w[idx], val, w, dp);
        return dp[idx][wt] = Math.max(not_take, take);
    }

    public int unBoundedKnapsackMemo(int capacity, int[] val, int[] wt) {
        int n = val.length;
        int[][] dp = new int[n][capacity + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n - 1, capacity, val, wt, dp);
    }

    // Tabulation
    public int knapSack(int val[], int wt[], int capacity) {
        int n = val.length;
        int[][] dp = new int[n][capacity + 1];
        for (int i = 0; i <= capacity; i++)
            dp[0][i] = (i / wt[0]) * val[0];

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= capacity; target++) {
                int not_take = dp[idx - 1][target];
                int take = Integer.MIN_VALUE;
                if (wt[idx] <= target)
                    take = val[idx] + dp[idx][target - wt[idx]];
                dp[idx][target] = Math.max(not_take, take);
            }
        }
        return dp[n - 1][capacity];
    }

    // Space Optimization
    public int knapSackSpaceOpt(int val[], int wt[], int capacity) {
        int n = val.length;
        int[] prev = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++)
            prev[i] = (i / wt[0]) * val[0];

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[capacity + 1];
            for (int target = 0; target <= capacity; target++) {
                int not_take = prev[target];
                int take = Integer.MIN_VALUE;
                if (wt[idx] <= target)
                    take = val[idx] + curr[target - wt[idx]];
                curr[target] = Math.max(not_take, take);
            }
            prev = curr;
        }
        return prev[capacity];
    }
}
