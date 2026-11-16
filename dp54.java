import java.util.Arrays;

// LC 1043. Partition Array for Maximum Sum
public class dp54 {
    public static int f(int ind, int[] num, int k) {
        int n = num.length;
        if (ind == n)
            return 0;
        int len = 0;
        int maxi = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        for (int j = ind; j < Math.min(ind + k, n); j++) {
            len++;
            maxi = Math.max(maxi, num[j]);
            int sum = len * maxi + f(j + 1, num, k);
            maxAns = Math.max(maxAns, sum);
        }
        return maxAns;
    }

    public static int maximumSubarrayRec(int[] num, int k) {
        int n = num.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return f(0, num, k);
    }

    // Memoization
    public static int f(int ind, int[] num, int k, int[] dp) {
        int n = num.length;
        if (ind == n)
            return 0;
        if (dp[ind] != -1)
            return dp[ind];
        int len = 0;
        int maxi = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        for (int j = ind; j < Math.min(ind + k, n); j++) {
            len++;
            maxi = Math.max(maxi, num[j]);
            int sum = len * maxi + f(j + 1, num, k, dp);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[ind] = maxAns;
    }

    // Tabulation
    public static int maximumSubarrayTab(int[] num, int k) {
        int n = num.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int ind = n - 1; ind >= 0; ind--) {
            int len = 0;
            int maxi = Integer.MIN_VALUE;
            int maxAns = Integer.MIN_VALUE;
            for (int j = ind; j < Math.min(ind + k, n); j++) {
                len++;
                maxi = Math.max(maxi, num[j]);
                int sum = len * maxi + dp[j + 1];
                maxAns = Math.max(maxAns, sum);
            }
            dp[ind] = maxAns;
        }

        return dp[0];
    }
}
