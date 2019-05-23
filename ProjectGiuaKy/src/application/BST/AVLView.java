package application.BST;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AVLView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 20; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    AVLView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayAVLTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayAVLTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }
    
    /*
     * Display a subtree rooted at position (x, y)
     */
    private void displayAVLTree(AVL.TreeNode<Integer> root,
                                double x, double y, double hGap) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayAVLTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayAVLTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.CYAN);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
                new Text(x - 8, y + 4, root.element + ""));
    }
}