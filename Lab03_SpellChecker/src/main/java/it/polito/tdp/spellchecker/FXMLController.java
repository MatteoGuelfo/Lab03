package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dizionario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboLingua;
    

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnClearText;

    @FXML
    private Label lblContaErrori;

    @FXML
    private Label lblTempoImpiegato;

    @FXML
    void doClearText(ActionEvent event) {

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	if(comboLingua.getValue() == null) {
    		txtOutput.setText("Devi selezionare una lingua");
    	}else {
    		dizionario.loadDictionary(comboLingua.getValue());
    		System.out.println("lingua: "+comboLingua.getValue());
    	}
    	String[] parole=null;
    	String testo;
    	
    	if(txtInput.getText() != "") {
    		testo=txtInput.getText();
    		testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    		parole=testo.split(" ");
    	}else{
        	txtOutput.setText("Devi scrivere qualcosa");	
    	}
    	
    	List<String> lista = new LinkedList();
    	for(String s: parole)
    		lista.add(s);
    	
    	List<RichWord> errori = dizionario.spellChecker(lista);
    	
    	String ritorno="";
    	for(RichWord rw: errori)
    		ritorno+= rw.toString()+"\n";
    	
    	txtOutput.setText(ritorno);

    }
    
    public void setModel(Dictionary dizionario) {
    	this.dizionario = dizionario;
    	this.comboLingua.getItems().addAll("English", "Italiano");
    }

    @FXML
    void initialize() {
        assert comboLingua != null : "fx:id=\"comboLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblContaErrori != null : "fx:id=\"lblContaErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempoImpiegato != null : "fx:id=\"lblTempoImpiegato\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}



