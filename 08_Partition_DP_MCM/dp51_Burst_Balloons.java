public class dp51_Burst_Balloons {

    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3,1,5,8}));
    }
    // Burst Balloons - Recursion
    public static int burstBalloonsRecursion(int i, int j, int[] nums){
        if(i > j) return 0;

        int max = Integer.MIN_VALUE;
        for (int idx = i; idx <= j; idx++) {
            int cost = nums[i - 1] * nums[idx] * nums[j + 1] + 
                     + burstBalloonsRecursion(i, idx - 1, nums)
                     + burstBalloonsRecursion(idx + 1, j, nums);
            max = Math.max(max, cost);
        }
        return max;
    }

    // Memoization - agent add

    // Tabulation
    public static int maxCoins(int[] nums) {
        int n = nums.length;

        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int max = 0;

                for (int idx = i; idx <= j; idx++) {
                    int cost = balloons[i - 1] * balloons[idx] * balloons[j + 1]
                            + dp[i][idx - 1]
                            + dp[idx + 1][j];

                    max = Math.max(max, cost);
                }

                dp[i][j] = max;
            }
        }

        return dp[1][n];
    }
}

