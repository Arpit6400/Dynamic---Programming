
// LC: https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
import java.util.Arrays;

public class dp50_Min_Cost_Cut_Stick {
    public static void main(String[] args) {

    }

    // Minimum cost to cut a stick - LC 1547
    public static int minCost2(int n, int[] cuts) {
        Arrays.sort(cuts);
        int c = cuts.length;
        int[] cuts2 = new int[c + 2];
        cuts2[0] = 0;
        cuts2[c + 1] = n;

        for (int i = 1; i <= cuts2.length - 2; i++) {
            cuts2[i] = cuts[i - 1];
        }

        int c2 = cuts2.length;
        int[][] dp = new int[c2 + 2][c2 + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return minCostToCutStick2(1, c, cuts2, dp);
    }

    // Recursion
    public static int minCostToCutStick(int i, int j, int[] cuts) {
        if (i > j)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int idx = i; idx <= j; idx++) {
            int cost = cuts[j + 1] - cuts[i - 1]
                    + minCostToCutStick(i, idx - 1, cuts)
                    + minCostToCutStick(idx + 1, j, cuts);
            min = Math.min(min, cost);
        }
        return min;
    }

    // Memoization
    public static int minCostToCutStick2(int i, int j, int[] cuts, int[][] dp) {
        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        for (int idx = i; idx <= j; idx++) {
            int cost = cuts[j + 1] - cuts[i - 1]
                    + minCostToCutStick2(i, idx - 1, cuts, dp)
                    + minCostToCutStick2(idx + 1, j, cuts, dp);
            min = Math.min(min, cost);
        }
        return dp[i][j] = min;
    }

    // Tabulation
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int c = cuts.length;
        int[] cuts2 = new int[c + 2];
        cuts2[0] = 0;
        cuts2[c + 1] = n;

        for (int i = 1; i <= cuts2.length - 2; i++) {
            cuts2[i] = cuts[i - 1];
        }

        int c2 = cuts2.length;
        int[][] dp = new int[c2 + 2][c2 + 2];

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j)
                    continue; // Base Case
                int min = Integer.MAX_VALUE;
                for (int idx = i; idx <= j; idx++) {
                    int cost = cuts[j + 1] - cuts[i - 1]
                            + dp[i][idx - 1]
                            + dp[idx + 1][j];
                    min = Math.min(min, cost);
                }
                dp[i][j] = min;
            }
        }

        return dp[1][c];
    }
}
