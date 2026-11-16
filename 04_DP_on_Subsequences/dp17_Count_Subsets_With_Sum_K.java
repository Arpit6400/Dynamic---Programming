// GFG: https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1

import java.util.Arrays;

public class dp17_Count_Subsets_With_Sum_K {
    public static void main(String[] args) {
        System.out.println(perfectSum(new int[] { 0, 10, 0 }, 0));
    }

    // Recursion
    public int countSubsetsWithSumK(int idx, int sum, int[] arr) {
        if (sum == 0)
            return 1;
        if (idx == 0) {
            if (arr[idx] == sum)
                return 1;
            else
                return 0;
        }
        int not_take = countSubsetsWithSumK(idx - 1, sum, arr);
        int take = 0;
        if (arr[idx] <= sum)
            take = countSubsetsWithSumK(idx - 1, sum - arr[idx], arr);
        return take + not_take;
    }

    // Memoization
    private int countMemo(int idx, int sum, int[] arr, int[][] dp) {
        if (sum == 0)
            return 1;
        if (idx == 0)
            return (arr[idx] == sum) ? 1 : 0;
        if (dp[idx][sum] != -1)
            return dp[idx][sum];

        int not_take = countMemo(idx - 1, sum, arr, dp);
        int take = 0;
        if (arr[idx] <= sum)
            take = countMemo(idx - 1, sum - arr[idx], arr, dp);
        return dp[idx][sum] = (take + not_take) % 1_000_000_007;
    }

    public int countSubsetsMemo(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return countMemo(n - 1, target, arr, dp);
    }

    // Tabulation
    public static int perfectSum(int[] arr, int target) {
        int n = arr.length;
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = 1;
        if (arr[0] <= target)
            dp[0][arr[0]] += 1;
        if (arr[0] == 0)
            dp[0][0] = 2;

        for (int idx = 1; idx < n; idx++) {
            for (int sum = 0; sum <= target; sum++) {
                int not_take = dp[idx - 1][sum];
                int take = 0;
                if (arr[idx] <= sum)
                    take = dp[idx - 1][sum - arr[idx]];
                dp[idx][sum] = (take + not_take) % MOD;
            }
        }
        return dp[n - 1][target];
    }

    // Space Optimization
    public int perfectSumSpaceOpt(int[] arr, int target) {
        int n = arr.length;
        int MOD = 1_000_000_007;
        int[] prev = new int[target + 1];
        prev[0] = 1;
        if (arr[0] <= target)
            prev[arr[0]] += 1;
        if (arr[0] == 0)
            prev[0] = 2;

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[target + 1];
            curr[0] = 1;
            for (int sum = 0; sum <= target; sum++) {
                int not_take = prev[sum];
                int take = 0;
                if (arr[idx] <= sum)
                    take = prev[sum - arr[idx]];
                curr[sum] = (take + not_take) % MOD;
            }
            prev = curr;
        }
        return prev[target];
    }
}
