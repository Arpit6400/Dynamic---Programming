public class dp41_Longest_Increasing_Subsequence {
    public static void main(String[] args) {
        
    }

    // New type DP - Longest Increasing Subsequence
    public static int longestIncreasingSubsequence(int idx, int prev_idx, int[] arr){
        if(idx == arr.length) return 0;

        int not_take = 0 + longestIncreasingSubsequence(idx + 1, prev_idx, arr);
        int take = 0;
        if(prev_idx == -1 || arr[idx] > arr[prev_idx]){
            take = 1 + longestIncreasingSubsequence(idx + 1, idx, arr);
        }
        return Math.max(take, not_take);
    }

    // Index shifting for DP
    public static int longestIncreasingSubsequence2(int idx, int prev_idx, int[] arr){
        if(idx == arr.length) return 0;

        int not_take = 0 + longestIncreasingSubsequence2(idx + 1, prev_idx, arr);
        int take = 0;
        if(prev_idx == 0 || arr[idx] > arr[prev_idx - 1]){
            take = 1 + longestIncreasingSubsequence2(idx + 1, idx + 1, arr);
        }
        return Math.max(take, not_take);
    }

    // Memoization - agent add

    // Tabulation
    public static int longestIncreasingSubsequenceTabulation(int[] arr) {
    int n = arr.length;
    int[][] dp = new int[n + 1][n + 1];

    for (int idx = n - 1; idx >= 0; idx--) {
        for (int prev_idx = 0; prev_idx <= idx; prev_idx++) {

            int not_take = 0 + dp[idx + 1][prev_idx];

            int take = 0;
            if (prev_idx == 0 || arr[idx] > arr[prev_idx - 1]) {
                take = 1 + dp[idx + 1][idx + 1];
            }

            dp[idx][prev_idx] = Math.max(take, not_take);
        }
    }
    return dp[0][0];
  }
}
