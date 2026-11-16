public class dp20_Minimum_Coins {
    public static void main(String[] args) {
        
    }

    // Recursion - Minimum Coins 
    int minimumCoins(int idx, int target, int[] arr){
        if(idx == 0){
            if(target%arr[0]==0) return target/arr[0];
            else return (int) 1e9;
        }
        int not_take = 0 + minimumCoins(idx- 1, target, arr);
        int take = (int) 1e9;
        if(arr[idx] <= target){
            take = 1 + minimumCoins(idx, target - arr[idx], arr);
        }
        return Math.min(take, not_take);
    }

    // Memoization - Agent Add

    // Tabulation
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for(int t=0;t<=amount;t++){
            if(t%coins[0] == 0) dp[0][t] = t/coins[0];
            else dp[0][t] = (int) 1e9;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {
                int not_take = 0 + dp[idx -1][target];
                int take = (int) 1e9;
                if(coins[idx] <= target){
                    take = 1 + dp[idx][target - coins[idx]];
                }
                dp[idx][target] = Math.min(take, not_take);
            }
        }
        return dp[n - 1][amount] != (int) 1e9 ? dp[n - 1][amount] : -1;
    }
}
