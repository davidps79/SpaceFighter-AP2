package model;

import java.util.concurrent.TimeUnit;

public class BasicEnemy {
	
	private int x;
	private int y;
	private int dx; 
	private int dy;
	private Image sprite;
	
	public static void main(String[] args) throws InterruptedException {
		BasicEnemy be= new BasicEnemy();
		be.start();
	}
	
	public BasicEnemy() {
		dx=80;
		dy=80;
	}
	
	public void start() throws InterruptedException {
		
		Thread th = new Thread(() -> {
			while(y<1000) {
				System.out.print("Derecha: ");
				for (int i = 0; i < 10; i++) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x+=dx;
					shoot();
					System.out.print(x+ " ");
				}
				System.out.print("\n");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				y+=dy;
				shoot();
				System.out.println("Abajo: "+ y);
				System.out.print("Izquierda: ");
				for (int i = 0; i < 10; i++) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x-=dx;
					shoot();
					System.out.print(x+ " ");
				}
				System.out.print("\n");
				y+=dy;
				shoot();
				System.out.println("Abajo: "+ y);
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
			System.out.print("Disparó");
		}
	}

}
