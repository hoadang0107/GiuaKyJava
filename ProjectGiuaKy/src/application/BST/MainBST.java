package application.BST;

import application.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainBST extends Application {

	
	static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			MainBST.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("BST_Animation"));
			primaryStage.setTitle("Binary Search Tree - AVL Tree");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void startMain() throws Exception {
		
	    Main main = new Main();
		main.start(primaryStage);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
}
