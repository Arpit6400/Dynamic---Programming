// LC: https://leetcode.com/problems/house-robber-ii/

import java.util.ArrayList;

public class dp6_House_Robber_II {

    public static void main(String[] args) {
        dp6_House_Robber_II dp6 = new dp6_House_Robber_II();
        int[] nums = { 1, 2, 3, 1 };
        System.out.println("Tabulation: " + dp6.rob2(nums));
        System.out.println("Space Opt: " + dp6.rob2SpaceOpt(nums));
    }

    // Tabulation - O(N) Time, O(N) Space
    public int rob2(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];

        ArrayList<Integer> temp1 = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != 0)
                temp1.add(arr[i]);
            if (i != n - 1)
                temp2.add(arr[i]);
        }
        return Math.max(rob1(temp1), rob1(temp2));
    }

    private int rob1(ArrayList<Integer> arr) {
        int n = arr.size();
        int[] dp = new int[n];
        dp[0] = arr.get(0);
        for (int i = 1; i < n; i++) {
            int take = arr.get(i);
            if (i > 1)
                take += dp[i - 2];
            int not_take = dp[i - 1];
            dp[i] = Math.max(take, not_take);
        }
        return dp[n - 1];
    }

    // Space Optimization - O(N) Time, O(1) Space
    public int rob2SpaceOpt(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];

        return Math.max(rob1SpaceOpt(arr, 1, n - 1), rob1SpaceOpt(arr, 0, n - 2));
    }

    private int rob1SpaceOpt(int[] arr, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int take = arr[i] + prev2;
            int notTake = prev1;
            int cur = Math.max(take, notTake);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
