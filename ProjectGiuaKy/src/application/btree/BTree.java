package application.btree;

import java.util.Comparator;


public class BTree<T> {

	private NodeT root;

	private Comparator<T> comparator;

	public BTree(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public void insert(T value) {
		insert(value, root, null, false);
	}

	private void insert(T value, NodeT NodeT, NodeT parent, boolean right) {
		if (NodeT == null) {
			if (parent == null) {
				root = NodeT = new NodeT(value, parent);
			} else if (right) {
				parent.right = NodeT = new NodeT(value, parent);
			} else {
				parent.left = NodeT = new NodeT(value, parent);
			}
			return;
		}

		int compare = comparator.compare(value, NodeT.value);
		if (compare == 0) {
			NodeT.value = value;
		} else if (compare > 0) {
			insert(value, NodeT.right, NodeT, true);
		} else {
			insert(value, NodeT.left, NodeT, false);
		}
	}

	public boolean contains(T value) {
		return contains(value, root);
	}

	private boolean contains(T value, NodeT NodeT) {
		if(NodeT == null) return false;
		int compare = comparator.compare(value, NodeT.value);
		if(compare == 0) return true;
		if(compare > 0) return contains(value, NodeT.right);
		return contains(value, NodeT.left);
		
	}

	public void delete(T value) {
		delete(value, root);
	}

	private void delete(T value, NodeT NodeT) {
		if (NodeT == null)
			return;
		int compare = comparator.compare(value, NodeT.value);
		if (compare == 0) {
			deleteNodeT(NodeT);
		} else if (compare > 0) {
			delete(value, NodeT.right);
		} else {
			delete(value, NodeT.left);
		}
	}

	private void deleteNodeT(NodeT NodeT) {
		NodeT eNodeT, tempNodeT;
		
		if(NodeT.left == null && NodeT.right == null) {
			if(NodeT.parent == null ) {
				root = null;
			}else if(NodeT.parent.right == NodeT) {
				NodeT.parent.right= null;
			}else if(NodeT.parent.left == NodeT) {
				NodeT.parent.left = null;
			}
			return;
		}
		 if (NodeT.left != null) {
		      tempNodeT = NodeT.left;
		      for (eNodeT = NodeT.left; eNodeT != null; eNodeT = eNodeT.right) {
		        tempNodeT = eNodeT;
		      }
		      NodeT.value = tempNodeT.value;

		      if (NodeT.left.right != null) {
		        tempNodeT.parent.right = tempNodeT.left;
		      } else {
		        tempNodeT.parent.left = tempNodeT.left;
		      }

		      if (tempNodeT.left != null) tempNodeT.left.parent = tempNodeT.parent;

		      return;
		    }

		    if (NodeT.right == null) return;

		    tempNodeT = NodeT.right;

		    NodeT.value = tempNodeT.value;

		    NodeT.right = tempNodeT.right;
		    if (NodeT.right != null) NodeT.right.parent = NodeT;

		    NodeT.left = tempNodeT.left;
		    if (NodeT.left != null) NodeT.left.parent = NodeT;
		  }

	class NodeT {

		T value;

		NodeT parent;

		NodeT left;

		NodeT right;

		NodeT(T value, NodeT parent) {
			this.value = value;
			this.parent = parent;
		}

	}

}
