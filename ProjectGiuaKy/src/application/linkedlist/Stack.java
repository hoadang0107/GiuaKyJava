package application.linkedlist;


public class Stack extends FLinkedList {


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

}
