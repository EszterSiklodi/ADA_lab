import java.util.Random;

public class AVLTree1 {
    private static class Node {
        int key, height;
        Node left, right;
        
        public Node(int key) {
            this.key = key;
            height = 0;
        }
    }
    
    private static Node root;
    
    public static void insert(int key) {
        root = insert(root, key);
    }
    
    private static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    private static int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }
    
    private static int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    
    private static Node leftRotate(Node node) {
        Node newRoot = node.right;
        Node tmp = newRoot.left;
        newRoot.left = node;
        node.right = tmp;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }
    
    private static Node rightRotate(Node node) {
        Node newRoot = node.left;
        Node tmp = newRoot.right;
        newRoot.right = node;
        node.left = tmp;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }
    
    public int getHeight() {
        return getHeight(root);
    }
    
    public static void main(String[] args) {
        int N = 1000000;
        int[] keys = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            keys[i] = rand.nextInt(N);
        }

        long start = System.nanoTime();

        for (int i = 0; i < N; i++) {
            root = insert(root, keys[i]);
        }

        long end = System.nanoTime();
        long time = (end - start)/1000000;

        int height = getHeight(root);

        System.out.println("Time taken for insertion: " + time + " ms");
        System.out.println("Height of tree: " + height);
    }
        /*Random rand = new Random();
        AVLTree1 tree = new AVLTree1();
        long start = System.currentTimeMillis();
        for (int i = 0; i <N; i++) {
            tree.insert(rand.nextInt(1000000));
        }
        long end = System.currentTimeMillis();
        System.out.println("Tree height: " + tree.getHeight());
        System.out.println("Insertion time: " + (end - start) + " ms");
        */
    }

