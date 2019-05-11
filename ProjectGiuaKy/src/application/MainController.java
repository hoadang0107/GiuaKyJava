package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable{
	
	private Main main;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void showStack() throws Exception {
		main.startStack();
		
	}
	@FXML
	public void showLinkedList() throws Exception {
		Main.startLinkedList();
		
	}
	@FXML
	public void showBTree() throws Exception{
		
		Main.startBTree();
		
	}
}
