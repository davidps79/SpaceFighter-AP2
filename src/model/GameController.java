package model;

import java.util.ArrayList;

public class GameController {
	private Fighter fighter;
	private ArrayList<BasicEnemy> enemies;
	
	public GameController() {
		this.fighter = new Fighter(400, 800);
		this.enemies = new ArrayList<>();
		for (int i=0; i<5; i++) {
			enemies.add(new BasicEnemy(i*150, 0));
		}
		
		for (int i=0; i<5; i++) {
			enemies.add(new BasicEnemy(i*150, 150));
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
}
