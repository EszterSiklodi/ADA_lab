class Node<T extends Comparable> {
    public T key;

    public Node left, right, p;

    public Node(T key) {
        this.key = key;
    }
}


public class BST <T extends Comparable>{

private Node<T> root;             // root of BST


public BST() {
    root=null;
}


public void insert(T k) {
    Node x, y;
    Node z= new Node(k);
    y=null;
    x=root;
    while (x!=null) {
        y=x;
        if (x.key.compareTo(z.key)<0)
            x=x.right;
        else
            x=x.left;
    }
    z.p=y;
    if (y==null)
        root=z;
    else
        if (y.key.compareTo(z.key)<0)
            y.right=z;
        else
            y.left=z;

}

public class PrintLevels {
    
    public void printLevels(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            System.out.println();
        }
    }
}


public static void main(String[] args) {

    BST<Integer> st1=new BST<Integer>();
    st1.insert(5);
    st1.insert(2);
    st1.insert(10);
    st1.insert(8);
    st1.insert(15);
    

    BST<String> st2=new BST<String>();
    st2.insert("dog");
    st2.insert("bear");
    st2.insert("cat");
    st2.insert("fish");
    st2.insert("wolf");
   

}

}

/* 
// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class PrintLevels {
    
    public void printLevels(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            System.out.println();
        }
    }
}
*/