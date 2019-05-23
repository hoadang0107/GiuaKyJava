package application.linkedlist;

import javafx.scene.control.Label;

public class FLinkedList {
	protected Node head;

	public int getCount() {
		int count = 0;
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
	

	

	// Them phan tu vao cuoi ds
	public void append(Label label,String data) {
		if (head == null) {
			head = new Node(label,data);
			return;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = new Node(label,data);
	}
	
	
	public boolean isEmpty() {
		if(head == null) return true;
		return false;
	}


}
