public class dp18 {
    public static void main(String[] args) {
        
    }

    // Tabulation -  Count Partitions With Given Difference
    public int countPartitions(int[] arr, int diff) {
        // code here
        int totalSum = 0;
        for(int val : arr){
            totalSum += val;
        }

        if((totalSum + diff)%2 != 0) return 0;
        if(totalSum < diff) return 0;

        return perfectSum(arr, (totalSum + diff)/2);

    }

    public int perfectSum(int[] arr, int target) {
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
