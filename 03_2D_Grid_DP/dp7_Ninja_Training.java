

public class dp7_Ninja_Training {
    public int maximumPoints(int mat[][]) {
        return ninjaTraining(mat.length, mat);
    }
    // Recursion
    // private int ninjaTraining(int day, int lastTraining, int[][] mat) {
    //     if(day == 0){
    //         int maxi= 0;
    //         for(int task=0;task<3;task++){
    //             if(task!=lastTraining){
    //                 maxi = Math.max(maxi, mat[day][task]);
    //             }
    //         }
    //         return maxi;
    //     }
    //     int maxi = 0;
    //     for(int task = 0;task < 3;task++){
    //         if(task!=lastTraining){
    //             int points = mat[day][task] + ninjaTraining(day-1, task, mat);
    //             maxi = Math.max(maxi,points);
    //         }
    //     }
    //     return maxi;
    // }

    // Memoization
    // private int ninjaTraining(int day, int lastTraining, int[][] mat, int[][] dp) {
    //     if(day == 0){
    //         int maxi= 0;
    //         for(int task=0;task<3;task++){
    //             if(task!=lastTraining){
    //                 maxi = Math.max(maxi, mat[day][task]);
    //             }
    //         }
    //         return maxi;
    //     }
    //     if(dp[day][lastTraining]!=-1) return dp[day][lastTraining];
    //     int maxi = 0;
    //     for(int task = 0;task < 3;task++){
    //         if(task!=lastTraining){
    //             int points = mat[day][task] + ninjaTraining(day-1, task, mat,dp);
    //             maxi = Math.max(maxi,points);
    //         }
    //     }
    //     dp[day][lastTraining] = maxi;
    //     return dp[day][lastTraining];
    // }

    // Tabulation
    private int ninjaTraining(int n, int[][] mat){
        int[][] dp=new int[n][4];
        dp[0][0] = Math.max(mat[0][1], mat[0][2]);
        dp[0][1] = Math.max(mat[0][0], mat[0][2]);
        dp[0][2] = Math.max(mat[0][0], mat[0][1]);
        dp[0][3] = Math.max(Math.max(mat[0][1], mat[0][2]), mat[0][0]);

        for (int day = 1; day < n; day++) {
            for (int lastTask = 0; lastTask < 4; lastTask++) {
                dp[day][lastTask]=0;
                for (int currTask = 0; currTask < 3; currTask++) {
                    if(currTask!=lastTask){
                        int points = mat[day][currTask] + dp[day-1][currTask];
                        dp[day][lastTask]=Math.max(points, dp[day][lastTask]);
                    }
                }
            }
        }
        return dp[n-1][3];
    }
    public static void main(String[] args) {
        
    }
}
