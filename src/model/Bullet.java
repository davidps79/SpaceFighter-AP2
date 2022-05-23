package model;

import java.io.File;
import java.util.Queue;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Bullet {
	private float speed;
	private float x;
	private float y;
	private boolean exists;
	private Queue<Bullet> parent;
	
	public Bullet(float x, float y, Queue<Bullet> parent) {
		this.x = x;
		this.y = y;
		this.speed = 12f;
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
						parent.poll();
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
}
