import java.util.ArrayList;
import java.util.Arrays;

public class dp6_House_Robber_II {
    public int rob2(int[] arr){
        int n = arr.length;
        if (n==1) {
            return arr[0];
        }
        ArrayList<Integer> temp1 = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        for(int i = 0;i<n;i++){
            if (i!=0) {temp1.add(arr[i]);}
            if (i!=n-1) {temp2.add(arr[i]);}
        }
        return Math.max(rob1(temp1), rob1(temp2));
    }
    private int rob1(ArrayList<Integer> arr){
        int n = arr.size();
        int[] dp= new int[n];
        Arrays.fill(dp, -1);
        dp[0] = arr.get(0);
        for (int i = 1; i < n; i++) {
            int take = arr.get(i);
            if(i>1) take+=dp[i-2];
            int not_take = 0 + dp[i-1];
            dp[i] = Math.max(take, not_take);
        }
        return dp[n-1];
    }
    public static void main(String[] args) {
        dp6 dp6 = new dp6();
        System.out.println(dp6.rob2(new int[]{1,2,3,1}));
    }
}
