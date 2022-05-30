package model;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Fighter {
	private float x;
	private float y;
	private float dx;
	private float acceleration;
	private float minSpeed;
	private float maxSpeed;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean canShoot;
	private boolean isShooting;
	private ArrayList<Bullet> bullets;
	private Image sprite;
	private float xAdjust;
	private float yAdjust;
	private Thread startShooting;
	private Thrust thrust;
	private int score;
	private GameController controller;
	
	public Fighter(float x, float y, GameController controller) {
		this.dx = 1f;
		this.acceleration = 0.6f;
		this.controller= controller;
		this.minSpeed = dx;
		this.maxSpeed = 6f;
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.canShoot = true;
		this.isShooting = false;
		this.bullets = controller.getBullets();
		this.startShooting = null;
		this.sprite = new Image("file:files/sprites/fighter2.png");
		this.xAdjust = (float) sprite.getWidth()/2;
		this.yAdjust = (float) sprite.getHeight()/2;
		this.x = x - xAdjust;
		this.y = y - yAdjust;
		this.thrust = new Thrust();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score+=score;
	}
	

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}
	
	public void moveLeft() {
		if (isMovingRight) stopX();
		
		if (!isMovingLeft) {
			isMovingLeft = true;
			Thread move = new Thread(() -> {
				while(isMovingLeft) {
					try {
						Thread.sleep(25);
						if (dx<maxSpeed)
							dx+=acceleration;
						x -= dx;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			
			move.setDaemon(true);
			move.start();
		}
	}
	
	public void moveRight() {
		if (isMovingLeft) stopX();
		
		if (!isMovingRight) {
			isMovingRight = true;
			Thread move = new Thread(() -> {
				while(isMovingRight) {
					try {
						Thread.sleep(25);
						if (dx<maxSpeed)
							dx+=acceleration;
						x += dx;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			
			move.setDaemon(true);
			move.start();
		}
	}

	public void stopX() {
		isMovingRight = false;
		isMovingLeft = false;
		dx = minSpeed;
	}

	public float getY() {
		return y;
	}
	
	public void shoot() {
		if (!isShooting && canShoot) {
			isShooting = true;	
			startShooting = new Thread(() -> {
				while (isShooting) {
					if (canShoot) {
						bullets.add(new Bullet((float) (getX()+sprite.getWidth()/2), getY()-10, bullets,controller));
						canShoot = false;
					}

					try {
						Thread.sleep(400);
						canShoot = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
			startShooting.setDaemon(true);
			startShooting.start();
		}
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void stopShoot() {
		isShooting = false;
	}

	public Image getSprite() {
		return sprite;
	}
	
	public Thrust getThrust() {
		return thrust;
	}

	public void setThrust(Thrust thrust) {
		this.thrust = thrust;
	}

	public double getThrustX() {
		return x+(sprite.getWidth()/2)-(thrust.getSprite().getWidth()/2);
	}

	public double getThrustY() {
		return y+(sprite.getHeight()/2)+(thrust.getSprite().getHeight()/2);
	}
}
