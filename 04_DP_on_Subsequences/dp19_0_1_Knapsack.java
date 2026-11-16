// GFG: https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1

import java.util.Arrays;

public class dp19_0_1_Knapsack {
    public static void main(String[] args) {

    }

    // Recursion - 0/1 Knapsack problem
    public int zeroOneKnapsack(int idx, int wt, int[] val, int[] w) {
        if (idx == 0) {
            if (w[0] <= wt)
                return val[idx];
            else
                return 0;
        }
        int not_take = 0 + zeroOneKnapsack(idx - 1, wt, val, w);
        int take = Integer.MIN_VALUE;
        if (w[idx] <= wt)
            take = val[idx] + zeroOneKnapsack(idx - 1, wt - w[idx], val, w);
        return Math.max(not_take, take);
    }

    // Memoization
    private int solveMemo(int idx, int wt, int[] val, int[] w, int[][] dp) {
        if (idx == 0) {
            if (w[0] <= wt)
                return val[0];
            else
                return 0;
        }
        if (dp[idx][wt] != -1)
            return dp[idx][wt];

        int not_take = solveMemo(idx - 1, wt, val, w, dp);
        int take = Integer.MIN_VALUE;
        if (w[idx] <= wt)
            take = val[idx] + solveMemo(idx - 1, wt - w[idx], val, w, dp);
        return dp[idx][wt] = Math.max(not_take, take);
    }

    public int zeroOneKnapsackMemo(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n - 1, W, val, wt, dp);
    }

    // Tabulation
    public int knapsack(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for (int i = wt[0]; i <= W; i++)
            dp[0][i] = val[0];

        for (int idx = 1; idx < n; idx++) {
            for (int weight = 0; weight <= W; weight++) {
                int not_take = dp[idx - 1][weight];
                int take = Integer.MIN_VALUE;
                if (wt[idx] <= weight)
                    take = val[idx] + dp[idx - 1][weight - wt[idx]];
                dp[idx][weight] = Math.max(not_take, take);
            }
        }
        return dp[n - 1][W];
    }

    // Space Optimization
    public int knapsackSpaceOpt(int W, int val[], int wt[]) {
        int n = val.length;
        int[] prev = new int[W + 1];
        for (int i = wt[0]; i <= W; i++)
            prev[i] = val[0];

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[W + 1];
            for (int weight = W; weight >= 0; weight--) {
                int not_take = prev[weight];
                int take = Integer.MIN_VALUE;
                if (wt[idx] <= weight)
                    take = val[idx] + prev[weight - wt[idx]];
                curr[weight] = Math.max(not_take, take);
            }
            prev = curr;
        }
        return prev[W];
    }
}
