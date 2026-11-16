// LC: https://leetcode.com/problems/shortest-common-supersequence/
// GFG: https://www.geeksforgeeks.org/problems/shortest-common-supersequence0322/1

public class dp31_Shortest_Common_Supersequence {

    public static void main(String[] args) {
        dp31_Shortest_Common_Supersequence solver = new dp31_Shortest_Common_Supersequence();
        System.out.println(solver.shortestCommonSupersequence("abac", "cab"));
    }

    // Tabulation - O(N * M) Time, O(N * M) Space (Table required for
    // reconstruction)
    public String shortestCommonSupersequence(String text1, String text2) {
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

        int i = n, j = m;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                lcs.append(text1.charAt(i - 1));
                i--;
            } else {
                lcs.append(text2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            lcs.append(text1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            lcs.append(text2.charAt(j - 1));
            j--;
        }
        return lcs.reverse().toString();
    }
}
