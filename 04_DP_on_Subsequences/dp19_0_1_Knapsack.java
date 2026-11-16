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
        if (w[idx] <= wt) {
            take = val[idx] + zeroOneKnapsack(idx - 1, wt - w[idx], val, w);
        }
        return Math.max(not_take, take);
    }

    // Mamoization - Agent Add

    // Tabulation
    public int knapsack(int W, int val[], int wt[]) {
        // code here
        int n = val.length;
        int[][] dp = new int[n][W + 1];

        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }

        for (int idx = 1; idx < n; idx++) {
            for (int weight = 0; weight <= W; weight++) {
                int not_take = 0 + dp[idx - 1][weight];
                int take = Integer.MIN_VALUE;
                if (wt[idx] <= weight) {
                    take = val[idx] + dp[idx - 1][weight - wt[idx]];
                }
                dp[idx][weight] = Math.max(not_take, take);
            }
        }
        return dp[n-1][W];
    }
}
