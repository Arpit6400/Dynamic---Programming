public class dp22 {
    public static void main(String[] args) {
        
    }

    // Recursion - Coin Change 2
    public int coinChange2(int idx, int target, int[] arr){
        if(idx == 0){
            if(target%arr[0]==0) return 1;
            else return 0;
        }
        int not_take = coinChange2(idx- 1, target, arr);
        int take = 0;
        if(arr[idx] <= target){
            take = coinChange2(idx, target - arr[idx], arr);
        }
        return take + not_take;
    }

    // Memoization - Agent Add

    // Tabulation
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for(int t=0;t<=amount;t++){
            if(t%coins[0] == 0) dp[0][t] = 1;
            else dp[0][t] = 0;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {
                int not_take = dp[idx -1][target];
                int take = 0;
                if(coins[idx] <= target){
                    take = dp[idx][target - coins[idx]];
                }
                dp[idx][target] = take + not_take;
            }
        }
        return dp[n - 1][amount];
    }
}
