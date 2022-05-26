package application;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.BasicEnemy;
import model.BasicEnemyBullet;
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
					Thread.sleep(1000/100);
					gc.clearRect(0, 0, 800, 800);
					drawBullets();
					drawFighter();
					drawEnemy();
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
		gc.drawImage(back.getFighter().getThrust().getSprite(), back.getFighter().getX()+29.5, back.getFighter().getY()+78);
		gc.drawImage(back.getFighter().getSprite(), back.getFighter().getX(), back.getFighter().getY());
	}
	
	public void drawBullets() {
		for (Bullet b : back.getFighter().getBullets()) {
			gc.drawImage(b.getSprite(), b.getX(), b.getY());
		}
		
		for (BasicEnemy e : back.getEnemies()) {
			for (BasicEnemyBullet b : e.getBullets()) {
				gc.drawImage(b.getSprite(), b.getX(), b.getY());
			}
		}
	}
	
	private void drawEnemy() {
		for (BasicEnemy e : back.getEnemies()) {
			gc.drawImage(e.getSprite(),e.getX(), e.getY());
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
		mediaPlayer.setVolume(0.45);
		mediaPlayer.play();
	}
}
