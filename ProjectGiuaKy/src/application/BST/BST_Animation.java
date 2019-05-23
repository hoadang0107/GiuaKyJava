package application.BST;


//Imports

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashSet;
//import java.util.Optional;

import application.Main;



public class BST_Animation extends Application {
	static Stage primaryStage;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception{
        BST<Integer> BSTtree = new BST<>(); // Create a BST tree
       AVL<Integer> AVLTree = new AVL<>(); // Create an AVL tree

        BorderPane pane = new BorderPane();
        BTView view = new BTView(BSTtree); // Create a View
        AVLView view1 = new AVLView(AVLTree);
        pane.setLeft(view);
        pane.setRight(view1);
        view.setPrefWidth(300);
        view1.setPrefWidth(250);
        pane.setPrefWidth(250);
        BorderPane.setMargin(view, new Insets(10, 20, 10, 20));
        BorderPane.setMargin(view1, new Insets(10, 20, 10, 20));

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btSearch = new Button("Search");
        Button btBalance = new Button("Balance");
        Button btClear = new Button("Clear");
        Button btBack = new Button("Back");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btSearch, btBalance, btClear, btBack);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));

        Validation v = new Validation();
        HashSet<Integer> treeVal = new HashSet<>();

        btInsert.setOnAction(e -> {
            if (v.emptyTextField(tfKey)) {
                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (BSTtree.search(key)) { // key is in the tree already
                        view.displayBSTTree();
                        view.setStatus(key + " is already in the tree");
                    } else {
                        BSTtree.insert(key); // Insert a new key
                        view.displayBSTTree();
                        view.setStatus(key + " is inserted in the tree");
                        treeVal.add(key); // Adds value to HashSet for building AVL tree
                    }
                    tfKey.setText("");
                    tfKey.requestFocus();
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });

        btDelete.setOnAction(e -> {
            if (v.emptyTextField(tfKey)) {
                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (!BSTtree.search(key)) { // key is not in the tree
                        view.displayBSTTree();
                        view.setStatus(key + " is not in the tree");
                    } else {
                        BSTtree.delete(key); // Delete a key
                        view.displayBSTTree();
                        view.setStatus(key + " is deleted from the tree");
                        treeVal.remove(key); // Removes key from HashSet for when tree is rebalanced
                        if (!AVLTree.isEmpty()) { // Removes key from AVL tree if it is currently displayed
                            if (AVLTree.getSize() == 1) { // Prevents NullPointerException when removing last node
                                AVLTree.clear();
                            } else {
                                AVLTree.delete(key);
                            }
                            view1.displayAVLTree();
                        }
                        AVLTree.delete(key); // Removes key from AVL tree
                    }
                    tfKey.setText("");
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });
        btSearch.setOnAction(e -> {
        	 if (v.emptyTextField(tfKey)) {
                 invalidKey(tfKey, "No key entered!");
             } else {
                 try {
                     int key = Integer.parseInt(tfKey.getText());
                     if (!BSTtree.search(key)) { // key is not in the tree
                         view.displayBSTTree();
                         view.setStatus(key + " is not in the tree");
                     } else {
                    	 
                    	 view.displayBSTTree(tfKey.getText());
                         view.setStatus(key + " is in the tree");
                         tfKey.setText("");
                     }
                        
                 } catch (NumberFormatException ex) {
                     invalidKey(tfKey, "Key must be an integer!");
                 }
             }
        	
        });
        btBalance.setOnAction(e -> {
            for (Integer i : treeVal) {
                AVLTree.insert(i); // Builds AVL tree
            }
            view1.displayAVLTree();
            if (treeVal.isEmpty()) {
                view1.setStatus("Tree is empty"); // Keeps empty message if balance is hit with no nodes to balance
            } else {
                view1.setStatus("The AVL Tree");
            }
        });

        btClear.setOnAction(e -> {
            tfKey.clear();
            BSTtree.clear();
            AVLTree.clear();
            treeVal.clear();
            view.displayBSTTree();
            view1.displayAVLTree();
            view.setStatus("BST Tree deleted");
            view1.setStatus("AVL Tree deleted");
        });

        btBack.setOnAction(e -> {
//            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
//            exit.setTitle("Goodbye!");
//            exit.setContentText("Really quit?");
//            Optional<ButtonType> result = exit.showAndWait();
//            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
//                System.exit(0);
//            }
        	try {
				BST_Animation.startMain();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        // Create a scene and place the pane in the stage
        this.primaryStage = primaryStage;
        Scene scene = new Scene(pane, 650, 350);
        primaryStage.setTitle("Binary Search Tree - AVL Tree"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    public static void startMain() throws Exception {
		
	    Main main = new Main();
		main.start(primaryStage);
		
	}
    
    private void invalidKey(TextField key, String alertHeader) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Please enter an integer in the key box and try again");
        key.requestFocus();
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
