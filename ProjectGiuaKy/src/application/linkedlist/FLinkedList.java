package application.linkedlist;

public class FLinkedList {

	

	protected Node head;

	// Them phan tu vao cuoi ds
	public void append(String data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = new Node(data);
	}
	
	public FLinkedList(String ...arr) {
		super();
		for(int i =0; i < arr.length; i++) {
			append(arr[i]);
		}
	}
	
	public boolean isEmpty() {
		if(head == null) return true;
		return false;
	}


}
