package application;


import java.util.ArrayList;
import java.util.List;

import application.linkedlist.Stack;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class controller {
	@FXML private BorderPane bPane;
	@FXML private AnchorPane pane;
	@FXML private TextField txt1; 
	@FXML private Label labl;
	Stack stack = new Stack(); 
	//List<RecArr> arr = new ArrayList<RecArr>();
	List<Label> listLabel = new ArrayList<Label>();
	
	@FXML
	public void pushOnAction(ActionEvent e) {
		String t = txt1.getText();
		if (t.compareTo("") != 0) {
			Label lb = new Label();
			lb.setText(t);
			lb.setPrefSize(65, 34);
			lb.setAlignment(Pos.CENTER);
			lb.setFont(new Font("Arial", 15));
			lb.setStyle("-fx-background-color: red;");
			lb.setTextFill(Color.WHITE);
			listLabel.add(lb);
			setPoint(lb);
			stack.append(lb,t);
			txt1.setText(null);
			pane.getChildren().add(lb);
			labl.setText("");
		}
	}
	
	@FXML
	public void peakAction(ActionEvent event) {
		if(!stack.isEmpty()) {
		String s = stack.peak();
		labl.setText("Peak element: "+s);
		}

	}
	
	public void setPoint(Label lb) {
			AnchorPane.setTopAnchor(lb, 280.0- (stack.getCount()-1)*40);
			AnchorPane.setLeftAnchor(lb, 250.0);
	}
	
	
	@FXML public void popOnAction(ActionEvent e) {
		if(stack.isEmpty() == false) {
		pane.getChildren().remove(stack.getCount());
		String s = stack.pop();
		labl.setText("Pop element: "+s);
		}else {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack trống",ButtonType.OK);
			alert.showAndWait()
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}
		
	}
	
	@FXML public void backAction(ActionEvent even) throws Exception {
		StackDM.startMain();
		//Main.main(null);
	}
	
}
