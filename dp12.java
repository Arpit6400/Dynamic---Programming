import java.util.Arrays;

public class dp12 {
    public static void main(String[] args) {
        
    }
    //Recursion
    public int maxPathSumInMatrix(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        int max = Integer.MIN_VALUE;
        for(int j = 0;j < n;j++){
            int val = solve(m-1, j, mat);
            max = Math.max(max, val);
        }
        return max;
    }
    private int solve(int row, int col, int[][] mat) {
        if(col < 0 || col >= mat[0].length) return -1_000_000_000;
        if(row == 0) return mat[0][col];
        int up = mat[row][col] + solve(row - 1, col, mat);
        int LD = mat[row][col] + solve(row - 1, col - 1, mat);
        int RD = mat[row][col] + solve(row - 1, col + 1, mat);
        return Math.max(up, Math.max(LD, RD));
    }
    // Memoization
    public int maxPathSumInMatrixMemo(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        for(int[] row : dp) Arrays.fill(row, -1);
        int max = Integer.MIN_VALUE;
        for(int j = 0; j < n; j++){
            int val = solveMemo(m-1, j, mat, dp);
            max = Math.max(max, val);
        }
        return max;
    }
    private int solveMemo(int row, int col, int[][] mat, int[][] dp) {
        if(col < 0 || col >= mat[0].length) return -1_000_000_000;
        if(row == 0) return mat[0][col];
        if(dp[row][col] != -1) return dp[row][col];
        int up = mat[row][col] + solveMemo(row - 1, col, mat, dp);
        int LD = mat[row][col] + solveMemo(row - 1, col - 1, mat, dp);
        int RD = mat[row][col] + solveMemo(row - 1, col + 1, mat, dp);
        dp[row][col] = Math.max(up, Math.max(LD, RD));
        return dp[row][col];
    }
    // Tabulation (Striver's style)
    public int maxPathSumInMatrixTabulation(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        int mod = -1_000_000_000;
        for (int k = 0; k < n; k++) {
            dp[0][k] = mat[0][k];
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int up = mat[i][j] + dp[i - 1][j];
                    int LD = mat[i][j] + (j == 0 ? mod : dp[i - 1][j - 1]);
                    int RD = mat[i][j] + (j == n-1 ? mod : dp[i - 1][j + 1]);
                    dp[i][j] = Math.max(up, Math.max(LD, RD));
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans  = Math.max(ans, dp[m-1][i]);
        }
        return ans;
    }
    // Tabulation (separated init)
    public int maxPathSumInMatrixTabulation2(int[][] mat){
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
                int LD = mat[i][j] + (j == 0 ? mod : dp[i - 1][j - 1]);
                int RD = mat[i][j] + (j == n-1 ? mod : dp[i - 1][j + 1]);
                dp[i][j] = Math.max(up, Math.max(LD, RD));
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans  = Math.max(ans, dp[m-1][i]);
        }
        return ans;
    }
}
