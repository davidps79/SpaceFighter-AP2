package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;

public class BasicEnemy {
	private int x;
	private int y;
	private int dx; 
	private int dy;
	private Image sprite;
	private Queue<BasicEnemyBullet> bullets;
	
	public BasicEnemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.sprite = new Image("file:files/sprites/enemy_1.png");
		this.dx=80;
		this.dy=80;
		this.bullets = new LinkedList<>();
		start();
	}
	
	public void start()  {
		
		Thread th = new Thread(() -> {
			while(y<1000) {
				//System.out.print("Derecha: ");
				for (int i = 0; i < 10; i++) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x+=dx;
					shoot();
					//System.out.print(x+ " ");
				}
				//System.out.print("\n");
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				y+=dy;
				shoot();
				//System.out.println("Abajo: "+ y);
				//System.out.print("Izquierda: ");
				for (int i = 0; i < 10; i++) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x-=dx;
					shoot();
					//System.out.print(x+ " ");
				}
				//System.out.print("\n");
				y+=dy;
				shoot();
				//System.out.println("Abajo: "+ y);
			}
        });
		th.setDaemon(true);
		th.start();
		
	}
	
	public int random() {
		return (int) (Math.random() * (8 + 1 - 1)) + 1;
	}
	
	public void shoot() {
		int shoot=random();
		if (shoot==3) {
			startShoot();
		}
	}
	
	public void startShoot() {
		bullets.offer(new BasicEnemyBullet((float) (getX()+sprite.getWidth()/2), getY()-10, bullets));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public Queue<BasicEnemyBullet> getBullets() {
		return bullets;
	}

	public void setBullets(Queue<BasicEnemyBullet> bullets) {
		this.bullets = bullets;
	}
	
	
	public void shootTime(int originY) {
		double distance = 760-originY;
		double speed= 18/25;
		double time=distance/speed ;
	}
		
	public void collisionEnemyL(int origenx, int origeny) {

		double x_1= origenx+ dx;
		double y_1= (origeny*-1)+(dy*-1)-43;

		double x_2= origenx+ dx+16;
		double y_2= (origeny*-1)+(dy*-1) - 43;

		double m = (y_2-y_1)/(x_2-x_1);

		double b = y_1 - (m*x_1);

		double result;

		for (int i = (int) x_2; i < x_1; i++) {
			result = (m*i+b)*-1;
		}

	}

	public void collisionEnemyR(int origenx, int origeny) {

		double x_1= origenx+ dx+77;
		double y_1= (origeny*-1)+(dy*-1)-43;

		double x_2= origenx+ dx+93;
		double y_2= (origeny*-1)+(dy*-1) - 43;

		double m = (y_2-y_1)/(x_2-x_1);

		double b = y_1 - (m*x_1);

		double result;

		for (int i = (int) x_2; i < x_1; i++) {
			result = (m*i+b)*-1;
		}

	}

	public void collisionEnemyDiagonalL(int origenx, int origeny) {

		double x_1= origenx+ dx+16;
		double y_1= (origeny*-1)+(dy*-1)-43;

		double x_2= origenx+ dx+39;
		double y_2= (origeny*-1)+(dy*-1) - 80;

		double m = (y_2-y_1)/(x_2-x_1);

		double b = y_1 - (m*x_1);

		double result;

		for (int i = (int) x_2; i < x_1; i++) {
			result = (m*i+b)*-1;
		}

}

	public void collisionEnemyDiagonalR(int origenx, int origeny) {

		double x_1= origenx+ dx+54;
		double y_1= (origeny*-1)+(dy*-1)-80;

		double x_2= origenx+ dx+77;
		double y_2= (origeny*-1)+(dy*-1) - 43;

		double m = (y_2-y_1)/(x_2-x_1);

		double b = y_1 - (m*x_1);

		double result;

		for (int i = (int) x_2; i < x_1; i++) {
			result = (m*i+b)*-1;
		}

}

	public void collisionEnemyCenter(int origenx, int origeny) {

		double x_1= origenx+ dx+39;
		double y_1= (origeny*-1)+(dy*-1)-43;

		double x_2= origenx+ dx+54;
		double y_2= (origeny*-1)+(dy*-1) - 43;

		double m = (y_2-y_1)/(x_2-x_1);

		double b = y_1 - (m*x_1);

		double result;

		for (int i = (int) x_2; i < x_1; i++) {
			result = (m*i+b)*-1;
		}

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
}
