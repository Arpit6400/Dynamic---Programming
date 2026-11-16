import java.util.*;

public class dp46_Longest_Bitonic_Subsequence {
    public static void main(String[] args) {
    System.out.println(longestBitonicSequence(5, new int[]{1,2,5,3,2}));
    }

    public static int longestBitonicSequence(int n, int[] nums) {
        // code here
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if (nums[prev] < nums[idx]) {
                    dp1[idx] = Math.max(dp1[idx], dp1[prev] + 1);
                }
            }
            for(int prev = n - 1; prev > n - 1 - idx ; prev--){
                if(nums[prev] < nums[n - 1 - idx]){
                    dp2[n - 1 - idx] = Math.max(dp2[n - 1 - idx], dp2[prev] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(dp1[i] > 1 && dp2[i] > 1){
                ans = Math.max(ans, dp1[i] + dp2[i] - 1); 
            }
        }
        return ans;
    }

    public static int longestIncreasingSubsequence1D(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;

        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if (arr[prev] < arr[idx]) {
                    dp[idx] = Math.max(dp[idx], dp[prev] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[idx]);
        }

        return maxLength;
    }
}
