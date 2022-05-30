package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import model.Player;


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
    
        
    public void setMain(Main main) {
    	area.setFont(Font.loadFont("file:files/ui/GamePlayed.otf", 18));
    	
    	this.main = main; 
    
	    String cadena = "";
    	for(Player p: main.getRegistry().getPlayers()){
    		cadena += p.getName()+" - "+p.getScore();
    	}
    	area.setText(cadena);
    }

}
