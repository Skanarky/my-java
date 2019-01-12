/**
 * 
 */
package com.exercise.main;

import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.LinkedList;

/**
 * @author ILIAN Kutkurov
 * This class handles all of the objects in the game (player, enemy, health, rewards, levels...)
 */
public class Handler {
	
//	Maybe use the ArrayList instead - better with index access than LinkedList 
//	LinkedList<GameOneObject> gameObjects = new LinkedList<GameOneObject>();
	ArrayList<GameOneObject> gameObjects = new ArrayList<GameOneObject>();
	
	public void addObject(GameOneObject o) {
		this.gameObjects.add(o);
	}
	
	public void removeObject(GameOneObject o) {
		this.gameObjects.remove(o);
	}
	
//	Maybe use forEach loops instead, in the render and tick methods
	public void tick() {
		// Need to use this type of loop, because there are concurrent modifications (EnemyTrail),
		// the forEach loop throws an error
		for(int i = 0; i < gameObjects.size(); ++i) {
			GameOneObject obj = gameObjects.get(i);
			obj.tick();
		};
//		for(GameOneObject o : this.gameObjects) {
//			o.tick();
//		};
	}

	public void render(Graphics g) {
		for(int i = 0; i < gameObjects.size(); ++i) {
			GameOneObject obj = gameObjects.get(i);
			obj.render(g);
		};
//		for(GameOneObject o : this.gameObjects) {
//			o.render(g);
//		};
	}
	
	public void clearAllEnemies() {
		
		// remove all but Player
		for(int i = 0; i < gameObjects.size(); ++i) {
			
			GameOneObject obj = gameObjects.get(i);
			
			if(obj.getId() == ID.Player) {
				
				this.gameObjects.clear();
				this.addObject(new Player((float) obj.getX(), (float) obj.getY(), ID.Player, this));

			};
		};
		
	}
	
	public void addAllEnemies(ArrayList<GameOneObject> allEnemies) {
		
		// clear Boss enemy first
		for(int i = 0; i < gameObjects.size(); ++i) {
			
			GameOneObject obj = gameObjects.get(i);
			
			if(obj.getId() == ID.BossEnemy) {
				this.removeObject(obj);
			};
		};
		
		// add all enemies back
		if(gameObjects.size() == 1) {
			
			for(int i = 0; i < allEnemies.size(); ++i) {
			
				GameOneObject obj = allEnemies.get(i);
				
				this.addObject(obj);
				
			};
			
		};
		
	}
	
}
