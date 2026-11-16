public class dp32_Distinct_Subsequences {
    public static void main(String[] args) {
        
    }

    int distinctSubsequences(int i, int j, String s, String t){
        if(j < 0) return 1;
        if(i < 0) return 0;

        if(s.charAt(i)==t.charAt(j)){
            return distinctSubsequences(i - 1, j - 1, s, t) + distinctSubsequences(i - 1, j, s, t);
        }
        return distinctSubsequences(i - 1, j, s, t);
    }
    // Shifting of indices for tabulation
    int distinctSubsequences2(int i, int j, String s, String t){
        if(j == 0) return 1;
        if(i == 0) return 0;

        if(s.charAt(i - 1)==t.charAt(j - 1)){
            return distinctSubsequences2(i - 1, j - 1, s, t) + distinctSubsequences2(i - 1, j, s, t);
        }
        return distinctSubsequences2(i - 1, j, s, t);
    }

    // Memoization - Agent add

    // Tabulation
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        
        // Base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1)==t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }
}
