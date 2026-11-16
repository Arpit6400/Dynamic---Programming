// LC: https://leetcode.com/problems/maximal-rectangle/
// GFG: https://www.geeksforgeeks.org/problems/max-rectangle/1

import java.util.Stack;

public class dp55_Maximum_Rectangle_Area_With_All_1s {

    public static void main(String[] args) {
        char[][] matrix = {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' }
        };
        System.out.println("Maximal Rectangle Area: " + maximalRectangle(matrix));
    }

    // Helper: Largest Rectangle in Histogram (O(M) time, O(M) space using Stack)
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!st.isEmpty() && h < heights[st.peek()]) {
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            st.push(i);
        }
        return maxArea;
    }

    // DP on Rectangles - Largest Rectangle of 1's
    // Concept: Treat each row as the base of a histogram.
    // If matrix[i][j] == '1', height[j] += 1; else height[j] = 0.
    // Time Complexity: O(N * M), Space Complexity: O(M)
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // For each row's histogram, find max area
            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Overload for int[][] matrix (GFG format)
    public static int maxArea(int[][] M, int n, int m) {
        int[] heights = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (M[i][j] == 1)
                    heights[j]++;
                else
                    heights[j] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }
}
