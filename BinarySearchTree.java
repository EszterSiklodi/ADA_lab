import java.util.Random;
public class BinarySearchTree {

    private static Node root;

    private static class Node {
        int key;
        Node left, right, parent;

        public  Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    public static  void insert(int key) {
        root = insert(root, key, null);
    }

    private static Node insert(Node x, int key, Node parent) {
        if (x == null) return new  Node(key, parent);
        if (key < x.key) x.left = insert(x.left, key, x);
        else if (key > x.key) x.right = insert(x.right, key, x);
        return x;
    }

   /*  public void inorderTreeWalk() {
        inorderTreeWalk(root);
    }

    private void inorderTreeWalk(Node x) {
        if (x != null) {
            inorderTreeWalk(x.left);
            System.out.print(x.key + " ");
            inorderTreeWalk(x.right);
        }
    }

    public void preorderTreeWalk() {
        preorderTreeWalk(root);
    }

    private void preorderTreeWalk(Node x) {
        if (x != null) {
            System.out.print(x.key + " ");
            preorderTreeWalk(x.left);
            preorderTreeWalk(x.right);
        }
    }
*/
    public static int height() {
        return  height(root);
    }

    private static int height(Node x) {
        if (x == null) return -1;
        //int leftHeight = height(x.left);
        //int rightHeight = height(x.right);
        return 1 + Math.max(height(x.left), height(x.right));
    }

  /*   public Node search(int key) {
        return search(root, key);
    }

    private Node search(Node x, int key) {
        if (x == null || x.key == key) return x;
        if (key < x.key) return search(x.left, key);
        else return search(x.right, key);
    }

    public Node successor(Node x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    private Node minimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }
    public Node searchClosest(int k) {
        return searchClosest(root, k, root);
    }
    
    private Node searchClosest(Node x, int k, Node closest) {  //we check if the current node x is null
        if (x == null) return closest;
        
        if (x.key == k) {
            return x;
        }
        
        if (Math.abs(x.key - k) < Math.abs(closest.key - k)) {
            closest = x;
        }
        
        if (k < x.key) {
            return searchClosest(x.left, k, closest);
        } else {
            return searchClosest(x.right, k, closest);
        }
    }
    */
    
   /*  public boolean isPerfectlyBalanced() {
        return isPerfectlyBalanced(root) != -1;
    }
    
    private int isPerfectlyBalanced(Node x) {
        if (x == null) return 0;//we first check if the root node is null
        
        int leftHeight = isPerfectlyBalanced(x.left);
        if (leftHeight == -1) return -1;
        System.out.println("The BST is not perfectly balanced. ");
        
        int rightHeight = isPerfectlyBalanced(x.right);
        if (rightHeight == -1) return -1;
        System.out.println("The BST is not perfectly balanced. ");
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    */
    
   /*  public void printPathFromTo(Node node1, Node node2) {
        Stack<Node> path1 = new Stack<>();
        Stack<Node> path2 = new Stack<>();
        
        // Find path to node1
        Node curr = root;
        while (curr != null && curr != node1) {
            path1.push(curr);
            if (node1.key < curr.key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        path1.push(node1);
        
        // Find path to node2
        curr = root;
        while (curr != null && curr != node2) {
            path2.push(curr);
            if (node2.key < curr.key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        path2.push(node2);
        
        Node lca = null;
        while (!path1.isEmpty() && !path2.isEmpty()) {
            Node node1Ancestor = path1.pop();
            Node node2Ancestor = path2.pop();
            
            if (node1Ancestor == node2Ancestor) {
                lca = node1Ancestor;
            } else {
                break;
            }
        }
        
        if (lca != null) {
            printPath(path1, lca);
            printPath(path2, lca.right); // Use lca.right because we already printed lca
        } else {
            // Continue traversing down subtree of current node to find LCA
            if (!path1.isEmpty()) {
                curr = path1.pop().right;
            } else {
                curr = path2.pop().right;
            }
            while (curr != null) {
                if (node1.key < curr.key && node2.key < curr.key) {
                    curr = curr.left;
                } else if (node1.key > curr.key && node2.key > curr.key) {
                    curr = curr.right;
                } else {
                    lca = curr;
                    break;
                }
            }
            if (lca != null) {
                printPath(path1, lca);
                printPath(path2, lca.right);
            }
        }
    }
    
    private void printPath(Stack<Node> path, Node end) {
        while (!path.isEmpty() && path.peek() != end) {
            System.out.print(path.pop().key + " -> ");
        }
        System.out.println(end.key);
    }
    */

