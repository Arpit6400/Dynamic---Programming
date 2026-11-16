// GFG: https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
// LC:  https://leetcode.com/problems/partition-equal-subset-sum/ (related)

import java.util.Arrays;

public class dp14_Subset_Sum_Equal_To_K {
    public static void main(String[] args) {

    }

    // Recursion - subset sum equal to k
    private static boolean solve(int idx, int target, int[] arr) {
        if (target == 0)
            return true;
        if (idx == 0)
            return arr[idx] == target;

        boolean not_take = solve(idx - 1, target, arr);
        boolean take = false;
        if (target >= arr[idx]) {
            take = solve(idx - 1, target - arr[idx], arr);
        }
        return take || not_take;
    }

    static boolean isSubsetSumRecursion(int arr[], int sum) {
        return solve(0, sum, arr);
    }

    // Memoization
    private static boolean solveMemo(int idx, int target, int[] arr, int[][] dp) {
        if (target == 0)
            return true;
        if (idx == 0)
            return arr[idx] == target;
        if (dp[idx][target] != -1)
            return dp[idx][target] == 1;

        boolean not_take = solveMemo(idx - 1, target, arr, dp);
        boolean take = false;
        if (target >= arr[idx]) {
            take = solveMemo(idx - 1, target - arr[idx], arr, dp);
        }
        boolean result = take || not_take;
        dp[idx][target] = result ? 1 : 0;
        return result;
    }

    static boolean isSubsetSumMemo(int arr[], int sum) {
        int n = arr.length;
        int[][] dp = new int[n][sum + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n - 1, sum, arr, dp);
    }

    // Tabulation
    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;
        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= sum; target++) {
                boolean not_take = dp[idx - 1][target];
                boolean take = false;
                if (target >= arr[idx])
                    take = dp[idx - 1][target - arr[idx]];
                dp[idx][target] = take || not_take;
            }
        }
        return dp[n - 1][sum];
    }

    // Space Optimization
    static boolean isSubsetSumSpaceOpt(int arr[], int sum) {
        int n = arr.length;
        boolean[] prev = new boolean[sum + 1];
        prev[0] = true;
        if (arr[0] <= sum)
            prev[arr[0]] = true;

        for (int idx = 1; idx < n; idx++) {
            boolean[] curr = new boolean[sum + 1];
            curr[0] = true;
            for (int target = 1; target <= sum; target++) {
                boolean not_take = prev[target];
                boolean take = false;
                if (target >= arr[idx])
                    take = prev[target - arr[idx]];
                curr[target] = take || not_take;
            }
            prev = curr;
        }
        return prev[sum];
    }
}
