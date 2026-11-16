// LC: https://leetcode.com/problems/wildcard-matching/

import java.util.Arrays;

public class dp34_Wildcard_Matching {
    public static void main(String[] args) {

    }

    // Recursion - wildcard matching (original indices)
    public boolean wildcardmatching(int i, int j, String s, String p) {
        if (i < 0 && j < 0)
            return true;
        if (i < 0 && j >= 0) {
            for (int k = 0; k <= j; k++)
                if (p.charAt(k) != '*')
                    return false;
            return true;
        }
        if (j < 0 && i >= 0)
            return false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
            return wildcardmatching(i - 1, j - 1, s, p);
        if (p.charAt(j) == '*')
            return wildcardmatching(i - 1, j, s, p) || wildcardmatching(i, j - 1, s, p);
        return false;
    }

    // Recursion - index shifting (1-indexed)
    public boolean wildcardmatching2(int i, int j, String s, String p) {
        if (i == 0 && j == 0)
            return true;
        if (i == 0 && j > 0) {
            for (int k = 1; k <= j; k++)
                if (p.charAt(k - 1) != '*')
                    return false;
            return true;
        }
        if (j == 0 && i > 0)
            return false;
        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
            return wildcardmatching2(i - 1, j - 1, s, p);
        if (p.charAt(j - 1) == '*')
            return wildcardmatching2(i - 1, j, s, p) || wildcardmatching2(i, j - 1, s, p);
        return false;
    }

    // Memoization
    private Boolean solveMemo(int i, int j, String s, String p, Boolean[][] dp) {
        if (i == 0 && j == 0)
            return true;
        if (i == 0 && j > 0) {
            for (int k = 1; k <= j; k++)
                if (p.charAt(k - 1) != '*')
                    return false;
            return true;
        }
        if (j == 0 && i > 0)
            return false;
        if (dp[i][j] != null)
            return dp[i][j];

        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
            return dp[i][j] = solveMemo(i - 1, j - 1, s, p, dp);
        if (p.charAt(j - 1) == '*')
            return dp[i][j] = solveMemo(i - 1, j, s, p, dp) || solveMemo(i, j - 1, s, p, dp);
        return dp[i][j] = false;
    }

    public boolean isMatchMemo(String s, String p) {
        int n = s.length(), m = p.length();
        Boolean[][] dp = new Boolean[n + 1][m + 1];
        return solveMemo(n, m, s, p, dp);
    }

    // Tabulation
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int j = 1; j <= m; j++) {
            boolean flag = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0][j] = flag;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                else
                    dp[i][j] = false;
            }
        }
        return dp[n][m];
    }

    // Space Optimization
    public boolean isMatchSpaceOpt(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[] prev = new boolean[m + 1];
        prev[0] = true;
        for (int j = 1; j <= m; j++) {
            boolean flag = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            prev[j] = flag;
        }

        for (int i = 1; i <= n; i++) {
            boolean[] curr = new boolean[m + 1];
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    curr[j] = prev[j - 1];
                else if (p.charAt(j - 1) == '*')
                    curr[j] = prev[j] || curr[j - 1];
                else
                    curr[j] = false;
            }
            prev = curr;
        }
        return prev[m];
    }
}
