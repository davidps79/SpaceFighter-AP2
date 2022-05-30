package model;

import java.io.File;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BasicEnemyBullet {
	private float speed;
	private float x;
	private float y;
	private boolean exists;
	private ArrayList<BasicEnemyBullet> parent;
	private GameController controller;
	private Image sprite;
	
	public BasicEnemyBullet(float x, float y, GameController controller) {
		this.sprite = new Image("file:files/sprites/enemy_laser.png");
		this.x = (float) (x-sprite.getWidth()/2);
		this.y = y;
		this.speed = 18f;
		this.exists = true;
		this.controller = controller;
		this.parent = controller.getEnemyBullets();
		startMovement();
	}
	
	public void startMovement() {
    	playSound();
		Thread move = new Thread(() -> {
			while(exists) {
				try {
					Thread.sleep(25);
					y+=speed;
					
					if (y<0) {
						if (exists) {
							exists = false;
							parent.remove(this);
						}
					}
					if (exists) collisionsFighter();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		
		move.setDaemon(true);
		move.start();
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	private void playSound(){
		Media sound = new Media(new File("files/sounds/laserShoot.wav").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	public int random(int min, int max) {
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

	public Image getSprite() {
		return sprite;
	}
	
	public void collisionsFighter() {
		if (exists) {
			float x_1 = controller.getFighter().getX();
			float x_2 = controller.getFighter().getX()+78;
			float y_1 = controller.getFighter().getY();

			if(x>=x_1 && x<=x_2 && y>=y_1 && y<=y_1+80) {
				parent.remove(this);
				controller.lostLife();
				exists = false;
			}
		}
	}

	public void setExists(boolean b) {
		this.exists = b;
	}
}
