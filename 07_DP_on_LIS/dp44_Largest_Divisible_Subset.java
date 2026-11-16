import java.util.*;
public class dp44_Largest_Divisible_Subset {
    
    // Largest Divisible Subset
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
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
                if (nums[idx] % nums[prev] == 0 && dp[prev] + 1 > dp[idx]) {
                    dp[idx] = dp[prev] + 1;
                    hash[idx] = prev; // Update hash to point to the previous index
                }
            }
            if (dp[idx] > maxLength) {
                maxLength = dp[idx];
                lastIndex = idx; // Update last index of the largest divisible subset
            }
        }

        // Reconstruct the largest divisible subset using the hash array
        List<Integer> result = new ArrayList<>();
        int currentIndex = lastIndex;
        for (int i = 0; i < maxLength; i++) {
            result.add(nums[currentIndex]);
            currentIndex = hash[currentIndex]; // Move to the previous index in the subset
        }
        

        Collections.reverse(result); // Reverse to get the correct order
        return result;
    }
}
