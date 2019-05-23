package application.BST;




 //BTView Description: Displays Binary tree from BST in pane


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 20; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    BTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayBSTTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayBSTTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4,"");
        }
    }
    
    public void displayBSTTree(String s) {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayBSTTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4,s);
        }
    }

    /*
     * Display a subtree rooted at position (x, y)
     */
    private void displayBSTTree(BST.TreeNode<Integer> root,
                                double x, double y, double hGap,String s) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayBSTTree(root.left, x - hGap, y + vGap, hGap / 2,s);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayBSTTree(root.right, x + hGap, y + vGap, hGap / 2,s);
        }

        // Display a node
        
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.YELLOW);
        circle.setStroke(Color.BLACK);
        if(s.compareTo(root.element+"") == 0) {
        	circle.setFill(Color.RED);
        }
        getChildren().addAll(circle,
                new Text(x - 8, y + 4, root.element + ""));
    }
}
