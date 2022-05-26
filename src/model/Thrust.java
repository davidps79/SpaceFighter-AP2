package model;

import javafx.scene.image.Image;

public class Thrust {
	private Image[] thrustSprite;
	private int currentThrustSprite;
	
	public Thrust() {
		this.thrustSprite = new Image[3];
		for (int i=0; i<thrustSprite.length; i++) {
			thrustSprite[i] = new Image("file:files/sprites/space_thrust" + (i+1) + ".png");
		}
		this.currentThrustSprite = 0;
	}
}
