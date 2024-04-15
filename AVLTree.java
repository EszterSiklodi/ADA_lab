public class AVLTree {
    private static Node root;
    
    private class Node {
        int  val, height;
        Node left, right;
        
        Node(int val) {
            this.val = val;
            this.height = 1;
        }
    }
    
    public void insert(int val) {
        root = insert(root, val);
    }
    
    private Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        } else {
            return node;
        }
        
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        
        int balance = getBalance(node);
        
        if (balance > 1 && val < node.left.val) {
            return rightRotate(node);
        }
        
        if (balance < -1 && val > node.right.val) {
            return leftRotate(node);
        }
        
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        
        return node.height;
    }
    
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        
        return getHeight(node.left) - getHeight(node.right);
    }
    
    private Node rightRotate(Node node) {
       System.out.println("r:"+node.val);
        Node x = node.left;
        Node T2 = x.right;
        
        x.right = node;
        node.left = T2;
        
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        
        return x;
        //System.out.println("Left rotate:"+tree.rightRotate());
    }
    
    private Node leftRotate(Node node) {
        System.out.println("l:"+node.val);
        Node x = node.right;
        Node T2 = x.left;
        
        x.left = node;
        node.right = T2;
        
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        
        return x;

        //System.out.println("Left rotate:"+tree.leftRotate());
    }
    
    public void checkBalance() {
         checkBalance(root);
    }
    
    private void checkBalance(Node node) {
        if (node != null) {
            int balance = getBalance(node);
            if (balance > 1 || balance < -1) {
                System.out.println("Tree is not balanced");
                return ;
            }
            
            checkBalance(node.left);
            checkBalance(node.right);
        }
        
    }

   public static void main(String[] args) {
    AVLTree tree = new AVLTree();

    tree.insert(3);
    tree.insert(6);
    tree.insert(18);
    tree.insert(2);
    tree.insert(1);
    tree.insert(13);
    tree.insert(16);
    tree.insert(4);

    tree.checkBalance();
   //System.out.println("the balanced AVL tree:"+tree.checkBalance());
  // System.out.println("Left rotate:"+tree.leftRotate(root));
   //System.out.println("Right rotate:"+tree.rightRotate(root));
    }
}


