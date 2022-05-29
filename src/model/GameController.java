package model;

import java.util.ArrayList;

public class GameController {
	private Fighter fighter;
	private ArrayList<BasicEnemy> enemies;
	private ArrayList<BasicEnemyBullet> enemyBullets;
	private ArrayList<Bullet> bullets;
	
	public GameController() {
		this.enemyBullets = new ArrayList<>();
		this.bullets= new ArrayList<>();
		this.fighter = new Fighter(400, 800, this);
		this.enemies = new ArrayList<>();
		for (int i=0; i<5; i++) {
			enemies.add(new BasicEnemy(i*150, 0, this));
		}
		
		for (int i=0; i<5; i++) {
			enemies.add(new BasicEnemy(i*150, 150, this));
		}
	}
	
	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public ArrayList<BasicEnemy> getEnemies() {
		return enemies;
	}

	public ArrayList<BasicEnemyBullet> getEnemyBullets() {
		return enemyBullets;
	}

	public ArrayList<Bullet> getBullets() {
		
		return bullets;
	}
}
