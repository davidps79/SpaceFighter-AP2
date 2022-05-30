package model;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Bullet {
	private float speed;
	private float x;
	private float y;
	private boolean exists;
	private ArrayList<Bullet> parent;
	private Image sprite;
	private GameController controller;
	
	public Bullet(float x, float y, ArrayList<Bullet> parent, GameController controller) {
		this.sprite = new Image("file:files/sprites/basicBullet.png");
		this.x = (float) (x-sprite.getWidth()/2);
		this.y = y;
		this.speed = 18f;
		this.controller=controller;
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
					collisionsComplete();
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
        try {
            AudioClip clip = new AudioClip(new File("files/sounds/shoot" + random(0,2) + ".wav").toURI().toString());
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public int random(int min, int max) {
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

	public Image getSprite() {
		return sprite;
	}
	

	public void collisionsL(int origenx, int origeny, int x, int y) {

		int x_1=origenx;
		int x_2=origenx+16;
		int y_1=origeny+43;

		if(x>=x_1 && x<=x_2 && y<=y_1) {

		}

	}

	public void collisionsR(int origenx, int origeny, int x, int y) {

		int x_1=origenx+77;
		int x_2=origenx+93;
		int y_1=origeny+43;

		if(x>=x_1 && x<=x_2 && y<=y_1) {

		}

	}

	public void collisionsDL(int origenx, int origeny, int x, int y) {

		int x_1=origenx+16;
		int x_2=origenx+39;
		int y_1=origeny+80;

		if(x>=x_1 && x<=x_2 && y<=y_1) {

		}

	}

	public void collisionsDR(int origenx, int origeny, int x, int y) {

		int x_1=origenx+54;
		int x_2=origenx+77;
		int y_1=origeny+80;

		if(x>=x_1 && x<=x_2 && y<=y_1) {

		}

	}

	public void collisionsCenter(int origenx, int origeny, int x, int y) {

		int x_1=origenx+39;
		int x_2=origenx+54;
		int y_1=origeny+43;

		if(x>=x_1 && x<=x_2 && y<=y_1) {

		}

	}
	public void collisionsComplete() {

		for (BasicEnemy i: controller.getEnemies()) {
			int x_1=i.getX();
			int x_2=i.getX()+93;
			int y_1=i.getY();
			int y_2=i.getY()+75;

			if(x>=x_1 && x<=x_2 && y<=y_2 && y>=y_1) {
				parent.remove(this);
				exists= false;
				controller.getFighter().setScore(1);
				i.destroy();
				controller.addScore();
				controller.getEnemies().remove(i);
				break;
			}
			
		}	

	}
}
