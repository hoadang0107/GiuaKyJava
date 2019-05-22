package application.BST;




  //Tree Description: Tree Interface
 

public interface Tree<E> extends Iterable<E> {
  //Return true if the element is in the tree
    
    boolean search(E e);

    
     //Insert element into the binary tree Return true if the element is inserted successfully
     
    boolean insert(E e);

   
     // Delete the specified element from the tree Return true if the element is deleted successfully
     
    boolean delete(E e);


    // Inorder traversal from the root

    void inorder();


    //Get the number of nodes in the tree

    int getSize();


    //Return true if the tree is empty

    boolean isEmpty();

}
