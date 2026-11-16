// LC: https://leetcode.com/problems/longest-palindromic-subsequence/
// GFG: https://www.geeksforgeeks.org/problems/longest-palindromic-subsequence-1612327878/1

public class dp28_Longest_Palindromic_Subsequence {

    public static void main(String[] args) {
        dp28_Longest_Palindromic_Subsequence solver = new dp28_Longest_Palindromic_Subsequence();
        String s = "bbbab";
        System.out.println("Tabulation: " + solver.longestPalindromeSubseq(s));
        System.out.println("Space Opt: " + solver.longestPalindromeSubseqSpaceOpt(s));
    }

    // Tabulation - O(N^2) Time, O(N^2) Space
    public int longestPalindromeSubseq(String s) {
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s1, s2);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
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

    // Space Optimization - O(N^2) Time, O(N) Space
    public int longestPalindromeSubseqSpaceOpt(String s) {
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequenceSpaceOpt(s1, s2);
    }

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
