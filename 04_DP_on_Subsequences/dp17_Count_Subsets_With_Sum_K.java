public class dp17_Count_Subsets_With_Sum_K {
    public static void main(String[] args) {

        System.out.println(perfectSum(new int[]{0,10,0}, 0));
    }

    // Recursion
    public int countSubsetsWithSumK(int idx, int sum, int[] arr) {
        if (sum == 0)
            return 1;
        if (idx == 0) {
            if (arr[idx] == sum)
                return 1;
            else
                return 0;
        }
        int not_take = countSubsetsWithSumK(idx + 1, sum, arr);
        int take = 0;
        if (arr[idx] <= sum) {
            take = countSubsetsWithSumK(idx + 1, sum - arr[idx], arr);
        }
        return take + not_take;
    }

    // Memoization - Agent add

    // Tabulation
    public static int perfectSum(int[] arr, int target) {
        // code here
        int n = arr.length;
        int MOD = 1_000_000_007;
        int[][] dp = new int[n][target + 1];
        for(int i = 0; i < n ; i++){
            dp[i][0] = 1;
        }
        if(arr[0] <= target) dp[0][arr[0]] = 1;

        if(arr[0] == 0) dp[0][0] =2;

        for(int idx = 1; idx < n; idx++){
            for(int sum = 0; sum <= target; sum ++){
                int not_take = dp[idx - 1][sum];
                int take = 0;
                if(arr[idx] <= sum) take = dp[idx -1][sum - arr[idx]];
                dp[idx][sum] = (take + not_take)%MOD;
            }
        }
        return dp[n-1][target];
    }
}
