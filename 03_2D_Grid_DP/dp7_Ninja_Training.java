// GFG: https://www.geeksforgeeks.org/problems/geeks-training/1
// CodingNinjas: https://www.naukri.com/code360/problems/ninja-s-training_3621003

import java.util.Arrays;

public class dp7_Ninja_Training {

    public static void main(String[] args) {
        dp7_Ninja_Training solver = new dp7_Ninja_Training();
        int[][] mat = {
                { 10, 40, 70 },
                { 20, 50, 80 },
                { 30, 60, 90 }
        };
        System.out.println("Tabulation: " + solver.maximumPoints(mat));
        System.out.println("Space Opt: " + solver.maximumPointsSpaceOpt(mat));
    }

    public int maximumPoints(int mat[][]) {
        return ninjaTrainingTabulation(mat.length, mat);
    }

    public int maximumPointsSpaceOpt(int mat[][]) {
        return ninjaTrainingSpaceOpt(mat.length, mat);
    }

    // Recursion
    private int ninjaTrainingRec(int day, int lastTraining, int[][] mat) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != lastTraining) {
                    maxi = Math.max(maxi, mat[0][task]);
                }
            }
            return maxi;
        }

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != lastTraining) {
                int points = mat[day][task] + ninjaTrainingRec(day - 1, task, mat);
                maxi = Math.max(maxi, points);
            }
        }
        return maxi;
    }

    // Memoization
    private int ninjaTrainingMemo(int day, int lastTraining, int[][] mat, int[][] dp) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != lastTraining) {
                    maxi = Math.max(maxi, mat[0][task]);
                }
            }
            return maxi;
        }
        if (dp[day][lastTraining] != -1)
            return dp[day][lastTraining];

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != lastTraining) {
                int points = mat[day][task] + ninjaTrainingMemo(day - 1, task, mat, dp);
                maxi = Math.max(maxi, points);
            }
        }
        return dp[day][lastTraining] = maxi;
    }

    // Tabulation - O(N * 4 * 3) Time, O(N * 4) Space
    private int ninjaTrainingTabulation(int n, int[][] mat) {
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(mat[0][1], mat[0][2]);
        dp[0][1] = Math.max(mat[0][0], mat[0][2]);
        dp[0][2] = Math.max(mat[0][0], mat[0][1]);
        dp[0][3] = Math.max(Math.max(mat[0][1], mat[0][2]), mat[0][0]);

        for (int day = 1; day < n; day++) {
            for (int lastTask = 0; lastTask < 4; lastTask++) {
                dp[day][lastTask] = 0;
                for (int currTask = 0; currTask < 3; currTask++) {
                    if (currTask != lastTask) {
                        int points = mat[day][currTask] + dp[day - 1][currTask];
                        dp[day][lastTask] = Math.max(points, dp[day][lastTask]);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }

    // Space Optimization - O(N * 4 * 3) Time, O(4) Space
    private int ninjaTrainingSpaceOpt(int n, int[][] mat) {
        int[] prev = new int[4];
        prev[0] = Math.max(mat[0][1], mat[0][2]);
        prev[1] = Math.max(mat[0][0], mat[0][2]);
        prev[2] = Math.max(mat[0][0], mat[0][1]);
        prev[3] = Math.max(Math.max(mat[0][1], mat[0][2]), mat[0][0]);

        for (int day = 1; day < n; day++) {
            int[] curr = new int[4];
            for (int lastTask = 0; lastTask < 4; lastTask++) {
                curr[lastTask] = 0;
                for (int currTask = 0; currTask < 3; currTask++) {
                    if (currTask != lastTask) {
                        int points = mat[day][currTask] + prev[currTask];
                        curr[lastTask] = Math.max(points, curr[lastTask]);
                    }
                }
            }
            prev = curr;
        }
        return prev[3];
    }
}
