package model;

import java.io.File;
import java.util.ArrayList;
import application.Main;
import controller.GameViewController;
import javafx.scene.media.AudioClip;

public class GameController {
	private Main main;
	private Fighter fighter;
	private ArrayList<BasicEnemy> enemies;
	private ArrayList<BasicEnemyBullet> enemyBullets;
	private ArrayList<Bullet> bullets;
	private GameViewController gameViewController;
	private int life;
	private int enemyAmount;
	private int enemyCounter;
	private int score;
	private int scoreLevel;
	private int shootLevel;
	private int movementLevel;
	private int level;
	
	public GameController(Main main) {
		this.level =1;
		this.main = main;
		this.scoreLevel = 1;
		this.shootLevel = 18;
		this.movementLevel = 4000;
		this.enemyAmount = 12;
		this.enemyCounter = enemyAmount;
		this.life = 3;
		this.score = 0;
		this.enemyBullets = new ArrayList<>();
		this.bullets= new ArrayList<>();
		this.fighter = new Fighter(400, 800, this);
		this.enemies = new ArrayList<>();
		spawnEnemies();
	}
	
	private void spawnEnemies() {
		int rows = enemyAmount/8;
		int extra = enemyAmount%8;
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<8; j++) {
				enemies.add(new BasicEnemy(150*j+20, 50+i*150, this, shootLevel, movementLevel));
			}
		}
		
		for (int i=0; i<extra; i++) {
			enemies.add(new BasicEnemy(150*i+20, 50+rows*150, this, shootLevel, movementLevel));
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
		this.enemyCounter--;
		this.score+=scoreLevel;
		this.gameViewController.updateStats();
		
		if (enemyCounter<1) nextLevel();
	}
	
	private void nextLevel() {
		gameViewController.updateStats();
		playSound("respawn.wav", 1);
		level++;
		movementLevel -= 100;
		shootLevel -= 1;
		
		if (level%3==0) {
			enemyAmount += 2;
		}

		enemyCounter = enemyAmount;
		scoreLevel += 1;
		spawnEnemies();
	}
	
	private void playSound(String file, float volume) {
        try {
            AudioClip clip = new AudioClip(new File("files/sounds/" + file).toURI().toString());
            clip.setVolume(volume);
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
	public void setLife(int life) {
		this.life=life;
	}

	public void lostLife() {
		this.life--;
		gameViewController.updateStats();
		
		if (life<1) {
			endGame();
		}
	}
	
	public void endGame() {
		gameViewController.stop();
		life = 0;
		
		fighter.stop();
		for (BasicEnemy e : enemies) {
			e.setExists(false);
		}
		
		for (Bullet b : bullets) {
			b.setExists(false);
		}
		
		for (BasicEnemyBullet b : enemyBullets) {
			b.setExists(false);
		}
		
		if (main.getRegistry().check(score)) gameViewController.addTop();
		
		main.endGame();
	}

	public String getLevel() {
		return level + "";
	}
}
