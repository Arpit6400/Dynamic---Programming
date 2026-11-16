public class dp10 {
    public static void main(String[] args) {
        
    }
    // Recursion
    int minPathSum(int i, int j, int[][] grid){
        if(i == 0 && j == 0) {
            return grid[0][0];
        }
        if( i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        int up = grid[i][j] + minPathSum(i-1, j, grid);
        int left = grid[i][j] + minPathSum(i, j-1, grid);
        return Math.min(up, left);
    }
    // Memoization
    int minPathSum(int i, int j, int[][] grid, int[][] dp){
        if(i == 0 && j == 0) {
            return grid[0][0];
        }
        if( i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if(dp[i][j]!= -1) return dp[i][j];
        int up = grid[i][j] + minPathSum(i-1, j, grid, dp);
        int left = grid[i][j] + minPathSum(i, j-1, grid, dp);
        dp[i][j] = Math.min(up, left);
        return dp[i][j];
    }
    // Tabulation
    int mintPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0){
                    dp[0][0] = grid[0][0];
                }
                else{
                    int up = grid[i][j];
                    if(i > 0) up+= dp[i-1][j];
                    else up = Integer.MAX_VALUE;

                    int left = grid[i][j];
                    if( j > 0) left += dp[i][j-1];
                    else left = Integer.MAX_VALUE;

                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[m-1][n-1];
    }
}
