
// GFG: https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
import java.util.Arrays;

public class dp48_Matrix_Chain_Multiplication {
    public static void main(String[] args) {

    }

    // New type DP - Partition DP
    // MCM - Recursion
    static int matrixMultiplication1(int arr[]) {
        // code here
        int n = arr.length;
        return recMCM(1, n - 1, arr);
    }

    static int recMCM(int i, int j, int[] arr) {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps = arr[i - 1] * arr[k] * arr[j] + recMCM(i, k, arr) + recMCM(k + 1, j, arr);
            min = Math.min(min, steps);
        }
        return min;
    }

    // MCM - Memoization
    static int matrixMultiplication2(int arr[]) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return memoizationMCM(1, n - 1, arr, dp);
    }

    static int memoizationMCM(int i, int j, int[] arr, int[][] dp) {
        if (i == j)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps = arr[i - 1] * arr[k] * arr[j] + memoizationMCM(i, k, arr, dp)
                    + memoizationMCM(k + 1, j, arr, dp);
            min = Math.min(min, steps);
        }
        return dp[i][j] = min;
    }

}
