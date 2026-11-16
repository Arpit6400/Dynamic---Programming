// LC: https://leetcode.com/problems/delete-operation-for-two-strings/
// GFG: https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1

public class dp30_Min_Insertions_Deletions_To_Convert {

    public static void main(String[] args) {
        dp30_Min_Insertions_Deletions_To_Convert solver = new dp30_Min_Insertions_Deletions_To_Convert();
        String w1 = "sea", w2 = "eat";
        System.out.println("Tabulation: " + solver.minDistance(w1, w2));
        System.out.println("Space Opt: " + solver.minDistanceSpaceOpt(w1, w2));
    }

    // Tabulation - O(N * M) Time, O(N * M) Space
    public int minDistance(String word1, String word2) {
        int lengthOfLCS = longestCommonSubsequence(word1, word2);
        int deletions = word1.length() - lengthOfLCS;
        int insertions = word2.length() - lengthOfLCS;
        return deletions + insertions;
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

    // Space Optimization - O(N * M) Time, O(M) Space
    public int minDistanceSpaceOpt(String word1, String word2) {
        int lengthOfLCS = longestCommonSubsequenceSpaceOpt(word1, word2);
        int deletions = word1.length() - lengthOfLCS;
        int insertions = word2.length() - lengthOfLCS;
        return deletions + insertions;
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
