// GFG: https://www.geeksforgeeks.org/problems/rod-cutting0840/1

import java.util.Arrays;

public class dp24_Rod_Cutting {
    public static void main(String[] args) {

    }

    // Recursion - Rod cutting problem
    // idx = 0-indexed piece length, totalLength = remaining rod to cut
    public int rodCutting(int idx, int totalLength, int[] cost) {
        if (idx == 0)
            return totalLength * cost[0];

        int not_cut = rodCutting(idx - 1, totalLength, cost);
        int cut = Integer.MIN_VALUE;
        int currRodLength = idx + 1;
        if (currRodLength <= totalLength)
            cut = cost[idx] + rodCutting(idx, totalLength - currRodLength, cost);
        return Math.max(cut, not_cut);
    }

    // Memoization
    private int solveMemo(int idx, int totalLength, int[] cost, int[][] dp) {
        if (idx == 0)
            return totalLength * cost[0];
        if (dp[idx][totalLength] != -1)
            return dp[idx][totalLength];

        int not_cut = solveMemo(idx - 1, totalLength, cost, dp);
        int cut = Integer.MIN_VALUE;
        int currRodLength = idx + 1;
        if (currRodLength <= totalLength)
            cut = cost[idx] + solveMemo(idx, totalLength - currRodLength, cost, dp);
        return dp[idx][totalLength] = Math.max(cut, not_cut);
    }

    public int cutRodMemo(int[] price) {
        int n = price.length;
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solveMemo(n - 1, n, price, dp);
    }

    // Tabulation
    public int cutRod(int[] price) {
        int n = price.length;
        int totalLength = n;
        int[][] dp = new int[n][totalLength + 1];
        for (int i = 0; i <= totalLength; i++)
            dp[0][i] = i * price[0];

        for (int idx = 1; idx < n; idx++) {
            for (int currLength = 0; currLength <= totalLength; currLength++) {
                int not_cut = dp[idx - 1][currLength];
                int cut = Integer.MIN_VALUE;
                if (idx + 1 <= currLength)
                    cut = price[idx] + dp[idx][currLength - (idx + 1)];
                dp[idx][currLength] = Math.max(cut, not_cut);
            }
        }
        return dp[n - 1][totalLength];
    }

    // Space Optimization
    public int cutRodSpaceOpt(int[] price) {
        int n = price.length;
        int totalLength = n;
        int[] prev = new int[totalLength + 1];
        for (int i = 0; i <= totalLength; i++)
            prev[i] = i * price[0];

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[totalLength + 1];
            for (int currLength = 0; currLength <= totalLength; currLength++) {
                int not_cut = prev[currLength];
                int cut = Integer.MIN_VALUE;
                if (idx + 1 <= currLength)
                    cut = price[idx] + curr[currLength - (idx + 1)];
                curr[currLength] = Math.max(cut, not_cut);
            }
            prev = curr;
        }
        return prev[totalLength];
    }
}
