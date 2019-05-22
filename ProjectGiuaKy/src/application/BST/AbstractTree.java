package application.BST;





// AbstractTree Description: Abstract tree class
//Imports

public abstract class AbstractTree<E extends Comparable<E>>
       implements Tree<E> {

   @Override
    //Inorder traversal from the root   
   public void inorder() {
   }

   @Override
   // Return true if the tree is empty
   
   public boolean isEmpty() {
       return getSize() == 0;
   }
}