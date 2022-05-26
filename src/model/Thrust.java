package model;

import javafx.scene.image.Image;

public class Thrust {
	private Image[] sprite;
	private int currentSprite;
	private boolean flag;
	private int spriteSpeed;
	
	public Thrust() {
		this.sprite = new Image[3];
		for (int i=0; i<sprite.length; i++) {
			sprite[i] = new Image("file:files/sprites/space_thrust" + (i+1) + ".png");
		}
		this.currentSprite = 0;
		this.flag = true;
		this.spriteSpeed = 70;
		
		start();
	}
	
	public void start() {
		Thread animation = new Thread(()->{
			while (flag) {
				try {
					currentSprite = 0;
					Thread.sleep(spriteSpeed);
					currentSprite = 1;
					Thread.sleep(spriteSpeed);
					currentSprite = 2;
					Thread.sleep(spriteSpeed);
					currentSprite = 1;
					Thread.sleep(spriteSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		animation.setDaemon(true);
		animation.start();
	}
	
	public Image getSprite() {
		return sprite[currentSprite];
	}
}
