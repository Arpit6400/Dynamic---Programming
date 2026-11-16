import java.util.Arrays;
public class dp42_Print_LIS {
    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println("Length of Longest Increasing Subsequence: " + longestIncreasingSubsequence1D(arr));
        printLongestIncreasingSubsequence(arr);
    }

    // LIS Using 1D DP
    public static int longestIncreasingSubsequence1D(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;

        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if (arr[prev] < arr[idx]) {
                    dp[idx] = Math.max(dp[idx], dp[prev] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[idx]);
        }

        return maxLength;
    }

    // Print the Longest Increasing Subsequence using hashing
    public static void printLongestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            hash[i] = i; // Initialize hash array
        }

        int maxLength = 1;
        int lastIndex = 0;

        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if (arr[prev] < arr[idx] && dp[prev] + 1 > dp[idx]) {
                    dp[idx] = dp[prev] + 1;
                    hash[idx] = prev; // Update hash to point to the previous index
                }
            }
            if (dp[idx] > maxLength) {
                maxLength = dp[idx];
                lastIndex = idx; // Update last index of the LIS
            }
        }

        // Reconstruct the LIS using the hash array
        int[] lis = new int[maxLength];
        int currentIndex = lastIndex;
        for (int i = maxLength - 1; i >= 0; i--) {
            lis[i] = arr[currentIndex];
            currentIndex = hash[currentIndex]; // Move to the previous index in the LIS
        }

        System.out.print("Longest Increasing Subsequence: ");
        for (int num : lis) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
