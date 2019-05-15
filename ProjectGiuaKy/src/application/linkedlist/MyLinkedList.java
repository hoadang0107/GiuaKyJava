package application.linkedlist;

import java.util.Timer;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MyLinkedList extends FLinkedList {

	public void deleteWithValue(String data) {

		if (head == null)
			return;
		while (head != null && head.data.compareTo(data) == 0) {
			
			//head.label.setTextFill(Color.WHITE);
			//head.label.setStyle("-fx-background-color: red;");
			head = head.next;
		} 

		Node current = head;
		while (current!= null && current.next != null) {
			if (current.next.data.compareTo(data) == 0) {
				current.next = current.next.next;
				//current.next.label.setTextFill(Color.WHITE);
				//head.label.setStyle("-fx-background-color: red;");

			} else {
				current = current.next;
			}
		}
	}

	// Them phan tu vao dau ds
	public void prepend(Label lb, String data) {

		Node nHead = new Node(lb, data);
		nHead.next = head;
		head = nHead;
	}

	public MyLinkedList() {
		super();
		// TODO Auto-generated constructor stub
	}

}
