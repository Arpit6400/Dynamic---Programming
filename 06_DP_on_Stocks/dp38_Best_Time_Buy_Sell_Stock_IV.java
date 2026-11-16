public class dp38_Best_Time_Buy_Sell_Stock_IV {
    //Buy & Sell Stocks 4 - LC 188
    public static int maxProfit3(int[] prices , int k) {
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
