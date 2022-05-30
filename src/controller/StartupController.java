package controller;

import java.io.File;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class StartupController {
	private Main main;
	
    @FXML
    private MediaView startup;
	
	public void setMain(Main main) {
		this.main = main;
		
		startup.setMediaPlayer(new MediaPlayer(new Media(new File("files/ui/startup.mp4").toURI().toString())));
    	startup.getMediaPlayer().play();
    	
    	Thread timer = new Thread(()->{
    		try {
    			Thread.sleep(11300);
    			playSound("CannonBlow.wav", 0.75f);
    			Thread.sleep(1100);
    			playSound("BlowDistance.wav", 1f);
    			Thread.sleep(1300);
    			main.openMenu();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	});
    	timer.setDaemon(true);
    	timer.start();
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
