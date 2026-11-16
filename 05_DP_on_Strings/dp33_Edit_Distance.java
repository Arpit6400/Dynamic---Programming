// LC: https://leetcode.com/problems/edit-distance/

import java.util.Arrays;

public class dp33_Edit_Distance {
    public static void main(String[] args) {

    }

    // Recursion
    public int editdistanceRecursion(int i, int j, String S1, String S2) {
        if (i < 0)
            return j + 1;
        if (j < 0)
            return i + 1;
        if (S1.charAt(i) == S2.charAt(j))
            return editdistanceRecursion(i - 1, j - 1, S1, S2);
        int insertion = 1 + editdistanceRecursion(i, j - 1, S1, S2);
        int delete = 1 + editdistanceRecursion(i - 1, j, S1, S2);
        int replace = 1 + editdistanceRecursion(i - 1, j - 1, S1, S2);
        return Math.min(insertion, Math.min(delete, replace));
    }

    // Recursion - index shifting (1-indexed, easier for tabulation)
    public int editdistanceRecursion2(int i, int j, String S1, String S2) {
        if (i == 0)
            return j;
        if (j == 0)
            return i;
        if (S1.charAt(i - 1) == S2.charAt(j - 1))
            return editdistanceRecursion2(i - 1, j - 1, S1, S2);
        return 1 + Math.min(editdistanceRecursion2(i, j - 1, S1, S2),
                Math.min(editdistanceRecursion2(i - 1, j, S1, S2),
                        editdistanceRecursion2(i - 1, j - 1, S1, S2)));
    }

    // Memoization
    private int solveMemo(int i, int j, String S1, String S2, int[][] dp) {
        if (i == 0)
            return j;
        if (j == 0)
            return i;
        if (dp[i][j] != -1)
            return dp[i][j];

        if (S1.charAt(i - 1) == S2.charAt(j - 1))
            return dp[i][j] = solveMemo(i - 1, j - 1, S1, S2, dp);
        return dp[i][j] = 1 + Math.min(solveMemo(i, j - 1, S1, S2, dp),
                Math.min(solveMemo(i - 1, j, S1, S2, dp),
                        solveMemo(i - 1, j - 1, S1, S2, dp)));
    }

    public int minDistanceMemo(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n, m, word1, word2, dp);
    }

    // Tabulation
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++)
            dp[0][j] = j;
        for (int i = 0; i <= n; i++)
            dp[i][0] = i;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
            }
        }
        return dp[n][m];
    }

    // Space Optimization
    public int minDistanceSpaceOpt(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] prev = new int[m + 1];
        for (int j = 0; j <= m; j++)
            prev[j] = j;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            curr[0] = i;
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    curr[j] = prev[j - 1];
                else
                    curr[j] = 1 + Math.min(curr[j - 1], Math.min(prev[j], prev[j - 1]));
            }
            prev = curr;
        }
        return prev[m];
    }
}
