public class dp9 {
    public static void main(String[] args) {
        
    }
    // Recursion
    public int totalUniquePathsWithObstacle(int i, int j, int[][] mat){
        if(i>=0 && j>=0 && mat[i][j]==-1){
            return 0;
        }
        if(i == 0 && j == 0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        int up = totalUniquePathsWithObstacle(i-1,j, mat);
        int left = totalUniquePathsWithObstacle(i, j-1, mat);
        return up + left;
    }
    // Memoization
    public int totalUniquePathsWithObstacle(int i, int j, int[][] mat,int dp[][]){
        if(i>=0 && j>=0 && mat[i][j]==-1){
            return 0;
        }
        if(i == 0 && j == 0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        } 
        if(dp[i][j]!=-1) return dp[i][j];
        int up = totalUniquePathsWithObstacle(i-1, j,mat, dp);
        int left = totalUniquePathsWithObstacle(i, j-1,mat, dp);
        dp[i][j] = up + left;
        return dp[i][j];
    }
    // Tabulation
    public int totalUniquePathsTabulationWithObstacle(int m, int n, int[][] mat){
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j] == -1) {
                    dp[i][j] = 0;
                }
                else if(i == 0 && j == 0) {
                    dp[0][0] = 1;
                }
                else{
                    int up = i > 0 ? dp[i-1][j] : 0;
                    int left= j > 0 ? dp[i][j-1] : 0;
                    dp[i][j]= up+ left;
                }
            }
        }
        return dp[m-1][n-1];
    }
}
