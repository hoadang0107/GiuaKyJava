package application.linkedlist;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Stack extends FLinkedList {

	private int count = 0;

	public int getCount() {

		Node current = head;
		if (current == null) {
			return 0;
		}
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;

	}

	public String pop() {

		Node current = head;
		if (current.next == null) {
			head = null;
			return current.data;
		} else {
			while (current.next.next != null) {
				current = current.next;
			}
			String data = current.next.data;
			current.next = null;
			return data;

		}

	}

	public String peak() {

		Node current = head;
		if (current.next == null) {
			return current.data;
		} else {
			while (current.next.next != null) {
				current = current.next;
			}
			return current.next.data;

		}

	}

	public Stack(String... arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}

}
