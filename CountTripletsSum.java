/*import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CountTripletsSum {
    public static int countTriplets(int[] arr, int V) {
        int count = 0;
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum == V) {
                    count++;
                    j++;
                    k--;
                } else if (sum < V) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String filename = args[0];
        int[] arr = readInputFile(filename);
        long startTime = System.currentTimeMillis();
        int count = countTriplets(arr, 0);
        long endTime = System.currentTimeMillis();
        long runtime = endTime - startTime;
        System.out.println("Triplets count: " + count);
        System.out.println("Runtime: " + runtime + " ms");
    }

    public static int[] readInputFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            scanner.close();
            return arr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/
import java.io.*;
import java.util.*;

public class CountTripletsSum {
    static void countTriplets(int[] arr) {
        //BrutForce: 3 nested for loops->O(n^3)
        int count = 0;
        for(int i = 0; i < arr.length - 2; i++)
            for(int j = i + 1; j < arr.length - 1; j++)
                for(int k = j + 1; k < arr.length; k++)
                    if(arr[i] + arr[j] + arr[k] == 0)
                        count++;
        System.out.println(count);
    }

    static void countTripletsSum(int[] arr) {
        int count = 0;

        //With sorting->2 nested loops->O(n^2)
        Arrays.sort(arr);

        for(int i = 0; i < arr.length - 1; i++){
            int sum;
            int left = i + 1;
            int right = arr.length - 1;
            while(left < right) {
                sum = arr[i] + arr[left] + arr[right];
                if(sum == 0) {
                    count++;
                    left++;
                    right--;
                }
                else if(sum < 0)
                    left++;
                else
                    right--;
            }
        }
        System.out.println(count);
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("30Kint_3.txt"));

        int[] arr = new int[scan.nextInt()];

        for(int i = 0; i < arr.length; i++)
            arr[i] = scan.nextInt();

        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();

        countTriplets(arr);

        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();

        System.out.println("Time taken in nano seconds: " + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: " + (millis_endTime - millis_startTime));

        nano_startTime = System.nanoTime();
        millis_startTime = System.currentTimeMillis();

        countTripletsSum(arr);

        nano_endTime = System.nanoTime();
        millis_endTime = System.currentTimeMillis();

        System.out.println("Time taken in nano seconds: " + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: " + (millis_endTime - millis_startTime));
    }
}