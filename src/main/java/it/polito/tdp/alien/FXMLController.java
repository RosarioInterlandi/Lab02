package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.alien.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Dictionary model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    void handleClear(ActionEvent event) {
    	txtOutput.clear();
    }

    @FXML
    void handleTranslation(ActionEvent event) {
    	String[] words = txtInput.getText().split(" ");
    	if (words.length==1) {
    		String output = this.model.searchTranslation(words[0]);
    		txtOutput.appendText(output);
    	}
    	if (words.length==2) {
    		if(!this.model.addWord(words[0], words[1])) {
    			txtOutput.appendText("Error in words format");
    		}
    		
    	}
    }
    @FXML
    void handleWIldCard(ActionEvent event) {
    	String wildCarsResult = this.model.translateWordWildCard(txtInput.getText());
    	txtOutput.appendText(wildCarsResult);
    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Dictionary model) {
		this.model = model;
	}
    

}
