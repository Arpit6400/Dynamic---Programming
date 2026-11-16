// GFG: https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1

public class dp18_Count_Partitions_With_Difference {

    public static void main(String[] args) {
        int[] arr = { 5, 2, 6, 4 };
        int d = 3;
        dp18_Count_Partitions_With_Difference solver = new dp18_Count_Partitions_With_Difference();
        System.out.println("Tabulation: " + solver.countPartitions(arr, d));
        System.out.println("Space Opt: " + solver.countPartitionsSpaceOpt(arr, d));
    }

    // Tabulation - Count Partitions With Given Difference
    public int countPartitions(int[] arr, int diff) {
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        if ((totalSum + diff) % 2 != 0)
            return 0;
        if (totalSum < diff)
            return 0;

        return perfectSum(arr, (totalSum + diff) / 2);
    }

    public int perfectSum(int[] arr, int target) {
        int n = arr.length;
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][target + 1];

        for (int i = 0; i < n; i++)
            dp[i][0] = 1;
        if (arr[0] <= target)
            dp[0][arr[0]] = 1;
        if (arr[0] == 0)
            dp[0][0] = 2;

        for (int idx = 1; idx < n; idx++) {
            for (int sum = 0; sum <= target; sum++) {
                int not_take = dp[idx - 1][sum];
                int take = 0;
                if (arr[idx] <= sum)
                    take = dp[idx - 1][sum - arr[idx]];
                dp[idx][sum] = (take + not_take) % MOD;
            }
        }
        return dp[n - 1][target];
    }

    // Space Optimization - O(N * Target) Time, O(Target) Space
    public int countPartitionsSpaceOpt(int[] arr, int diff) {
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        if ((totalSum + diff) % 2 != 0)
            return 0;
        if (totalSum < diff)
            return 0;

        return perfectSumSpaceOpt(arr, (totalSum + diff) / 2);
    }

    public int perfectSumSpaceOpt(int[] arr, int target) {
        int n = arr.length;
        int MOD = 1_000_000_007;
        int[] prev = new int[target + 1];

        prev[0] = 1;
        if (arr[0] <= target)
            prev[arr[0]] = 1;
        if (arr[0] == 0)
            prev[0] = 2;

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[target + 1];
            for (int sum = 0; sum <= target; sum++) {
                int not_take = prev[sum];
                int take = 0;
                if (arr[idx] <= sum)
                    take = prev[sum - arr[idx]];
                curr[sum] = (take + not_take) % MOD;
            }
            prev = curr;
        }
        return prev[target];
    }
}
