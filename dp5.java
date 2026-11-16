import java.util.Arrays;

public class dp5 {
    // House Robber 1
    public static int rob(int[] nums) {
        return solve(nums.length - 1, nums);
    }

    // Recursion
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
    private static int solve2(int idx, int[] nums, int[] dp) {
        if (idx == 0)
            return nums[idx];
        if (idx < 0)
            return 0;
        if (dp[idx]!= -1) return dp[idx];
        int pick = nums[idx] + solve2(idx - 2, nums, dp);
        int notPick = 0 + solve2(idx - 1, nums, dp);
        dp[idx] = Math.max(pick, notPick);
        return dp[idx];
    }
    // Tabulation
    public static int rob1(int[] nums) {
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int take = nums[i];
            if(i>1) take+=dp[i-2];
            int not_take = 0 + dp[i-1];
            dp[i] = Math.max(take, not_take);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {

    }
}
