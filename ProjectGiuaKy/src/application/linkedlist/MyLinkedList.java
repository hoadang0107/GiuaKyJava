package application.linkedlist;

import javafx.scene.control.Label;

public class MyLinkedList extends FLinkedList{
	
	public void deleteWithValue(String data) {
		if (head == null)
			return;
		if (head.data.compareTo(data) == 0) {
			head = head.next;
			return;
		}
		Node current = head;
		while (current.next != null) {
			if (current.next.data.compareTo(data) == 0) {
				current.next = current.next.next;
				return;
			}
			current = current.next;
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
