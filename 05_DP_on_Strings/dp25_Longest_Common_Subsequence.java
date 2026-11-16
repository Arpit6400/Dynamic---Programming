// LC: https://leetcode.com/problems/longest-common-subsequence/
// GFG: https://www.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1

public class dp25_Longest_Common_Subsequence {

    public static void main(String[] args) {
        dp25_Longest_Common_Subsequence solver = new dp25_Longest_Common_Subsequence();
        String s1 = "abcde", s2 = "ace";
        System.out.println("Tabulation: " + solver.longestCommonSubsequence(s1, s2));
        System.out.println("Space Opt: " + solver.longestCommonSubsequenceSpaceOpt(s1, s2));
    }

    // Recursion - Longest Common Subsequence
    public int LCSRec(int idx1, int idx2, String str1, String str2) {
        if (idx1 < 0 || idx2 < 0)
            return 0;

        if (str1.charAt(idx1) == str2.charAt(idx2)) {
            return 1 + LCSRec(idx1 - 1, idx2 - 1, str1, str2);
        }
        return Math.max(LCSRec(idx1 - 1, idx2, str1, str2), LCSRec(idx1, idx2 - 1, str1, str2));
    }

    // Memoization
    public int LCSRec(int idx1, int idx2, String str1, String str2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0)
            return 0;
        if (dp[idx1][idx2] != -1)
            return dp[idx1][idx2];

        if (str1.charAt(idx1) == str2.charAt(idx2)) {
            return dp[idx1][idx2] = 1 + LCSRec(idx1 - 1, idx2 - 1, str1, str2, dp);
        }
        return dp[idx1][idx2] = Math.max(LCSRec(idx1 - 1, idx2, str1, str2, dp),
                LCSRec(idx1, idx2 - 1, str1, str2, dp));
    }

    // Memoization - 1-indexed for tabulation mapping
    public int LCSRec2(int idx1, int idx2, String str1, String str2, int[][] dp) {
        if (idx1 == 0 || idx2 == 0)
            return 0;
        if (dp[idx1][idx2] != -1)
            return dp[idx1][idx2];

        if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1)) {
            return dp[idx1][idx2] = 1 + LCSRec2(idx1 - 1, idx2 - 1, str1, str2, dp);
        }
        return dp[idx1][idx2] = Math.max(LCSRec2(idx1 - 1, idx2, str1, str2, dp),
                LCSRec2(idx1, idx2 - 1, str1, str2, dp));
    }

    // Tabulation - O(N * M) Time, O(N * M) Space
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Base cases
        for (int j = 0; j <= m; j++)
            dp[0][j] = 0;
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    // Space Optimization - O(N * M) Time, O(M) Space
    public int longestCommonSubsequenceSpaceOpt(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] prev = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        return prev[m];
    }
}
