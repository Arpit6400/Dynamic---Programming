// LC: https://leetcode.com/problems/unique-paths-ii/

public class dp9_Unique_Paths_With_Obstacles {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 0 }
        };
        dp9_Unique_Paths_With_Obstacles solver = new dp9_Unique_Paths_With_Obstacles();
        System.out.println("Tabulation: " + solver.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Space Opt: " + solver.uniquePathsWithObstaclesSpaceOpt(obstacleGrid));
    }

    // Recursion
    public int totalUniquePathsWithObstacle(int i, int j, int[][] mat) {
        if (i >= 0 && j >= 0 && (mat[i][j] == -1 || mat[i][j] == 1))
            return 0;
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        int up = totalUniquePathsWithObstacle(i - 1, j, mat);
        int left = totalUniquePathsWithObstacle(i, j - 1, mat);
        return up + left;
    }

    // Memoization
    public int totalUniquePathsWithObstacle(int i, int j, int[][] mat, int dp[][]) {
        if (i >= 0 && j >= 0 && (mat[i][j] == -1 || mat[i][j] == 1))
            return 0;
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int up = totalUniquePathsWithObstacle(i - 1, j, mat, dp);
        int left = totalUniquePathsWithObstacle(i, j - 1, mat, dp);
        return dp[i][j] = up + left;
    }

    // Tabulation - O(M * N) Time, O(M * N) Space
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1 || obstacleGrid[i][j] == -1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
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
    public int uniquePathsWithObstaclesSpaceOpt(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1 || obstacleGrid[i][j] == -1) {
                    curr[j] = 0;
                } else if (i == 0 && j == 0) {
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
