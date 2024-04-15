import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*public class RodCuttingComplete {
public static List<Integer> rodCutting(int[] prices, int length) {
        if (length==0) {
            return new ArrayList<Integer>();
        }

        List<Integer> maxPieces=null;
        int maxProfit=Integer.MIN_VALUE;
        for (int i=1; i<=length; i++) {
            List<Integer> pieces=rodCutting(prices, length - i);
            int profit=0;
            for (Integer p:pieces)
                profit=profit+prices[p-1];
            profit=profit+prices[i-1];
            if (profit>maxProfit) {
                maxProfit=profit;
                maxPieces=new ArrayList<Integer>();
                maxPieces.add(i);
                maxPieces.addAll(pieces);
            }
        }

        return maxPieces;
    } */
    public class RodCuttingComplete {  
    public static List<Integer> rodCutting(int[] prices, int length) {
        List<Integer>[][] memoization = new List[length + 1][length + 1];
        for (List<Integer>[] row : memoization) {
            Arrays.fill(row, null);
        }

        return rodCuttingHelper(prices, length, length, memoization);
    }

    private static List<Integer> rodCuttingHelper(int[] prices, int length, int remaining, List<Integer>[][] memoization) {
        if (remaining == 0) {
            return new ArrayList<>();
        }

        if (memoization[length][remaining] != null) {
            return new ArrayList<>(memoization[length][remaining]);
        }

        List<Integer> maxPieces = null;
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            if (i <= remaining) {
                List<Integer> pieces = rodCuttingHelper(prices, length, remaining - i, memoization);
                int profit = 0;
                for (Integer p : pieces) {
                    profit += prices[p - 1];
                }
                profit += prices[i - 1];
                if (profit > maxProfit) {
                    maxProfit = profit;
                    maxPieces = new ArrayList<>();
                    maxPieces.add(i);
                    maxPieces.addAll(pieces);
                }
            }
        }

        memoization[length][remaining] = new ArrayList<>(maxPieces);
        return maxPieces;
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 5;
        
        /*Generate input of big size:
        int n=100;
        int [] prices = new int[100]; 
        for (int i=0; i<99; i++)
            prices[i]=i+3;
        
        Improve rodCutting algorithm by Dynamic Programming such that 
        you can use the big size input.*/
         

        List<Integer> list = rodCutting(prices, n);

        System.out.println("The maximum profit is obtained by cutting pieces of the following lengths: " + list);
        int profit = 0;
        for (Integer l : list) {
            profit += prices[l - 1];
        }
        System.out.println("The value of the maximum profit is: " + profit);
    }
}
