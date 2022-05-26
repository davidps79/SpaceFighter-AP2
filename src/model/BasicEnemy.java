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
						TimeUnit.SECONDS.sleep(1);
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
					TimeUnit.SECONDS.sleep(1);
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
						TimeUnit.SECONDS.sleep(1);
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
}
