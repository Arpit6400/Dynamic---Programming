import java.util.*;
public class dp8 {
public static void main(String[] args) {
    dp8 solver = new dp8();
    int m = 3, n = 7;
    
    // Testing Memoization
    int[][] dp = new int[m][n];
    for(int[] row : dp) Arrays.fill(row, -1);
    
    System.out.println("Memoization: " + solver.totalUniquePaths(m - 1, n - 1, dp));
    System.out.println("Tabulation: " + solver.totalUniquePathsTabulation(m, n));
}


    // Recursion
    public int totalUniquePaths(int i, int j){
        if(i == 0 && j == 0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        int up = totalUniquePaths(i-1,j);
        int left = totalUniquePaths(i, j-1);
        return up + left;
    }
    // Memoization
    public int totalUniquePaths(int i, int j, int dp[][]){
        if(i == 0 && j == 0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        } 
        if(dp[i][j]!=-1) return dp[i][j];
        int up = totalUniquePaths(i-1, j, dp);
        int left = totalUniquePaths(i, j-1, dp);
        dp[i][j] = up + left;
        return dp[i][j];
    }
    // Tabulation
    public int totalUniquePathsTabulation(int m, int n){
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
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
