// LC: https://leetcode.com/problems/unique-paths/
// GFG: https://www.geeksforgeeks.org/problems/number-of-unique-paths5339/1

import java.util.Arrays;

public class dp8_Unique_Paths {

    public static void main(String[] args) {
        dp8_Unique_Paths solver = new dp8_Unique_Paths();
        int m = 3, n = 7;

        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        System.out.println("Memoization: " + solver.totalUniquePaths(m - 1, n - 1, dp));
        System.out.println("Tabulation: " + solver.totalUniquePathsTabulation(m, n));
        System.out.println("Space Opt: " + solver.totalUniquePathsSpaceOpt(m, n));
    }

    // Recursion
    public int totalUniquePaths(int i, int j) {
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        int up = totalUniquePaths(i - 1, j);
        int left = totalUniquePaths(i, j - 1);
        return up + left;
    }

    // Memoization
    public int totalUniquePaths(int i, int j, int dp[][]) {
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int up = totalUniquePaths(i - 1, j, dp);
        int left = totalUniquePaths(i, j - 1, dp);
        return dp[i][j] = up + left;
    }

    // Tabulation - O(M * N) Time, O(M * N) Space
    public int totalUniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                } else {
                    int up = i > 0 ? dp[i - 1][j] : 0;
                    int left = j > 0 ? dp[i][j - 1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // Space Optimization - O(M * N) Time, O(N) Space
    public int totalUniquePathsSpaceOpt(int m, int n) {
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[0] = 1;
                } else {
                    int up = prev[j];
                    int left = j > 0 ? curr[j - 1] : 0;
                    curr[j] = up + left;
                }
            }
            prev = curr;
        }
        return prev[n - 1];
    }
}
