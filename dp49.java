public class dp49 {
    
    // Tabulation
	static int matrixMultiplication(int arr[]) {
		// code here
		int n = arr.length;
		int[][] dp = new int[n][n];

        // Base Case
        for (int i = 1; i < n; i++) {
            dp[i][i] =  0;
        }

        // Tabulation
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n ; j++) {
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
