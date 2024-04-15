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

public void printPathFromTo(Node node1, Node node2) {
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
/*public void printPathFromTo(Node node1, Node node2) {
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
