package model;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class BasicEnemy {
	private int x;
	private int y;
	private int dx; 
	private int dy;
	private ArrayList<BasicEnemyBullet> bullets;
	private GameController controller;
	private Image sprite;
	public void setExists(boolean exists) {
		this.exists = exists;
	}

	private boolean exists;
	private int prob;
	private int speed;
	
	public BasicEnemy(int x, int y, GameController controller, int prob, int speed) {
		this.x = x;
		this.y = y;
		this.sprite = new Image("file:files/sprites/enemy_1.png");
		this.dx=80;
		this.dy=80;
		this.exists=true;
		this.controller = controller;
		this.bullets = controller.getEnemyBullets();
		this.prob=prob;
		this.speed=speed;
		start();
	}
	
	public void start()  {
		
		Thread th = new Thread(() -> {
			while(exists) {
				//System.out.print("Derecha: ");
				int movement= (1200-x)/80;
				for (int i = 0; i < movement-1 && exists; i++) {
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x+=dx;
					shoot();
					collisionsEnemy();
					//System.out.print(x+ " ");
				}
				//System.out.print("\n");
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				y+=dy;
				shoot();
				collisionsEnemy();
				//System.out.println("Abajo: "+ y);
				//System.out.print("Izquierda: ");
				int movement2= (int) ((1200-sprite.getWidth())/80);
				for (int i = 0; i < movement2 && exists; i++) {
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x-=dx;
					shoot();
					collisionsEnemy();
					//System.out.print(x+ " ");
				}
				//System.out.print("\n");
				y+=dy;
				shoot();
				collisionsEnemy();
				//System.out.println("Abajo: "+ y);
			}
        });
		th.setDaemon(true);
		th.start();
		
	}
	
	public int random() {
		return (int) (Math.random() * (prob)) + 1;
	}
	
	public int random1() {
		return (int) (Math.random() * (3000)) + 1;
	}
	
	public void shoot() {
		if(exists) {
			int shoot=random();
			if (shoot>=1 && shoot<=2) {
				Thread timer = new Thread(() -> {
					try {
						Thread.sleep(random1());
						startShoot();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				});
				timer.setDaemon(true);
				timer.start();
			}
		}
		
	}
	
	public void startShoot() {
		if (exists)
			bullets.add(new BasicEnemyBullet((float) (getX()+sprite.getWidth()/2), getY()-10, controller));
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
	
	public void destroy() {
		this.exists=false;
	}
	
	public void collisionsEnemy() {
		if (y+sprite.getHeight() > 750) {
			Fighter ft= controller.getFighter();
			float x_1=ft.getX();
			float x_2=ft.getX()+77;
			float y_1=ft.getY();
			float y_2=ft.getY()+80;

			if((x+93)>=x_1 && (x+93)<=x_2 && y+80<=y_2 && y+80>=y_1) {
				controller.endGame();
			} else if((x)>=x_1 && (x)<=x_2 && y+80<=y_2 && y+80>=y_1) {
				controller.endGame();
			}
		}	
	}	
}
