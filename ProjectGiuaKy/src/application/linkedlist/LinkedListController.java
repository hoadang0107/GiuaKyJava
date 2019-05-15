package application.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

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
	@FXML
	private AnchorPane anchor;
	@FXML
	TextField textf;
	Label lbHead,lbNull;

	private MyLinkedList list = new MyLinkedList();

	private double x, y;
	private int ap = 0, pre = 1;
	private Arrow arrow;
	private List<Arrow> arr = new ArrayList<Arrow>();
	private List<Label>listLabel = new ArrayList<Label>();

	@FXML
	public void backOnAction(ActionEvent event) throws Exception {

		LLDM.startMain();
	}

	@FXML
	public void appendAction(ActionEvent event) {
		
		String s = textf.getText();
		if (s.compareTo("") != 0) {

			Label lb = new Label();
			lb.setText(s);
			lb.setPrefSize(65, 34);
			lb.setAlignment(Pos.CENTER);
			lb.setFont(new Font("Arial", 15));
			lb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			list.append(lb, s);
			draw();
			textf.setText("");
		}

	}

	public void draw() {
		double h = anchor.getPrefHeight();
		double x, y;
		x = 20.0;
		y = h / 2 - 17;
		Node current = list.head;
		anchor.getChildren().removeAll(listLabel);
		anchor.getChildren().removeAll(arr);
		anchor.getChildren().removeAll(lbHead,lbNull);
		listLabel.clear();
		arr.clear();
		if (current != null) {
			lbHead = new Label();
			lbHead.setText("Head");
			lbHead.setPrefSize(65, 34);
			lbHead.setAlignment(Pos.CENTER);
			lbHead.setFont(new Font("Arial", 14));
			lbHead.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			lbHead.setText("Head");
			AnchorPane.setTopAnchor(lbHead, y);
			AnchorPane.setLeftAnchor(lbHead, x);
			anchor.getChildren().add(lbHead);
		
		while (current != null) {
			arrow = new Arrow(x + 65, y + 17, x + 125, y + 17);
			arr.add(arrow);
			x = x + 125;
			listLabel.add(current.label);
			AnchorPane.setTopAnchor(current.label, y);
			AnchorPane.setLeftAnchor(current.label, x);
			anchor.getChildren().addAll(arrow, current.label);
			current = current.next;
		}
		arrow = new Arrow(x + 65, y + 17, x + 125, y + 17);
		arr.add(arrow);
		lbNull = new Label();
		lbNull.setText("Head");
		lbNull.setAlignment(Pos.CENTER);
		lbNull.setFont(new Font("Arial", 14));
		lbNull.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		lbNull.setText("NULL");
		lbNull.setPrefSize(65, 34);
		AnchorPane.setTopAnchor(lbNull, y);
		AnchorPane.setLeftAnchor(lbNull, x+125);
		anchor.getChildren().addAll(arrow,lbNull);
		}

	}

	@FXML
	public void prependAction(ActionEvent event) {


		String s = textf.getText();
		if (s.compareTo("") != 0) {
			Label lb = new Label();
			lb.setText(s);
			lb.setPrefSize(65, 34);
			lb.setAlignment(Pos.CENTER);
			lb.setFont(new Font("Arial", 15));
			lb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			list.prepend(lb, s);
			draw();
			textf.setText("");

		}

	}

	@FXML
	public void deleteAction(ActionEvent event) {
		String s = textf.getText();
		if (s.compareTo("") != 0) {
			list.deleteWithValue(s);
			/*MyTask task = new MyTask();
			Timer timer = new Timer();
			timer.schedule(task, 5000);
			*/
			draw();
			textf.setText("");
		}

	}

}
