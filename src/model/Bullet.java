package model;

import java.util.Queue;

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
}
