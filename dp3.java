public class dp3 {
    // DP
    public static int minCost(int[] height) {
        if (height.length ==1) {
            return 0;
        }
        if (height.length == 2) {
            return Math.abs(height[0]-height[1]);
        }
        int n = height.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = Math.abs(height[0] - height[1]);
        for (int i = 2; i< n; i++) {
            int left = dp[i-1] + Math.abs(height[i] - height[i-1]);
            int right = dp[i-2] + Math.abs(height[i] - height[i-2]);
            dp[i] = Math.min(left, right);
        }
        return dp[n-1];
    }
    // Space Optimization
    public static int minCost2(int[] height) {
        if (height.length ==1) {
            return 0;
        }
        if (height.length == 2) {
            return Math.abs(height[0]-height[1]);
        }
        int n = height.length;
        int secondLast = 0;
        int last = Math.abs(height[0] - height[1]);
        for (int i = 2; i< n; i++) {
            int left = last + Math.abs(height[i] - height[i-1]);
            int right = secondLast + Math.abs(height[i] - height[i-2]);
            int curr = Math.min(left, right);
            secondLast = last;
            last = curr;
        }
        return last;
    }
    public static void main(String[] args) {
        int[] height = {10, 30, 40, 20, 50};
        System.out.println(minCost(height));
        System.out.println(minCost2(height));
    }
}
