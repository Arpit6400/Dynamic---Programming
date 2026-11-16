// LC: https://leetcode.com/problems/count-square-submatrices-with-all-ones/
// GFG: https://www.geeksforgeeks.org/problems/count-square-submatrices-with-all-ones/1

import java.util.Arrays;

public class dp56_Count_Square_Submatrices_With_All_Ones {

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 1, 1, 1 }
        };
        System.out.println("Total Square Submatrices: " + countSquares(matrix));
    }

    // Memoization approach
    // solve(i, j) = max side length of square with bottom-right corner at (i, j)
    private static int solveMemo(int i, int j, int[][] matrix, int[][] dp) {
        if (i < 0 || j < 0)
            return 0;
        if (matrix[i][j] == 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int top = solveMemo(i - 1, j, matrix, dp);
        int left = solveMemo(i, j - 1, matrix, dp);
        int diag = solveMemo(i - 1, j - 1, matrix, dp);

        return dp[i][j] = 1 + Math.min(top, Math.min(left, diag));
    }

    public static int countSquaresMemo(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int totalSquares = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                totalSquares += solveMemo(i, j, matrix, dp);
            }
        }
        return totalSquares;
    }

    // Tabulation - DP on Squares
    // Concept:
    // dp[i][j] = size of the largest square submatrix ending at (i, j) as
    // bottom-right corner.
    // If matrix[i][j] == 1:
    // dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    // Total number of squares = sum of all dp[i][j] in the matrix.
    // Time Complexity: O(N * M), Space Complexity: O(N * M)
    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        // Base cases: first row and first column
        for (int j = 0; j < m; j++)
            dp[0][j] = matrix[0][j];
        for (int i = 0; i < n; i++)
            dp[i][0] = matrix[i][0];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // Sum up all values
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
    }

    // Space Optimization
    // Space Complexity: O(M)
    public static int countSquaresSpaceOpt(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] prev = new int[m];
        int total = 0;

        for (int i = 0; i < n; i++) {
            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        curr[j] = 1;
                    } else {
                        curr[j] = 1 + Math.min(prev[j], Math.min(curr[j - 1], prev[j - 1]));
                    }
                    total += curr[j];
                }
            }
            prev = curr;
        }
        return total;
    }
}
