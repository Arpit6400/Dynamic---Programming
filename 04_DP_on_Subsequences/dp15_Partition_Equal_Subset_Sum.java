// LC: https://leetcode.com/problems/partition-equal-subset-sum/
// GFG: https://www.geeksforgeeks.org/problems/subset-sum-problem2014/1

public class dp15_Partition_Equal_Subset_Sum {

    public static void main(String[] args) {
        int[] nums = { 1, 5, 11, 5 };
        dp15_Partition_Equal_Subset_Sum solver = new dp15_Partition_Equal_Subset_Sum();
        System.out.println("Tabulation: " + solver.canPartition(nums));
        System.out.println("Space Opt: " + solver.canPartitionSpaceOpt(nums));
    }

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int val : nums)
            total += val;
        if (total % 2 != 0)
            return false;
        return isSubsetSum(nums, total / 2);
    }

    public boolean canPartitionSpaceOpt(int[] nums) {
        int total = 0;
        for (int val : nums)
            total += val;
        if (total % 2 != 0)
            return false;
        return isSubsetSumSpaceOpt(nums, total / 2);
    }

    // Tabulation - O(N * Target) Time, O(N * Target) Space
    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

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

    // Space Optimization - O(N * Target) Time, O(Target) Space
    static boolean isSubsetSumSpaceOpt(int arr[], int sum) {
        int n = arr.length;
        boolean[] prev = new boolean[sum + 1];
        prev[0] = true;

        if (arr[0] <= sum)
            prev[arr[0]] = true;

        for (int idx = 1; idx < n; idx++) {
            boolean[] curr = new boolean[sum + 1];
            curr[0] = true;
            for (int target = 1; target <= sum; target++) {
                boolean not_take = prev[target];
                boolean take = false;
                if (target >= arr[idx]) {
                    take = prev[target - arr[idx]];
                }
                curr[target] = take || not_take;
            }
            prev = curr;
        }
        return prev[sum];
    }
}
