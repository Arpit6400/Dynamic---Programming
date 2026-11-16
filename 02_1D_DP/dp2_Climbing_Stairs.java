// LC: https://leetcode.com/problems/climbing-stairs/
// GFG: https://www.geeksforgeeks.org/problems/count-ways-to-reach-the-nth-stair-1587115620/1

public class dp2_Climbing_Stairs {

    public static void main(String[] args) {
        dp2_Climbing_Stairs dp2 = new dp2_Climbing_Stairs();
        System.out.println("Tabulation: " + dp2.climbStairs(3));
        System.out.println("Space Optimization: " + dp2.climbStairsSpaceOpt(3));
    }

    // Recursion
    private int solve(int n, int idx) {
        if (idx == n) return 1;
        int left = 0;
        if (idx + 1 <= n) left += solve(n, idx + 1);
        int right = 0;
        if (idx + 2 <= n) right += solve(n, idx + 2);
        return left + right;
    }

    // Memoization
    private int solveMemo(int n, int idx, int[] dp) {
        if (idx == n) return 1;
        if (dp[idx] != -1) return dp[idx];
        int left = 0;
        if (idx + 1 <= n) left += solveMemo(n, idx + 1, dp);
        int right = 0;
        if (idx + 2 <= n) right += solveMemo(n, idx + 2, dp);
        return dp[idx] = left + right;
    }

    // Tabulation - O(N) Time, O(N) Space
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Space Optimization - O(N) Time, O(1) Space
    public int climbStairsSpaceOpt(int n) {
        if (n <= 1) return 1;
        int prev2 = 1;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
