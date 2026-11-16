public class dp16_Partition_Min_Sum_Difference {
    public static void main(String[] args) {
        
    }
    // Partition Into 2 Subsets with Min Sum Diff
    public int minSubsetSumDiff(int arr[], int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += arr[i];
        }
        boolean[][] dp = new boolean[n][total + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;
        
        if (arr[0] <= total) {
            dp[0][arr[0]] = true;
        }
        
        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= total; target++) {
                boolean not_take = dp[idx - 1][target];
                boolean take = false;
                if (target >= arr[idx]) {
                    take = dp[idx - 1][target - arr[idx]];
                }
                dp[idx][target] = take || not_take;
            }
        }
        int minDiff = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= total / 2; s1++) {
            if (dp[n - 1][s1]) {
                int s2 = total - s1;
                minDiff = Math.min(minDiff, Math.abs(s2 - s1));
            }
        }
        return minDiff;
    }
}
