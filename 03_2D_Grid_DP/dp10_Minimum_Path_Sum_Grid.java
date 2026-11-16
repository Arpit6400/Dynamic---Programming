// LC: https://leetcode.com/problems/minimum-path-sum/

public class dp10_Minimum_Path_Sum_Grid {

    public static void main(String[] args) {
        int[][] grid = {
                { 1, 3, 1 },
                { 1, 5, 1 },
                { 4, 2, 1 }
        };
        dp10_Minimum_Path_Sum_Grid solver = new dp10_Minimum_Path_Sum_Grid();
        System.out.println("Tabulation: " + solver.minPathSum(grid));
        System.out.println("Space Opt: " + solver.minPathSumSpaceOpt(grid));
    }

    // Recursion
    int minPathSumRec(int i, int j, int[][] grid) {
        if (i == 0 && j == 0)
            return grid[0][0];
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;

        int up = minPathSumRec(i - 1, j, grid);
        int left = minPathSumRec(i, j - 1, grid);
        return grid[i][j] + Math.min(up, left);
    }

    // Memoization
    int minPathSumMemo(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0)
            return grid[0][0];
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;
        if (dp[i][j] != -1)
            return dp[i][j];

        int up = minPathSumMemo(i - 1, j, grid, dp);
        int left = minPathSumMemo(i, j - 1, grid, dp);
        return dp[i][j] = grid[i][j] + Math.min(up, left);
    }

    // Tabulation - O(M * N) Time, O(M * N) Space
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0];
                } else {
                    int up = (i > 0) ? dp[i - 1][j] : Integer.MAX_VALUE;
                    int left = (j > 0) ? dp[i][j - 1] : Integer.MAX_VALUE;
                    dp[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // Space Optimization - O(M * N) Time, O(N) Space
    public int minPathSumSpaceOpt(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[0] = grid[0][0];
                } else {
                    int up = (i > 0) ? prev[j] : Integer.MAX_VALUE;
                    int left = (j > 0) ? curr[j - 1] : Integer.MAX_VALUE;
                    curr[j] = grid[i][j] + Math.min(up, left);
                }
            }
            prev = curr;
        }
        return prev[n - 1];
    }
}
