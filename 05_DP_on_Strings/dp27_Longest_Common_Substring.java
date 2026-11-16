// GFG: https://www.geeksforgeeks.org/problems/longest-common-substring1449/1

public class dp27_Longest_Common_Substring {

    public static void main(String[] args) {
        dp27_Longest_Common_Substring solver = new dp27_Longest_Common_Substring();
        String s1 = "ABCDGH", s2 = "ACDGHR";
        System.out.println("Tabulation: " + solver.longestCommonSubstring(s1, s2));
        System.out.println("Space Opt: " + solver.longestCommonSubstringSpaceOpt(s1, s2));
    }

    // Tabulation - O(N * M) Time, O(N * M) Space
    public int longestCommonSubstring(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++)
            dp[0][j] = 0;
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }

    // Space Optimization - O(N * M) Time, O(M) Space
    public int longestCommonSubstringSpaceOpt(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] prev = new int[m + 1];
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    ans = Math.max(ans, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr;
        }
        return ans;
    }
}
