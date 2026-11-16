// LC: https://leetcode.com/problems/distinct-subsequences/

import java.util.Arrays;

public class dp32_Distinct_Subsequences {
    public static void main(String[] args) {

    }

    // Recursion (original indices)
    int distinctSubsequences(int i, int j, String s, String t) {
        if (j < 0)
            return 1;
        if (i < 0)
            return 0;
        if (s.charAt(i) == t.charAt(j))
            return distinctSubsequences(i - 1, j - 1, s, t) + distinctSubsequences(i - 1, j, s, t);
        return distinctSubsequences(i - 1, j, s, t);
    }

    // Recursion - index shifting (1-indexed, easier for tabulation)
    int distinctSubsequences2(int i, int j, String s, String t) {
        if (j == 0)
            return 1;
        if (i == 0)
            return 0;
        if (s.charAt(i - 1) == t.charAt(j - 1))
            return distinctSubsequences2(i - 1, j - 1, s, t) + distinctSubsequences2(i - 1, j, s, t);
        return distinctSubsequences2(i - 1, j, s, t);
    }

    // Memoization (on shifted index version)
    private int solveMemo(int i, int j, String s, String t, int[][] dp) {
        if (j == 0)
            return 1;
        if (i == 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i - 1) == t.charAt(j - 1))
            return dp[i][j] = solveMemo(i - 1, j - 1, s, t, dp) + solveMemo(i - 1, j, s, t, dp);
        return dp[i][j] = solveMemo(i - 1, j, s, t, dp);
    }

    public int numDistinctMemo(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n, m, s, t, dp);
    }

    // Tabulation
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][m];
    }

    // Space Optimization
    public int numDistinctSpaceOpt(String s, String t) {
        int n = s.length(), m = t.length();
        int[] prev = new int[m + 1];
        prev[0] = 1;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            curr[0] = 1;
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    curr[j] = prev[j - 1] + prev[j];
                else
                    curr[j] = prev[j];
            }
            prev = curr;
        }
        return prev[m];
    }
}
