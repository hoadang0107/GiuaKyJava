package application.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LinkedListController {
	@FXML private AnchorPane anchor;
	@FXML TextField textf;
	@FXML Label lbHead;
	
	private MyLinkedList list = new MyLinkedList();
	
	private double x, y;
	private int ap = 0, pre = 1;
	private Arrow arrow;

	@FXML
	public void backOnAction(ActionEvent event) throws Exception {
		
		LLDM.startMain();
	}

	@FXML
	public void appendAction(ActionEvent event) {
		lbHead.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		x = anchor.getPrefWidth();
		y = anchor.getPrefHeight();
		String s = textf.getText();
		if(s != null) {
			
			Label lb = new Label();
			lb.setText(s);
			lb.setPrefSize(65, 34);
			lb.setAlignment(Pos.CENTER);
			lb.setFont(new Font("Arial", 15));
			lb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			
			AnchorPane.setTopAnchor(lb,  y/2-17);
			AnchorPane.setLeftAnchor(lb, x/2-30+ap*120);
			if(list.isEmpty()) {
				arrow = new Arrow(265,61,y/2-17,x/2);
			}
			if(!list.isEmpty()&& ap > 0) {
				Arrow arrow = new Arrow(x/2+(ap-1)*120+35,y/2,x/2+ap*120-30,y/2);
				anchor.getChildren().add(arrow);
			}
			if(!list.isEmpty()&& pre > 1 && ap == 0) {
				Arrow arrow = new Arrow(x/2-pre*120+35,y/2,x/2-30,y/2);
				anchor.getChildren().add(arrow);
			}
			if(list.isEmpty()) {
				arrow = new Arrow(265,55,x/2,y/2-17);
				anchor.getChildren().add(arrow);
			}
			ap++;
			list.append(lb,s);
			anchor.getChildren().add(lb);
			textf.setText("");
			
		}
	}

	@FXML
	public void prependAction(ActionEvent event) {
		
		lbHead.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		
		x = anchor.getPrefWidth();
		y = anchor.getPrefHeight();
		String s = textf.getText();
		if(s != null) {
			
			Label lb = new Label();
			lb.setText(s);
			lb.setPrefSize(65, 34);
			lb.setAlignment(Pos.CENTER);
			lb.setFont(new Font("Arial", 15));
			lb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			
			AnchorPane.setTopAnchor(lb,  y/2-17);
			AnchorPane.setLeftAnchor(lb, x/2-30-pre*120);
			if(!list.isEmpty()) {
				Arrow arrow = new Arrow(x/2-pre*120+35,y/2,x/2-(pre-1)*120-30,y/2);
				anchor.getChildren().add(arrow);
			}
			if(arrow != null) {
				anchor.getChildren().remove(arrow);
			}
			arrow = new Arrow(265,55,x/2-pre*120,y/2-17);
			anchor.getChildren().add(arrow);
			pre++;
			list.prepend(lb,s);
			anchor.getChildren().add(lb);
			textf.setText("");
			
		}


	}

	@FXML
	public void deleteAction(ActionEvent event) {

		x = anchor.getPrefWidth();
		y = anchor.getPrefHeight();
		String s = textf.getText();
		

	}
	
	
}
