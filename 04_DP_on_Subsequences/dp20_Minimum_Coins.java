// LC: https://leetcode.com/problems/coin-change/

import java.util.Arrays;

public class dp20_Minimum_Coins {
    public static void main(String[] args) {

    }

    // Recursion - Minimum Coins
    int minimumCoins(int idx, int target, int[] arr) {
        if (idx == 0) {
            if (target % arr[0] == 0)
                return target / arr[0];
            else
                return (int) 1e9;
        }
        int not_take = 0 + minimumCoins(idx - 1, target, arr);
        int take = (int) 1e9;
        if (arr[idx] <= target)
            take = 1 + minimumCoins(idx, target - arr[idx], arr);
        return Math.min(take, not_take);
    }

    // Memoization
    private int solveMemo(int idx, int target, int[] arr, int[][] dp) {
        if (idx == 0) {
            if (target % arr[0] == 0)
                return target / arr[0];
            else
                return (int) 1e9;
        }
        if (dp[idx][target] != -1)
            return dp[idx][target];

        int not_take = solveMemo(idx - 1, target, arr, dp);
        int take = (int) 1e9;
        if (arr[idx] <= target)
            take = 1 + solveMemo(idx, target - arr[idx], arr, dp);
        return dp[idx][target] = Math.min(take, not_take);
    }

    public int coinChangeMemo(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        int result = solveMemo(n - 1, amount, coins, dp);
        return result != (int) 1e9 ? result : -1;
    }

    // Tabulation
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                dp[0][t] = t / coins[0];
            else
                dp[0][t] = (int) 1e9;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {
                int not_take = dp[idx - 1][target];
                int take = (int) 1e9;
                if (coins[idx] <= target)
                    take = 1 + dp[idx][target - coins[idx]];
                dp[idx][target] = Math.min(take, not_take);
            }
        }
        return dp[n - 1][amount] != (int) 1e9 ? dp[n - 1][amount] : -1;
    }

    // Space Optimization
    public int coinChangeSpaceOpt(int[] coins, int amount) {
        int n = coins.length;
        int[] prev = new int[amount + 1];
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                prev[t] = t / coins[0];
            else
                prev[t] = (int) 1e9;
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[amount + 1];
            for (int target = 0; target <= amount; target++) {
                int not_take = prev[target];
                int take = (int) 1e9;
                if (coins[idx] <= target)
                    take = 1 + curr[target - coins[idx]];
                curr[target] = Math.min(take, not_take);
            }
            prev = curr;
        }
        return prev[amount] != (int) 1e9 ? prev[amount] : -1;
    }
}
