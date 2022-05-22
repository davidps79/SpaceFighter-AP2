package model;

public class GameController {
	private Fighter fighter;
	
	public GameController() {
		this.fighter = new Fighter();
	}
	
	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}
}
