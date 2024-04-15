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

public class PrintPathsWithSum {
    
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

public class PrintPathsWithSum {
    
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
