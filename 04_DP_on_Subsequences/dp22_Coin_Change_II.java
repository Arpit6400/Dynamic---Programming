// LC: https://leetcode.com/problems/coin-change-ii/

import java.util.Arrays;

public class dp22_Coin_Change_II {
    public static void main(String[] args) {

    }

    // Recursion - Coin Change 2
    public int coinChange2(int idx, int target, int[] arr) {
        if (idx == 0) {
            if (target % arr[0] == 0)
                return 1;
            else
                return 0;
        }
        int not_take = coinChange2(idx - 1, target, arr);
        int take = 0;
        if (arr[idx] <= target)
            take = coinChange2(idx, target - arr[idx], arr);
        return take + not_take;
    }

    // Memoization
    private int solveMemo(int idx, int target, int[] arr, int[][] dp) {
        if (idx == 0)
            return (target % arr[0] == 0) ? 1 : 0;
        if (dp[idx][target] != -1)
            return dp[idx][target];

        int not_take = solveMemo(idx - 1, target, arr, dp);
        int take = 0;
        if (arr[idx] <= target)
            take = solveMemo(idx, target - arr[idx], arr, dp);
        return dp[idx][target] = take + not_take;
    }

    public int changeMemo(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n - 1, amount, coins, dp);
    }

    // Tabulation
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                dp[0][t] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {
                int not_take = dp[idx - 1][target];
                int take = 0;
                if (coins[idx] <= target)
                    take = dp[idx][target - coins[idx]];
                dp[idx][target] = take + not_take;
            }
        }
        return dp[n - 1][amount];
    }

    // Space Optimization
    public int changeSpaceOpt(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount + 1];
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                prev[t] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[amount + 1];
            for (int target = 0; target <= amount; target++) {
                int not_take = prev[target];
                int take = 0;
                if (coins[idx] <= target)
                    take = curr[target - coins[idx]];
                curr[target] = take + not_take;
            }
            prev = curr;
        }
        return prev[amount];
    }
}
