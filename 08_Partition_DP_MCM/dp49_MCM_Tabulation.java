// GFG: https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1

public class dp49_MCM_Tabulation {

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50 };
        System.out.println("MCM Tabulation: " + matrixMultiplication(arr));
    }

    // Tabulation - O(N^3) Time, O(N^2) Space
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Base Case
        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        // Tabulation
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
                    min = Math.min(min, steps);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n - 1];
    }
}
