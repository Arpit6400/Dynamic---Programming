public class dp37 {
     public static void main(String[] args) {
        
     }
    public int maxProfit2(int idx, boolean buy,int[] prices, int cap){
        if(cap == 0) return 0;
        if(idx == prices.length){
            return 0;
        }
        if(buy){
            int buy_stock = - prices[idx] + maxProfit2(idx + 1, false, prices, cap);
            int not_buy_stock = 0 + maxProfit2(idx + 1, true, prices, cap);
            return Math.max(buy_stock, not_buy_stock);
        }
        else{
            int sell = prices[idx] + maxProfit2(idx + 1, true, prices , cap - 1);
            int not_sell = 0 + maxProfit2(idx + 1, false, prices, cap);
            return Math.max(sell, not_sell);
        }
    }

    // Memoization - agent add

    // Tabulation 
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        // Base case 1
        for (int idx = 0; idx <= n; idx++) {
           for (int buy = 0; buy <= 1; buy++) {
             dp[n][buy][0] = 0;
           }
        }
        // Base case 2
        for (int buy = 0; buy <= 1; buy++) {
            for (int cap = 0; cap <= 2; cap++) {
                dp[n][buy][cap] = 0;
            }
        }


        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                long profit = 0;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][0][cap], 0 + dp[idx + 1][1][cap]);
                } else {
                    profit = Math.max(prices[idx] + dp[idx + 1][1][cap -1], 0 + dp[idx + 1][0][cap]);
                }
                dp[idx][buy][cap] = (int) profit;
                }
            }
        }
        return dp[0][1][2];
    }

    // Tabulation + spaceoptimization
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] after = new int[2][3];
        int[][] curr = new int[2][3];

        // Base case 1 - 2 = Not needed as setting 0

        // Table
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                long profit = 0;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + after[0][cap], 0 + after[1][cap]);
                } else {
                    profit = Math.max(prices[idx] + after[1][cap -1], 0 + after[0][cap]);
                }
                curr[buy][cap] = (int) profit;
                }
            }
            after = curr;
        }
        return after[1][2];
    }

    // Sol using n X 4 dp array
    public static int maxProfit3(int[] prices) {
        int k = 2;// Set of transaction
        int n = prices.length;
        int transaction = 2 * k;
        int[][] dp = new int[n + 1][transaction + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int tran = 0; tran < transaction; tran++) {
                long profit = 0;
                if (tran % 2 == 0) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][tran + 1], 0 + dp[idx + 1][tran]);
                } else {
                    profit = Math.max(prices[idx] + dp[idx + 1][tran + 1], 0 + dp[idx + 1][tran]);
                }
                dp[idx][tran] = (int) profit;
            }
        }
        return dp[0][0];
    }
}
