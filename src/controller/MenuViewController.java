package controller;

import java.io.File;
import java.util.concurrent.TimeUnit;
import application.Main;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class MenuViewController {
	private Main main;

	@FXML
	private Button playButton;
	@FXML
	private Button topButton;
	@FXML
	private Button exitButton;
	@FXML
	private ImageView stars;
	
	@FXML
	void initialize() {
		playButton.setOpacity(0);
		topButton.setOpacity(0);
		exitButton.setOpacity(0);
	}
	
    @FXML
    void exit() {
    	exitButton.setGraphic(new ImageView(new Image("file:files/ui/exitButton3.png")));
    	clickSound();
    	try {
			TimeUnit.MILLISECONDS.sleep(10);
	    	Platform.exit();
	    	System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void play() {
    	clickSound();
    	main.openGame();
    }

    @FXML
    void seeTopFive() {
    	clickSound();
    	main.openTopFive();
    }

    @FXML
    void hoverSound() {
    	playSound("hoverButton.mp3", 0.3f);
    }
    
    private void clickSound() {
    	playSound("clickButton.wav", 1f);
    }
    
	public void setMain(Main main) {
		this.main = main;
		startButtons();
	}
	
	private void startButtons() {
		Thread timer = new Thread(()->{
			try {
				fadeButton(playButton);
				Thread.sleep(400);
				fadeButton(topButton);
				Thread.sleep(400);
				fadeButton(exitButton);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		timer.setDaemon(true);
		timer.start();
	}
	
	private void fadeButton(Button button) {
		FadeTransition fade = new FadeTransition();
		fade.setNode(button);
		fade.setDuration(Duration.millis(600));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
		playSound("Button4.wav", 0.8f);
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
