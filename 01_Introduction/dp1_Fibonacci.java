
// LC: https://leetcode.com/problems/fibonacci-number/
// GFG: https://www.geeksforgeeks.org/problems/nth-fibonacci-number1359/1
import java.util.Arrays;

public class dp1_Fibonacci {
    // Fibonnaci 1st memoization approach
    public int fibbo(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != -1)
            return dp[n];

        dp[n] = fibbo(n - 1, dp) + fibbo(n - 2, dp);
        return dp[n];
    }

    // Fibonnaci 2nd Tabulation approach
    public int fibbo2(int n, int[] dp) {
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Fibonnaci 3rd Tabulation + spaceOptimization
    public int fibbo3(int n) {
        int last = 1;
        int secondLast = 0;
        for (int i = 2; i <= n; i++) {
            int temp = last + secondLast;
            secondLast = last;
            last = temp;
        }
        return last;
    }

    public static void main(String[] args) {
        dp1_Fibonacci dp1 = new dp1_Fibonacci();
        int n = 5;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(dp1.fibbo(n, dp));
        System.out.println(dp1.fibbo2(n, dp));
        System.out.println(dp1.fibbo3(n));
    }
}