   /*  public class PrintPathsWithSum {
    
        public List<List<Integer>> printPathsWithSum(TreeNode root, int sum) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            printPathsWithSumHelper(root, sum, currentPath, result);
            return result;
        }
        
        private void printPathsWithSumHelper(TreeNode node, int sum, List<Integer> currentPath, List<List<Integer>> result) {
            if (node == null) {
                return;
            }
            currentPath.add(node.val);
            int pathSum = 0;
            for (int i = currentPath.size() - 1; i >= 0; i--) {
                pathSum += currentPath.get(i);
                if (pathSum == sum) {
                    result.add(new ArrayList<>(currentPath.subList(i, currentPath.size())));
                }
            }
            printPathsWithSumHelper(node.left, sum, currentPath, result);
            printPathsWithSumHelper(node.right, sum, currentPath, result);
            currentPath.remove(currentPath.size() - 1);
        }
    }
    */
    public static void main(String[] args) {
        Random rand = new Random();
        BinarySearchTree BinarySearchTree = new BinarySearchTree();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            BinarySearchTree.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Tree height: " + BinarySearchTree.height());
        System.out.println("Insertion time: " + (end - start) + " ms");
    }
       /*  Random rand = new Random();
        for (int i = 0; i < 10000000; i++) {
            //keys[i] = rand.nextInt(N);
            BinarySearchTree.insert(i);
        }

        long start = System.nanoTime();
        long end = System.nanoTime();
        long time = (end - start)/1000000000;

        int height = height(root);

        System.out.println("Time taken for insertion: " + time + " nanoseconds");
        System.out.println("Height of tree: " + height);
    }
    */
   /*  public static void main(String[] args) {
        Random rand = new Random();
        BinarySearchTree tree = new BinarySearchTree();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            tree.insert(i);
        }
       // long end = System.currentTimeMillis();
       long start = System.nanoTime();
        //System.out.println("Tree height: " + tree.height());
        long end = System.nanoTime();
        long time = (end - start)/1000000000;

        System.out.println("Insertion time: " + time + " ns");
        System.out.println("Height: " + tree.height());
    }*/
       /*  BinarySearchTree bst = new BinarySearchTree();
        bst.insert(8);
        bst.insert(15);
        bst.insert(2);
        bst.insert(5);
        bst.insert(4);
        bst.insert(10);
        bst.insert(3);
        bst.insert(1);
        bst.insert(20);
        bst.insert(18);
        bst.insert(7);
        bst.insert(22);*/

       // System.out.println("The closest value:" + bst.searchClosest(16).key);

      //  System.out.println("Is perfectly balanced:" + bst.isPerfectlyBalanced());

       // System.out.println("The Path from 10 to 18:" + bst.printPathFromTo(10,18));
    
       //System.out.println("Printing paths with sum 22:" + bst.printPathsWithSum(22));

        //System.out.print("Inorder Tree Walk: ");
       // bst.inorderTreeWalk();
       // System.out.println();

        //System.out.print("Preorder Tree Walk: ");
        //bst.preorderTreeWalk();
        //System.out.println();

        

       // System.out.println("Search(4): " + bst.search(4).key);
        //System.out.println("Search(10): " + bst.search(10));

        //System.out.println("Successor(5): " + bst.successor(bst.search(5)).key);
       // System.out.println("Successor(7): " + bst.successor(bst.search(7)).key);
        //System.out.println("Successor(8): " + bst.successor(bst.search(8)));
    }

