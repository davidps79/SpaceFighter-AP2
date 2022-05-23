package application;

import java.io.File;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Bullet;
import model.GameController;

public class SampleController {
	private Main main;
	private GameController back;
	private GraphicsContext gc;
	
	@FXML
	Canvas gameCanvas;
	
	public void setMain(Main main) {
		this.main = main;
		this.back = main.getBack();
		
		startGame();
		playSound("test.mp3");
	}
	
	private void startGame() {
		Thread th = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(1000/120);
					drawFighter();
					drawBullets();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th.setDaemon(true);
		th.start();
	}

	@FXML
	public void initialize() {
		gameCanvas.setFocusTraversable(true);
		gc = gameCanvas.getGraphicsContext2D();
	}
	
	public void drawFighter() {
		gc.clearRect(0, 0, 800, 800);
		//gc.drawImage(back.getFighter().getSprite(), 0, 0);
		gc.fillOval(back.getFighter().getX(), back.getFighter().getY(), 50, 50);
	}
	
	public void drawBullets() {
		for (Bullet b : back.getFighter().getBullets()) {
			gc.fillRect(b.getX(), b.getY(), 10, 30);
		}
	}
	
	@FXML
	void keyPressed(KeyEvent key) {
	    if (key.getCode() == KeyCode.A) back.getFighter().moveLeft();
	    if (key.getCode() == KeyCode.D) back.getFighter().moveRight();
	    if (key.getCode() == KeyCode.SPACE) back.getFighter().shoot();
	}
	
	@FXML
	void keyReleased(KeyEvent key) {
	    if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.D) 
	    	back.getFighter().stopX();
	    
	    if (key.getCode() == KeyCode.SPACE) {
	    	back.getFighter().stopShoot();
	    }
	}
	
	private static void playSound(String file){
		Media sound = new Media(new File("files/sounds/" + file).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
}
