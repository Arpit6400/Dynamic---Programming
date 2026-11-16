// LC: https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
// GFG: https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1

public class dp16_Partition_Min_Sum_Difference {

    public static void main(String[] args) {
        int[] arr = { 1, 6, 11, 5 };
        dp16_Partition_Min_Sum_Difference solver = new dp16_Partition_Min_Sum_Difference();
        System.out.println("Tabulation: " + solver.minSubsetSumDiff(arr, arr.length));
        System.out.println("Space Opt: " + solver.minSubsetSumDiffSpaceOpt(arr, arr.length));
    }

    // Tabulation - O(N * TotalSum) Time, O(N * TotalSum) Space
    public int minSubsetSumDiff(int arr[], int n) {
        int total = 0;
        for (int i = 0; i < n; i++)
            total += arr[i];

        boolean[][] dp = new boolean[n][total + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        if (arr[0] <= total)
            dp[0][arr[0]] = true;

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

    // Space Optimization - O(N * TotalSum) Time, O(TotalSum) Space
    public int minSubsetSumDiffSpaceOpt(int arr[], int n) {
        int total = 0;
        for (int i = 0; i < n; i++)
            total += arr[i];

        boolean[] prev = new boolean[total + 1];
        prev[0] = true;

        if (arr[0] <= total)
            prev[arr[0]] = true;

        for (int idx = 1; idx < n; idx++) {
            boolean[] curr = new boolean[total + 1];
            curr[0] = true;
            for (int target = 1; target <= total; target++) {
                boolean not_take = prev[target];
                boolean take = false;
                if (target >= arr[idx]) {
                    take = prev[target - arr[idx]];
                }
                curr[target] = take || not_take;
            }
            prev = curr;
        }

        int minDiff = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= total / 2; s1++) {
            if (prev[s1]) {
                int s2 = total - s1;
                minDiff = Math.min(minDiff, Math.abs(s2 - s1));
            }
        }
        return minDiff;
    }
}
