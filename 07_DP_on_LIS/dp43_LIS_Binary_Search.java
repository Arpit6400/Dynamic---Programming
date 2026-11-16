
import java.util.*;

public class dp43_LIS_Binary_Search {
    // LIS Using Binary Search
    public int longestIncreasingSubsequence(int[] arr, int n) {
        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(arr[0]);
        int len = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > temp.get(temp.size() - 1)) {
                temp.add(arr[i]);
                len++;
            } else {
                int ind = lowerBound(temp, arr[i]);
                temp.set(ind, arr[i]);
            }
        }

        return len;
    }

    private int lowerBound(ArrayList<Integer> temp, int target) {
        int low = 0;
        int high = temp.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (temp.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}

