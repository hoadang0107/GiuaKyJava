package application.BST;


import application.BST.BST;



public class AVL<E extends Comparable<E>> extends BST<E> {

    public AVL() {//tạo một cây AVL mặc định
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E o) {
        return new AVLTreeNode<E>(o);
    }
    @Override
    public boolean insert(E o) {
        boolean successful = super.insert(o);
        if (!successful) {
            return false; // o is already in the tree
        } else {
            balancePath(o); // Balance from o to the root if necessary
        }

        return true; // o is inserted
    }

    //cập nhật chiều cao của nút chỉ định
    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null) // nếu nút là lá
        {
            node.height = 0;
        } else if (node.left == null) // node has no left subtree
        {
            node.height = 1 + ((AVLTreeNode<E>) (node.right)).height;
        } else if (node.right == null) // node has no right subtree
        {
            node.height = 1 + ((AVLTreeNode<E>) (node.left)).height;
        } else {
            node.height = 1
                    + Math.max(((AVLTreeNode<E>) (node.right)).height,
                    ((AVLTreeNode<E>) (node.left)).height);
        }
    }

    //Cân bằng các nút trong đường dẫn từ nút được chỉ định đến gốc nếu cần thiết
    private void balancePath(E o) {
        java.util.ArrayList<TreeNode<E>> path = path(o);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null
                    : (AVLTreeNode<E>) (path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
                        balanceLL(A, parentOfA); // Perform LL rotation
                    } else {
                        balanceLR(A, parentOfA); // Perform LR rotation
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A, parentOfA); // Perform RR rotation
                    } else {
                        balanceRL(A, parentOfA); // Perform RL rotation
                    }
            }
        }
    }

    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null) // node has no right subtree
        {
            return -node.height;
        } else if (node.left == null) // node has no left subtree
        {
            return +node.height;
        } else {
            return ((AVLTreeNode<E>) node.right).height
                    - ((AVLTreeNode<E>) node.left).height;
        }
    }

  
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.left = B.right; // Make T2 the left subtree of A
        B.right = A; // Make A the left child of B
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left; // A is left-heavy
        TreeNode<E> C = B.right; // B is right-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right; // Make T3 the left subtree of A
        B.right = C.left; // Make T2 the right subtree of B
        C.left = B;
        C.right = A;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.right = B.left; // Make T2 the right subtree of A
        B.left = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left; // Make T2 the right subtree of A
        B.left = C.right; // Make T3 the left subtree of B
        C.left = A;
        C.right = B;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

   
    @Override
    public boolean delete(E element) {
        if (root == null) {
            return false; // Element is not in the tree
        }
        // Xác định vị trí nút cần xóa và cũng xác định vị trí nút cha của nó
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (element.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }

        if (current == null) {
            return false; // Element is not in the tree
        }
        // Case 1: current has no left children (See Figure 23.6)
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (element.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }

            // Balance the tree if necessary
            balancePath(parent.element);
        } else {
        	
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost is current
            {
                parentOfRightMost.left = rightMost.left;
            }

            // Balance the tree if necessary
            balancePath(parentOfRightMost.element);
        }

        size--;
        return true; // Element inserted
    }

    protected static class AVLTreeNode<E extends Comparable<E>> extends BST.TreeNode<E> {

        int height = 0; // New data field

        public AVLTreeNode(E o) {
            super(o);
        }
    }
}
