import java.util.Arrays;
import java.util.List;

public class RodCuttingSimple {

    public static int rodCutting(int[] prices, int length) {
        int[] memoization = new int[length + 1];
        Arrays.fill(memoization, Integer.MIN_VALUE);
        return rodCuttingHelper(prices, length, memoization);
    }

    private static int rodCuttingHelper(int[] prices, int length, int[] memoization) {
        if (length == 0) {
            return 0;
        }

        if (memoization[length] >= 0) {
            return memoization[length];
        }

        int maxProfit = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            maxProfit = Math.max(maxProfit, prices[i - 1] + rodCuttingHelper(prices, length - i, memoization));
        }

        memoization[length] = maxProfit;
        return maxProfit;
    }

    public static void main(String[] args) {
        /*int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 5;*/
        /*
        Generate input of big size:*/
        int n=100;
        int [] prices = new int[100];
        for (int i=0; i<99; i++)
            prices[i]=i+3;

       /* *Improve rodCutting algorithm by Dynamic Programming such that
        you can use the big size input.
         */


        int maxProfit = rodCutting(prices, n);

        System.out.println("The maximum profit from cutting a rod of length " + n + " is: " + maxProfit);
    }
}
