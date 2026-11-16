import java.util.Arrays;

public class dp13_Cherry_Pickup_II {
    public static void main(String[] args) {

    }

    // Recursion
    public int cherryPickupII(int i, int j1, int j2, int r, int c, int[][] grid) {
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) {
            return -1_000_000_000;
        }
        if (i == r - 1) {
            if (j1 == j2)
                return grid[i][j1];
            else
                return grid[i][j1] + grid[i][j2];
        }
        int max = 0;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = grid[i][j1];
                if (j1 != j2)
                    value += grid[i][j2];
                value += cherryPickupII(i + 1, j1 + dj1, j2 + dj2, r, c, grid);
                max = Math.max(max, value);
            }
        }
        return max;
    }

    // Memoization
    public int cherryPickupII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        for (int[][] rows : dp) {
            for (int[] row : rows) {
                Arrays.fill(row, -1);
            }
        }
        return down(grid, 0, 0, n - 1, m, n, dp);
    }

    public int down(int[][] grid, int i, int j1, int j2, int r, int c, int[][][] dp) {
        if (j1 < 0 || j2 < 0 || j1 >= c || j2 >= c)
            return -1000;
        if (i == r - 1) {
            if (j1 == j2)
                return dp[i][j1][j2] = grid[i][j1];
            else
                return dp[i][j1][j2] = grid[i][j1] + grid[i][j2];
        }
        if (dp[i][j1][j2] != -1)
            return dp[i][j1][j2];
        int max = 0;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int val = 0;
                if (j1 == j2)
                    val = grid[i][j1];
                else
                    val = grid[i][j1] + grid[i][j2];
                val += down(grid, i + 1, j1 + dj1, j2 + dj2, r, c, dp);
                max = Math.max(max, val);
            }
        }
        return dp[i][j1][j2] = max;
    }

    // Tabulation
    public int cherryPuckup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                if (j1 == j2)
                    dp[m - 1][j1][j2] = grid[m - 1][j1];
                else
                    dp[m - 1][j1][j2] = grid[m - 1][j1] + grid[m - 1][j2];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {
                    int max = -1_000_000_000;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int value = grid[i][j1];
                            if (j1 != j2)
                                value += grid[i][j2];
                            if (j1 + dj1 >= 0 && j1 + dj1 < n && j2 + dj2 >= 0 && j2 + dj2 < n) {
                                value += dp[i + 1][j1 + dj1][j2 + dj2];
                            } else
                                value += -1_000_000_000;
                            max = Math.max(max, value);
                        }
                    }
                    dp[i][j1][j2] = max;
                }

            }
        }
        return dp[0][0][n - 1];
    }
}
