package application;


import java.util.ArrayList;
import java.util.List;

import application.linkedlist.Stack;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class controller {
	@FXML private BorderPane bPane;
	@FXML private Pane pane;
	@FXML private TextField txt1; 
	Stack stack = new Stack(); 
	List<RecArr> arr = new ArrayList<RecArr>();
	
	@FXML
	public void pushOnAction(ActionEvent e) {
		String t = txt1.getText();
		if (t != null) {
			stack.append(t);

			RecArr r = new RecArr(new Text(String.valueOf(t)));
			r.setWidth(80);
			r.setHeight(30);
			r.setFill(Color.MEDIUMSLATEBLUE);
			r.text.setFill(Color.WHITE);
			arr.add(r);
			setPoint();
			txt1.setText(null);
			pane.getChildren().addAll(r, r.text);
		}
	}
	
	public void setPoint() {
		for(RecArr r: arr) {
			r.setX(250);
			r.setY(300- arr.indexOf(r)*40);
			r.text.setX(285);
			r.text.setY(320- arr.indexOf(r)*40);
		}
	}
	
	
	@FXML public void popOnAction(ActionEvent e) {
		if(stack.isEmpty() == false) {
		stack.pop();
		pane.getChildren().removeAll(arr.get(arr.size()-1),arr.get(arr.size()-1).text);
		arr.remove(arr.size()-1);
		}else {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack trống",ButtonType.OK);
			alert.showAndWait()
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}
		
	}
	
	@FXML public void backAction(ActionEvent even) throws Exception {
		StackDM.startMain();
		
	}
	
}
