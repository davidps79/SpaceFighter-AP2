package model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	public Player(String name, int score) {
		this.name=name;
		this.score=score;
	}
	
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	@Override
	public int compareTo(Player other) {
		return other.getScore()>this.getScore()?-1:1;
	}
	
}
