package model;

import java.io.File;
import java.util.ArrayList;
import controller.GameViewController;
import javafx.scene.media.AudioClip;

public class GameController {
	private Fighter fighter;
	private ArrayList<BasicEnemy> enemies;
	private ArrayList<BasicEnemyBullet> enemyBullets;
	private ArrayList<Bullet> bullets;
	private GameViewController gameViewController;
	private int life;
	private int enemyAmount;
	private int enemyCounter;
	private int score;
	private int shootLevel;
	private int movementLevel;
	
	public GameController() {
		this.shootLevel = 4;
		this.movementLevel = 3000;
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
		this.score++;
		this.gameViewController.updateStats();
		
		if (enemyCounter<1) nextLevel();
	}
	
	private void nextLevel() {
		playSound("respawn.wav", 1);
		enemyAmount += 4;
		enemyCounter = enemyAmount;
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

	public void lostLife() {
		this.life--;
		gameViewController.updateStats();
		
		if (life<1) {
			System.out.println("END");
		}
	}
}
