package controller;

import java.io.File;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuViewController {
	private Main main;
	private MediaPlayer mediaPlayer;

	@FXML
	private Button playButton;
	@FXML
	private Button settingsButton;
	@FXML
	private Button exitButton;
	
    @FXML
    void exit() {
    	exitButton.setGraphic(new ImageView(new Image("file:files/ui/exitButton3.png")));
    	clickSound();
    	Platform.exit();
    	System.exit(0);
    }

    @FXML
    void play() {
    	clickSound();
    	main.openGame();
    }

    
    @FXML
    void settings() {
    	clickSound();
    }

    @FXML
    void hoverSound() {
    	playSound("hoverButton.mp3");
    }
    
    private void clickSound() {
    	playSound("clickButton.wav");
    }
    
	public void setMain(Main main) {
		this.main = main;
		playBackgroundMusic("MenuMusic.wav");
	}
	
	private void playBackgroundMusic(String file){
		Media sound = new Media(new File("files/sounds/" + file).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.45);
		mediaPlayer.play();
	}
	
	 private void playSound(String file) {
        try {
            AudioClip clip = new AudioClip(new File("files/sounds/" + file).toURI().toString());
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

	 }
}
