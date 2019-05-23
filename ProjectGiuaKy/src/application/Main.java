package application;

import application.BST.BST_Animation;
import application.linkedlist.LLDM;
import application.linkedlist.LinkedListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage= primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainActivity.fxml"));
			primaryStage.setTitle("Main Menu");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void startStack() throws Exception {
		
		StackDM stack = new StackDM();
		stack.start(primaryStage);
		
	}
	public static void startLinkedList() throws Exception {
		
		LLDM ll = new LLDM();
		ll.start(primaryStage);
		
	}
	
	public static void startBTree() throws Exception {
		BST_Animation tree = new BST_Animation();
		tree.start(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
