package model;

import java.util.ArrayList;

import controller.GameViewController;
import javafx.beans.property.SimpleIntegerProperty;

public class GameController {
	private Fighter fighter;
	private ArrayList<BasicEnemy> enemies;
	private ArrayList<BasicEnemyBullet> enemyBullets;
	private ArrayList<Bullet> bullets;
	private GameViewController gameViewController;
	private int life;
	private int score;
	
	public GameController() {
		this.life = 3;
		this.score = 0;
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

	public int getScore() {
		return score;
	}
	
	public void addScore() {
		this.score++;
		this.gameViewController.updateStats();
	}
	
	public void updateStats() {
		gameViewController.updateStats();
	}

	public void setGameView(GameViewController gameViewController) {
		this.gameViewController = gameViewController;
	}

	public int getLife() {
		return life;
	}

	public void lostLife() {
		this.life--;
		gameViewController.updateStats();
		
		if (life<1) {
			System.out.println("END");
		}
	}
}
