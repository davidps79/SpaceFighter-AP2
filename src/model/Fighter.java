package model;

public class Fighter {
	private float x;
	private float dx;
	private float acceleration;
	private float minSpeed;
	private float maxSpeed;
	private boolean isMoving;
	
	public Fighter() {
		this.x = 400;
		this.dx = 1f;
		this.acceleration = 0.5f;
		this.minSpeed = dx;
		this.maxSpeed = 7f;
		this.isMoving = false;
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
	
	public void moveX(int direction) {	
		if (!isMoving) {
			isMoving = true;
			Thread th = new Thread(() -> {
				while(isMoving) {
					try {
						if (isMoving) {
							Thread.sleep(60);
							if (dx<maxSpeed)
								dx+=acceleration;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			th.setDaemon(true);
			th.start();
		}
		x+=dx*direction;
	}

	public void stopX() {
		isMoving = false;
		dx = minSpeed;
	}
}
