package model;

import java.util.LinkedList;
import java.util.Queue;
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
	private Queue<Bullet> bullets;
	private Image sprite;
	private float xAdjust;
	private float yAdjust;
	private Thread startShooting;
	private Thrust thrust;
	
	public Fighter(float x, float y) {
		this.dx = 1f;
		this.acceleration = 0.6f;
		this.minSpeed = dx;
		this.maxSpeed = 6f;
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.canShoot = true;
		this.isShooting = false;
		this.bullets = new LinkedList<>();
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
						bullets.add(new Bullet((float) (getX()+sprite.getWidth()/2), getY()-10, bullets));
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

	public Queue<Bullet> getBullets() {
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
	
	public void collisionR(int origenx, int origeny) {
		
		double x_1= origenx+ dx + (77/2);
		double y_1= (origeny*-1)+(dy*-1);
		
		double x_2= origenx+ dx + 77;
		double y_2= (origeny*-1)+(dy*-1) - 67;
		
		double m = (y_2-y_1)/(x_2-x_1);
		
		double b = y_1 - (m*x_1);
		
		double result;
		
		for (int i = (int) x_1; i < x_2; i++) {
			
			result = (m*i+b)*-1;
			
		}
		
	}
	
	public void collisionL(int origenx, int origeny) {

		double x_1= origenx+ dx + (77/2);
		double y_1= (origeny*-1)+(dy*-1);

		double x_2= origenx+ dx;
		double y_2= (origeny*-1)+(dy*-1) - 67;

		double m = (y_2-y_1)/(x_2-x_1);

		double b = y_1 - (m*x_1);

		double result;

		for (int i = (int) x_2; i < x_1; i++) {
			result = (m*i+b)*-1;
		}
		
	}
}
