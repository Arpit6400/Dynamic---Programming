public class dp14 {
    public static void main(String[] args) {

    }

    // Recursion - subset sum equal to k
    static boolean isSubsetSumRecursion(int arr[], int sum) {
        return solve(0, sum, arr);

    }

    private static boolean solve(int idx, int target, int[] arr) {
        if (target == 0)
            return true;
        if (idx == 0)
            return arr[idx] == target;

        boolean not_take = solve(idx - 1, target, arr);
        boolean take = false;
        if (target >= arr[idx]) {
            take = solve(idx - 1, target - arr[idx], arr);
        }
        return take || not_take;
    }

    // Memoization - Agent add

    // Tabulation
    static boolean isSubsetSum(int arr[], int sum) {
		int n = arr.length;
		boolean[][] dp = new boolean[n][sum + 1];
		for (int i = 0; i < n; i++)
			dp[i][0] = true;
		
		if (arr[0] <= sum) {
			dp[0][arr[0]] = true;
		}
		
		for (int idx = 1; idx < n; idx++) {
			for (int target = 1; target <= sum; target++) {
				boolean not_take = dp[idx - 1][target];
				boolean take = false;
				if (target >= arr[idx]) {
					take = dp[idx - 1][target - arr[idx]];
				}
				dp[idx][target] = take || not_take;
			}
		}
		return dp[n - 1][sum];
    }

}
