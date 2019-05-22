package application.BST;

import javafx.scene.control.TextField;

public class Validation {
	//Checks if a TextField is empty, return true if empty
	public boolean emptyTextField(TextField t) {
        return t.getText().trim().equals("");
    }
}
