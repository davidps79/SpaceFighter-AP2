package controller;

import java.io.File;
import java.util.Iterator;
import java.util.Optional;
import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.BasicEnemy;
import model.BasicEnemyBullet;
import model.Bullet;
import model.GameController;

public class GameViewController {
	private Main main;
	private GameController back;
	private GraphicsContext gc;
	
	@FXML
	Canvas gameCanvas;
	
	@FXML
	ImageView lifeBar;
	
	@FXML
	Label scoreLabel;
	
	@FXML
	Label levelLabel;
	
    @FXML
    private MediaView spaceStars;
    
    private boolean gameOn = true;
    
	public void setMain(Main main) {
		this.main = main;
		this.back = main.getBack();
		scoreLabel.setText("0 puntos");
		levelLabel.setText("Nivel 1");
		startGame();
		spaceStars.setMediaPlayer(new MediaPlayer(new Media(new File("files/sprites/spaceStars.mp4").toURI().toString())));
		spaceStars.getMediaPlayer().setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	        	spaceStars.getMediaPlayer().seek(Duration.ZERO);
	        	spaceStars.getMediaPlayer().play();
	        }
	    }); 
		spaceStars.getMediaPlayer().play();
	}
	
	private void startGame() {
		Thread th = new Thread(() -> {
			while(gameOn) {
				try {
					Thread.sleep(10);
					draw();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th.setDaemon(true);
		th.start();
	}
	
	private void draw() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				gc.clearRect(0, 0, 1200, 900);
				drawBullets();
				drawFighter();
				drawEnemy();	
			}
		});
	}
	
	@FXML
	public void initialize() {
		scoreLabel.setFont(Font.loadFont("file:files/ui/GamePlayed.otf", 18));
		levelLabel.setFont(Font.loadFont("file:files/ui/GamePlayed.otf", 18));
		gameCanvas.setFocusTraversable(true);
		gc = gameCanvas.getGraphicsContext2D();
	}
	
	public void drawFighter() {
		gc.drawImage(back.getFighter().getThrust().getSprite(), back.getFighter().getThrustX(), back.getFighter().getThrustY());
		gc.drawImage(back.getFighter().getSprite(), back.getFighter().getX(), back.getFighter().getY());
	}
	
	public void drawBullets() {
		Iterator<Bullet> iter = back.getBullets().iterator();  
		while(iter.hasNext() && gameOn){
			Bullet b = iter.next();
			gc.drawImage(b.getSprite(), b.getX(), b.getY());
		}
		
		Iterator<BasicEnemyBullet> iter2 = back.getEnemyBullets().iterator();  
		while(iter2.hasNext() && gameOn){
			BasicEnemyBullet b = iter2.next();
			gc.drawImage(b.getSprite(), b.getX(), b.getY());
		}
	}
	
	private void drawEnemy() {
		Iterator<BasicEnemy> iter = back.getEnemies().iterator();  
		while(iter.hasNext() && gameOn){
			BasicEnemy e = iter.next();
			gc.drawImage(e.getSprite(),e.getX(), e.getY());
		}
	}
	
	public void stop() {
		gameOn = false;
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

	public void updateStats() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				scoreLabel.setText(back.getScore() + " puntos");
				lifeBar.setImage(new Image("file:files/ui/life" + back.getLife() + ".png"));
				levelLabel.setText("Nivel " + back.getLevel());
			}
		});

	}

	public void addTop() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Est?s en el top 10");
				dialog.setHeaderText(null);
				dialog.setContentText("Ingrese el nombre del jugador:");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					main.getRegistry().add(result.get(), back.getScore());
				}	
			}
		});
	}
}
