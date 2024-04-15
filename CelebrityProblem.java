import java.util.Stack;

public class CelebrityProblem {

    // Returns true if a knows b, false otherwise
    private static boolean knows(int[][] matrix, int a, int b) {
        return matrix[a][b] == 1;
    }

    // Returns the celebrity in the group, or -1 if there is no celebrity
    public static int findCelebrity(int[][] matrix) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = matrix.length;

        // Push all people onto the stack
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        // Pop two people from the stack until there is only one person left
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();

            // If a knows b, then a is not the celebrity
            if (knows(matrix, a, b)) {
                stack.push(b);
            } 
            // If a does not know b, then b is not the celebrity
            else {
                stack.push(a);
            }
        }

        // Check if the remaining person is the celebrity
        int potentialCelebrity = stack.pop();
        for (int i = 0; i < n; i++) {
            if (i != potentialCelebrity && (knows(matrix, potentialCelebrity, i) || !knows(matrix, i, potentialCelebrity))) {
                return -1;
            }
        }

        return potentialCelebrity;
    }

    
    public static void main(String[] args) {
        int[][] matrix = {
            {0, 1, 0},
            {0, 0, 0},
            {1, 1, 0}
        };

        int celebrity = findCelebrity(matrix);

        if (celebrity == -1) {
            System.out.println("There is no celebrity in the group");
        } else {
            System.out.println("The celebrity is person " + celebrity);
        }
    }
}
