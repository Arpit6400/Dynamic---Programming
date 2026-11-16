// LC: https://leetcode.com/problems/longest-increasing-subsequence/

import java.util.Arrays;

public class dp41_Longest_Increasing_Subsequence {
    public static void main(String[] args) {

    }

    // New type DP - Longest Increasing Subsequence
    public static int longestIncreasingSubsequence(int idx, int prev_idx, int[] arr) {
        if (idx == arr.length)
            return 0;

        int not_take = 0 + longestIncreasingSubsequence(idx + 1, prev_idx, arr);
        int take = 0;
        if (prev_idx == -1 || arr[idx] > arr[prev_idx]) {
            take = 1 + longestIncreasingSubsequence(idx + 1, idx, arr);
        }
        return Math.max(take, not_take);
    }

    // Index shifting for DP (prev_idx shifted by +1 so -1 maps to 0)
    public static int longestIncreasingSubsequence2(int idx, int prev_idx, int[] arr) {
        if (idx == arr.length)
            return 0;

        int not_take = 0 + longestIncreasingSubsequence2(idx + 1, prev_idx, arr);
        int take = 0;
        if (prev_idx == 0 || arr[idx] > arr[prev_idx - 1]) {
            take = 1 + longestIncreasingSubsequence2(idx + 1, idx + 1, arr);
        }
        return Math.max(take, not_take);
    }

    // Memoization
    private static int solveMemo(int idx, int prev_idx, int[] arr, int[][] dp) {
        if (idx == arr.length)
            return 0;
        if (dp[idx][prev_idx + 1] != -1)
            return dp[idx][prev_idx + 1];

        int not_take = 0 + solveMemo(idx + 1, prev_idx, arr, dp);
        int take = 0;
        if (prev_idx == -1 || arr[idx] > arr[prev_idx]) {
            take = 1 + solveMemo(idx + 1, idx, arr, dp);
        }
        return dp[idx][prev_idx + 1] = Math.max(take, not_take);
    }

    public static int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(0, -1, nums, dp);
    }

    // Tabulation
    public static int longestIncreasingSubsequenceTabulation(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prev_idx = 0; prev_idx <= idx; prev_idx++) {

                int not_take = 0 + dp[idx + 1][prev_idx];

                int take = 0;
                if (prev_idx == 0 || arr[idx] > arr[prev_idx - 1]) {
                    take = 1 + dp[idx + 1][idx + 1];
                }

                dp[idx][prev_idx] = Math.max(take, not_take);
            }
        }
        return dp[0][0];
    }

    // Space Optimization
    public static int lengthOfLISSpaceOpt(int[] arr) {
        int n = arr.length;
        int[] next = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prev_idx = 0; prev_idx <= idx; prev_idx++) {
                int not_take = next[prev_idx];
                int take = 0;
                if (prev_idx == 0 || arr[idx] > arr[prev_idx - 1]) {
                    take = 1 + next[idx + 1];
                }
                curr[prev_idx] = Math.max(take, not_take);
            }
            next = curr.clone();
        }
        return next[0];
    }
}
