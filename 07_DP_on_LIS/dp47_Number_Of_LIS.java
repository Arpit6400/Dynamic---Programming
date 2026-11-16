
// LC: https://leetcode.com/problems/number-of-longest-increasing-subsequence/
import java.util.*;

public class dp47_Number_Of_LIS {
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[] { 2, 2, 2, 2, 2 }));
    }

    // Get back to the solution
    public static int findNumberOfLIS(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];
        int[] ct = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(ct, 1);

        int maxi = 1;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {

                if (arr[prev] < arr[i] && dp[prev] + 1 > dp[i]) {
                    dp[i] = dp[prev] + 1;
                    ct[i] = ct[prev];
                } else if (arr[prev] < arr[i] && dp[prev] + 1 == dp[i]) {
                    ct[i] += ct[prev];
                }
            }

            maxi = Math.max(maxi, dp[i]);
        }

        int nos = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == maxi) {
                nos += ct[i];
            }
        }

        return nos;
    }
}
