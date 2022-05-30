package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;


public class TopFiveController {
	
	private Main main;
	
	@FXML
	private Button returnButton;
	
	@FXML
	private TextArea area;
	
    @FXML
    void returnEvent(ActionEvent event) {
    	main.openMenu();
    }
    
    @FXML
	void initialize() {
    	area.setFont(Font.loadFont("file:files/ui/GamePlayed.otf", 18));
	}
        
    public void setMain(Main main) {
    	this.main = main; 
    
    String cadena;
    	for(Player p: main.getPlayers()){
    		cadena += p.getName()+" - "+p.getScore();
    	}
    	area.setText(cadena);
    }

}
