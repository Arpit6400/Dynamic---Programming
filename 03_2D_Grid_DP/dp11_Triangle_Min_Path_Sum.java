// LC: https://leetcode.com/problems/triangle/
// GFG: https://www.geeksforgeeks.org/problems/triangle-path-sum/1

import java.util.List;

public class dp11_Triangle_Min_Path_Sum {

    public static void main(String[] args) {

    }

    // Recursion
    public int minimumTotaTriangle(int row, int col, int[][] triangle) {
        if (row == triangle.length - 1)
            return triangle[triangle.length - 1][col];
        int down = triangle[row][col] + minimumTotaTriangle(row + 1, col, triangle);
        int diagonal = triangle[row][col] + minimumTotaTriangle(row + 1, col + 1, triangle);
        return Math.min(down, diagonal);
    }

    // Memoization
    public int minimumTotaTriangle(int row, int col, int[][] triangle, int[][] dp) {
        if (row == triangle.length - 1)
            return triangle[triangle.length - 1][col];
        if (dp[row][col] != -1)
            return dp[row][col];
        int down = triangle[row][col] + minimumTotaTriangle(row + 1, col, triangle, dp);
        int diagonal = triangle[row][col] + minimumTotaTriangle(row + 1, col + 1, triangle, dp);
        return dp[row][col] = Math.min(down, diagonal);
    }

    // Tabulation - O(N^2) Time, O(N^2) Space
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[][] dp = new int[m][m];
        for (int k = 0; k < m; k++) {
            dp[m - 1][k] = triangle.get(m - 1).get(k);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diagonal = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diagonal);
            }
        }
        return dp[0][0];
    }

    // Space Optimization - O(N^2) Time, O(N) Space
    public int minimumTotalSpaceOpt(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[] front = new int[m];
        for (int k = 0; k < m; k++) {
            front[k] = triangle.get(m - 1).get(k);
        }

        for (int i = m - 2; i >= 0; i--) {
            int[] curr = new int[i + 1];
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + front[j];
                int diagonal = triangle.get(i).get(j) + front[j + 1];
                curr[j] = Math.min(down, diagonal);
            }
            front = curr;
        }
        return front[0];
    }
}
