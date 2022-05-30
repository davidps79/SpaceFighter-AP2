package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Registry {
	
	private ArrayList<Player> players;
	
	@SuppressWarnings("unchecked")
	public Registry(String name, int score) {
		add(name, score);
		File file = new File(".\\files\\dataScore.obj");
		if(!file.exists()) {
			players = new ArrayList<Player>();
			saveScore();
		 }else {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis); 
				this.players=(ArrayList<Player>) ois.readObject();
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		 }
		
	}
	
	public void add(String name,int score) {
		Player player= new Player(name, score);
		if (players.size()==5) {
			if (player.getScore()>players.get(0).getScore()) {
				players.remove(0);
				players.add(player);
			}
		}else {
			players.add(player);
		}
		Collections.sort(players);
		saveScore();
		
	}
	
	public void saveScore() {
		 try {
			 	File file = new File(".\\files\\dataScore.obj");
	        	FileOutputStream fos = new FileOutputStream(file);
		        ObjectOutputStream oos = new ObjectOutputStream(fos);
		        oos.writeObject(players);
		        oos.close();
		        fos.close();
			    
		    }
		    catch (IOException ioe) {
		    	ioe.printStackTrace();
		    }
		
	}

}
