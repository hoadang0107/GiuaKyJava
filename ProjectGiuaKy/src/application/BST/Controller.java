package application.BST;


import java.util.HashSet;

import application.BST.BST;
import application.BST.BTView;
import application.BST.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Controller {
	@FXML private BorderPane bPane;
	@FXML private Pane pane;
	@FXML private HBox hBox;
	@FXML private TextField txt1; 
	
	BST<Integer> BSTtree = new BST<>(); // Create a BST tree
	BTView view = new BTView(BSTtree); // Create a View
	HashSet<Integer> treeVal = new HashSet<>();
	Validation v = new Validation();
	
	
	@FXML
	public void backOnAction(ActionEvent event) throws Exception {
		MainBST.startMain();
	}
	@FXML
	public void insertOnAction(ActionEvent event) {
		 if (v.emptyTextField(txt1)) {
             invalidKey(txt1, "No key entered!");
         } else {
             try {
//            	 String t = txt1.getText();
//            	 Label lb = new Label();
//            	 lb.setText(t);
                 int key = Integer.parseInt(txt1.getText());
                 if (BSTtree.search(key)) { // key is in the tree already
                     view.displayBSTTree();
                     view.setStatus(key + " is already in the tree");
                 } else {
                     BSTtree.insert(key); // Insert a new key
                     view.displayBSTTree();
                     view.setStatus(key + " is inserted in the tree");
                     treeVal.add(key); // Adds value to HashSet for building AVL tree
                 }
                 pane.getChildren().addAll(view);
                 txt1.setText("");
                 txt1.requestFocus();
             } catch (NumberFormatException ex) {
                 invalidKey(txt1, "Key must be an integer!");
             }
         }
		
	}
	private void invalidKey(TextField key, String alertHeader) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Please enter an integer in the key box and try again");
        key.requestFocus();
        alert.showAndWait();
		
	}
	@FXML
	public void deleteOnAction(ActionEvent event) {
		if (v.emptyTextField(txt1)) {
            invalidKey(txt1, "No key entered!");
        } else {
            try {
                int key = Integer.parseInt(txt1.getText());
                if (!BSTtree.search(key)) { // key is not in the tree
                    view.displayBSTTree();
                    view.setStatus(key + " is not in the tree");
                } else {
                    BSTtree.delete(key); // Delete a key
                    view.displayBSTTree();
                    view.setStatus(key + " is deleted from the tree");
                    treeVal.remove(key); // Removes key from HashSet for when tree is rebalanced
//                    if (!AVLTree.isEmpty()) { // Removes key from AVL tree if it is currently displayed
//                        if (AVLTree.getSize() == 1) { // Prevents NullPointerException when removing last node
//                            AVLTree.clear();
//                        } else {
//                            AVLTree.delete(key);
//                        }
//                        view1.displayAVLTree();
//                    }
//                    AVLTree.delete(key); // Removes key from AVL tree
                }
                txt1.setText("");
            } catch (NumberFormatException ex) {
                invalidKey(txt1, "Key must be an integer!");
            }
        }
		
		
	}
	@FXML
	public void searchOnAction(ActionEvent event) {

		
	}
	
}
