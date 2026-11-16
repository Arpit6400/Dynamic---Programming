public class dp15_Partition_Equal_Subset_Sum {
    public static void main(String[] args) {
        
    }

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int val : nums) {
            total += val;
        }
        if(total%2 != 0) return false;
        return isSubsetSum(nums, total/2);
    }

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
