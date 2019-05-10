package application;
	
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
			primaryStage.setTitle("My Application");
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
