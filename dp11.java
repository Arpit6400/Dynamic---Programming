import java.util.List;

public class dp11 {
    public static void main(String[] args) {

    }

    // Recursion
    public int minimumTotaTriangle(int row, int col, int[][] triangle) {
        if (row == triangle.length)
            return triangle[triangle.length - 1][col];
        int down = triangle[row][col] + minimumTotaTriangle(row + 1, col, triangle);
        int diagonal = triangle[row][col] + minimumTotaTriangle(row + 1, col + 1, triangle);
        return Math.min(down, diagonal);
    }

    // Memoization
    public int minimumTotaTriangle(int row, int col, int[][] triangle, int[][] dp) {
        if (row == triangle.length)
            return triangle[triangle.length - 1][col];
        if (dp[row][col] != -1)
            return dp[row][col];
        int down = triangle[row][col] + minimumTotaTriangle(row + 1, col, triangle);
        int diagonal = triangle[row][col] + minimumTotaTriangle(row + 1, col + 1, triangle);
        dp[row][col] = Math.min(down, diagonal);
        return dp[row][col];
    }

    // Tabulation
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
}
