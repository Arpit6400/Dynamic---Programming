public class dp2_Climbing_Stairs {

    // public int climbStairs(int n) {
    //     int ans = solve(n, 0);
    //     return ans;
    // }
    // // Recursive
    // private int solve(int n, int idx){
    //     if(idx == n) return 1;
    //     int left = 0;
    //     if(idx + 1 <= n){
    //         left+=solve(n, idx+1);
    //     }
    //     int right = 0;
    //     if(idx + 2 <= n){
    //         right+=solve(n, idx+2);
    //     }
    //     return left + right;
    // }
    // DP
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        dp2 dp2 = new dp2();
        System.out.println(dp2.climbStairs(3));
    }
}
