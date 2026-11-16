public class dp23_Unbounded_Knapsack {
    public static void main(String[] args) {
    }

    // Recursion - Unbounded Knapsack
    public int unBoundedKnapsack(int idx, int wt, int[] val, int[] w ){
        if(idx == 0){
            return (wt/w[0]) * val[0];
        }

        int not_take = 0 + unBoundedKnapsack(idx - 1, wt, val, w);
        int take = - (int) 1e9;
        if(w[idx] <= wt){
            take = val[idx] + unBoundedKnapsack(idx - 1, wt - w[idx], val, w);
        }
        return Math.max(not_take, take);
    }

    // Memoization - Agent add

    // Tabulation
    public int knapSack(int val[], int wt[], int capacity) {
        int n = val.length;
        int[][] dp = new int[n][capacity + 1];

        for(int i = 0; i<= capacity; i++){
            dp[0][i] = (i/wt[0])*val[0];
        }
        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= capacity; target++) {
                int not_take = dp[idx -1][target];
                int take = Integer.MIN_VALUE;
                if(wt[idx] <= target){
                    take =  val[idx] + dp[idx][target - wt[idx]];
                }
                dp[idx][target] = Math.max(not_take, take);
            }
        }
        return dp[n-1][capacity];
    }

}
