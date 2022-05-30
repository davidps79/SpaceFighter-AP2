package controller;

import java.io.File;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
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
    	clickSound();
    	main.openMenu();
    }
    
    @FXML
    void hoverSound() {
    	playSound("hoverButton.mp3", 0.3f);
    }
        
    public void setMain(Main main) {
    	area.setFont(Font.loadFont("file:files/ui/GamePlayed.otf", 20));
    	
    	this.main = main; 
    
	    String cadena = "";
	    int i=1;
    	for(Player p: main.getRegistry().getPlayers()){
    		cadena += i + ". " + p.getName()+" - "+p.getScore()+ "\n";
    		i++;
    	}
    	area.setText(cadena);
    }

    private void clickSound() {
    	playSound("clickButton.wav", 1f);
    }
    
	private void playSound(String file, float volume) {
        try {
            AudioClip clip = new AudioClip(new File("files/sounds/" + file).toURI().toString());
            clip.setVolume(volume);
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
