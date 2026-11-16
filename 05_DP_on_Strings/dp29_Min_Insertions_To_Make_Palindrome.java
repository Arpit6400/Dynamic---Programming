// LC: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
// GFG: https://www.geeksforgeeks.org/problems/form-a-palindrome1455/1

public class dp29_Min_Insertions_To_Make_Palindrome {

    public static void main(String[] args) {
        dp29_Min_Insertions_To_Make_Palindrome obj = new dp29_Min_Insertions_To_Make_Palindrome();
        String s = "mbadm";
        System.out.println("Tabulation: " + obj.minInsertions(s));
        System.out.println("Space Opt: " + obj.minInsertionsSpaceOpt(s));
    }

    // Tabulation - O(N^2) Time, O(N^2) Space
    public int minInsertions(String s) {
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        int lcs = longestCommonSubsequence(s1, s2);
        return s1.length() - lcs;
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
    public int minInsertionsSpaceOpt(String s) {
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        int lcs = longestCommonSubsequenceSpaceOpt(s1, s2);
        return s1.length() - lcs;
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
