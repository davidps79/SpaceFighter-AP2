package model;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Bullet {
	private float speed;
	private float x;
	private float y;
	private boolean exists;
	private ArrayList<Bullet> parent;
	private Image sprite;
	
	public Bullet(float x, float y, ArrayList<Bullet> parent) {
		this.sprite = new Image("file:files/sprites/basicBullet.png");
		this.x = (float) (x-sprite.getWidth()/2);
		this.y = y;
		this.speed = 18f;
		this.exists = true;
		this.parent = parent;
		startMovement();
	}
	
	public void startMovement() {
    	playSound();
		Thread move = new Thread(() -> {
			while(exists) {
				try {
					Thread.sleep(25);
					y-=speed;
					
					if (y<-30) {
						exists = false;
						parent.remove(this);
					}
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
		Media sound = new Media(new File("files/sounds/shoot" + random(0,2) + ".wav").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	public int random(int min, int max) {
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

	public Image getSprite() {
		return sprite;
	}
}
