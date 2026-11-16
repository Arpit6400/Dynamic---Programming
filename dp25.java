public class dp25 {
    public static void main(String[] args) {
        
    }

    // Recursion - Longest Common Sequence
    public int LCSRec(int idx1, int idx2, String str1, String str2){
        if(idx1 < 0 || idx2 < 0) return 0;

        if(str1.charAt(idx1) == str2.charAt(idx2)){
            return 1 + LCSRec(idx1 - 1, idx2 - 1, str1, str2);
        }
        return Math.max(LCSRec(idx1-1, idx2, str1, str2), LCSRec(idx1, idx2-1, str1, str2));
    }

    // Memoization
    public int LCSRec(int idx1, int idx2, String str1, String str2 , int[][] dp){
        // dp[n][m]
        if(idx1 < 0 || idx2 < 0) return 0;

        if(dp[idx1][idx2] !=-1) return dp[idx1][idx2];
        if(str1.charAt(idx1) == str2.charAt(idx2)){
            return dp[idx1][idx2] =  1 + LCSRec(idx1 - 1, idx2 - 1, str1, str2, dp);
        }
        return dp[idx1][idx2] = Math.max(LCSRec(idx1-1, idx2, str1, str2, dp), LCSRec(idx1, idx2-1, str1, str2, dp));
    }

    // Memoization - Shifting of indexes , treat f(1) as f(0) for the tabulation purpose
    public int LCSRec2(int idx1, int idx2, String str1, String str2 , int[][] dp){
        // dp[n + 1][m + 1]
        if(idx1 == 0 || idx2 == 0) return 0;

        if(dp[idx1][idx2] !=-1) return dp[idx1][idx2];
        if(str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1)){
            return dp[idx1][idx2] =  1 + LCSRec2(idx1 - 1, idx2 - 1, str1, str2, dp);
        }
        return dp[idx1][idx2] = Math.max(LCSRec2(idx1-1, idx2, str1, str2, dp), LCSRec2(idx1, idx2-1, str1, str2, dp));
    }

    // Tabulation 
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n+1][m+1];
        for(int j = 0;j <= m;j++) dp[0][j] = 0;
        for(int i = 0;i <= n; i++) dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }
}
