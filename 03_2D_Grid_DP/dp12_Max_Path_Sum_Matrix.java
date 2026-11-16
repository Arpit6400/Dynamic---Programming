// LC: https://leetcode.com/problems/minimum-falling-path-sum/
// GFG: https://www.geeksforgeeks.org/problems/maximum-path-sum-in-matrix1005/1

import java.util.Arrays;

public class dp12_Max_Path_Sum_Matrix {

    public static void main(String[] args) {
        int[][] mat = {
                { 1, 2, 10, 4 },
                { 100, 3, 2, 1 },
                { 1, 1, 20, 2 },
                { 1, 2, 2, 1 }
        };
        dp12_Max_Path_Sum_Matrix solver = new dp12_Max_Path_Sum_Matrix();
        System.out.println("Tabulation: " + solver.maxPathSumInMatrixTabulation(mat));
        System.out.println("Space Opt: " + solver.maxPathSumInMatrixSpaceOpt(mat));
    }

    // Recursion
    public int maxPathSumInMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            max = Math.max(max, solve(m - 1, j, mat));
        }
        return max;
    }

    private int solve(int row, int col, int[][] mat) {
        if (col < 0 || col >= mat[0].length)
            return -1_000_000_000;
        if (row == 0)
            return mat[0][col];
        int up = mat[row][col] + solve(row - 1, col, mat);
        int ld = mat[row][col] + solve(row - 1, col - 1, mat);
        int rd = mat[row][col] + solve(row - 1, col + 1, mat);
        return Math.max(up, Math.max(ld, rd));
    }

    // Memoization
    public int maxPathSumInMatrixMemo(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            max = Math.max(max, solveMemo(m - 1, j, mat, dp));
        }
        return max;
    }

    private int solveMemo(int row, int col, int[][] mat, int[][] dp) {
        if (col < 0 || col >= mat[0].length)
            return -1_000_000_000;
        if (row == 0)
            return mat[0][col];
        if (dp[row][col] != -1)
            return dp[row][col];
        int up = mat[row][col] + solveMemo(row - 1, col, mat, dp);
        int ld = mat[row][col] + solveMemo(row - 1, col - 1, mat, dp);
        int rd = mat[row][col] + solveMemo(row - 1, col + 1, mat, dp);
        return dp[row][col] = Math.max(up, Math.max(ld, rd));
    }

    // Tabulation - O(M * N) Time, O(M * N) Space
    public int maxPathSumInMatrixTabulation(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        int mod = -1_000_000_000;

        for (int k = 0; k < n; k++) {
            dp[0][k] = mat[0][k];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = mat[i][j] + dp[i - 1][j];
                int ld = mat[i][j] + (j == 0 ? mod : dp[i - 1][j - 1]);
                int rd = mat[i][j] + (j == n - 1 ? mod : dp[i - 1][j + 1]);
                dp[i][j] = Math.max(up, Math.max(ld, rd));
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, dp[m - 1][j]);
        }
        return ans;
    }

    // Space Optimization - O(M * N) Time, O(N) Space
    public int maxPathSumInMatrixSpaceOpt(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] prev = new int[n];
        int mod = -1_000_000_000;

        for (int k = 0; k < n; k++) {
            prev[k] = mat[0][k];
        }

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                int up = mat[i][j] + prev[j];
                int ld = mat[i][j] + (j == 0 ? mod : prev[j - 1]);
                int rd = mat[i][j] + (j == n - 1 ? mod : prev[j + 1]);
                curr[j] = Math.max(up, Math.max(ld, rd));
            }
            prev = curr;
        }

        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, prev[j]);
        }
        return ans;
    }
}
