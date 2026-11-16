// LC: https://leetcode.com/problems/target-sum/

import java.util.Arrays;

public class dp21_Target_Sum {

    public static void main(String[] args) {
    }

    // Recursion - Target Sum
    // Treat as: count partitions with difference = target
    // S1 - S2 = target, S1 + S2 = total => S1 = (total + target) / 2
    public int targetSum(int idx, int target, int[] arr) {
        if (idx == 0) {
            if (target == 0 && arr[0] == 0)
                return 2;
            if (target == 0 || target == arr[0])
                return 1;
            return 0;
        }
        int take = targetSum(idx - 1, target - arr[idx], arr);
        int neg_take = targetSum(idx - 1, target + arr[idx], arr);
        return take + neg_take;
    }

    // Memoization (via count-subsets approach — more practical)
    private int countMemo(int idx, int sum, int[] arr, int[][] dp) {
        if (idx == 0) {
            if (sum == 0 && arr[0] == 0)
                return 2;
            if (sum == 0 || arr[0] == sum)
                return 1;
            return 0;
        }
        if (dp[idx][sum] != -1)
            return dp[idx][sum];

        int not_take = countMemo(idx - 1, sum, arr, dp);
        int take = 0;
        if (arr[idx] <= sum)
            take = countMemo(idx - 1, sum - arr[idx], arr, dp);
        return dp[idx][sum] = take + not_take;
    }

    // Tabulation - Reduces to Count Partitions with Given Difference
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int n : nums)
            total += n;
        // S1 - S2 = target => S1 = (total + target) / 2
        if ((total + target) < 0 || (total + target) % 2 != 0)
            return 0;
        int s1 = (total + target) / 2;

        int n = nums.length;
        int[] dp = new int[s1 + 1];
        dp[0] = 1;
        if (nums[0] == 0)
            dp[0] = 2;
        else if (nums[0] <= s1)
            dp[nums[0]] = 1;

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[s1 + 1];
            for (int sum = 0; sum <= s1; sum++) {
                int not_take = dp[sum];
                int take = 0;
                if (nums[idx] <= sum)
                    take = dp[sum - nums[idx]];
                curr[sum] = take + not_take;
            }
            dp = curr;
        }
        return dp[s1];
    }
}
