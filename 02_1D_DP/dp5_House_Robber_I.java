// LC: https://leetcode.com/problems/house-robber/
// GFG: https://www.geeksforgeeks.org/problems/max-sum-without-adjacents2430/1

import java.util.Arrays;

public class dp5_House_Robber_I {

    public static void main(String[] args) {
        int[] nums = { 2, 7, 9, 3, 1 };
        System.out.println("Tabulation: " + rob1(nums));
        System.out.println("Space Opt: " + robSpaceOpt(nums));
    }

    // Recursion
    public static int rob(int[] nums) {
        return solve(nums.length - 1, nums);
    }

    private static int solve(int idx, int[] nums) {
        if (idx == 0)
            return nums[idx];
        if (idx < 0)
            return 0;
        int pick = nums[idx] + solve(idx - 2, nums);
        int notPick = solve(idx - 1, nums);
        return Math.max(pick, notPick);
    }

    // Memoization
    public static int robMemo(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solveMemo(n - 1, nums, dp);
    }

    private static int solveMemo(int idx, int[] nums, int[] dp) {
        if (idx == 0)
            return nums[idx];
        if (idx < 0)
            return 0;
        if (dp[idx] != -1)
            return dp[idx];

        int pick = nums[idx] + solveMemo(idx - 2, nums, dp);
        int notPick = solveMemo(idx - 1, nums, dp);
        return dp[idx] = Math.max(pick, notPick);
    }

    // Tabulation - O(N) Time, O(N) Space
    public static int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int take = nums[i];
            if (i > 1)
                take += dp[i - 2];
            int notTake = dp[i - 1];
            dp[i] = Math.max(take, notTake);
        }
        return dp[n - 1];
    }

    // Space Optimization - O(N) Time, O(1) Space
    public static int robSpaceOpt(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        int prev2 = 0;
        int prev1 = nums[0];

        for (int i = 1; i < n; i++) {
            int take = nums[i] + prev2;
            int notTake = prev1;
            int cur = Math.max(take, notTake);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
