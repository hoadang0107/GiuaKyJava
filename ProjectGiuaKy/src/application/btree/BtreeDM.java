package application.btree;

import application.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BtreeDM extends Application {

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("BTreeDemo.fxml"));
			primaryStage.setTitle("BTree");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static void startMain() {
		Main main = new Main();
		main.start(primaryStage);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
