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
	 
//	LinkedList<GameOneObject> gameObjects = new LinkedList<GameOneObject>();
	
//	Maybe use the ArrayList instead - better with index access than LinkedList
	ArrayList<GameOneObject> gameObjects = new ArrayList<GameOneObject>();
	
	// Copy of main array of enemy objects - for Boss Mode, when clearing all enemies
	//and later bring them back
	ArrayList<GameOneObject> gameObjectsEnemies = new ArrayList<GameOneObject>();
	
	// boolean for adding or not objects in gameObjectsEnemies
	private boolean addNewEnemy = true;
	
	public void addObject(GameOneObject o) {
		
		if(this.addNewEnemy) {
			
			this.gameObjects.add(o);
			
			// not adding the smart enemy since it doesn't work after destroying and then resetting the Player
			// I'll be just adding more than one Smart enemy after level 12
			if(o.getId() == ID.BasicEnemy || o.getId() == ID.EnemyTrail || o.getId() == ID.FastEnemy) {	
				this.gameObjectsEnemies.add(o);
			};
			
		} else {
			
			this.gameObjects.add(o);
			
		};
		
	}
	
	public void removeObject(GameOneObject o) {
		this.gameObjects.remove(o);
	}
	
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
		
		this.addNewEnemy = false;
		
		// remove all but Player
		for(int i = 0; i < gameObjects.size(); ++i) {
			
			GameOneObject obj = gameObjects.get(i);
			
			if(obj.getId() == ID.Player) {
				
				this.gameObjects.clear();
				this.addObject(new Player((float) obj.getX(), (float) obj.getY(), ID.Player, this));

			};
			
			// this below doesn't work
//			if(obj.getId() != ID.Player && obj.getId() != ID.PlayerTrail) {
//				
//				this.removeObject(obj);
//
//			};
			
		};
		
	}
	
	public void addAllEnemies() {
		
		// add all enemies back
		for(int i = 0; i < this.gameObjectsEnemies.size(); ++i) {
		
			GameOneObject obj = this.gameObjectsEnemies.get(i);
			
			this.addObject(obj);
			
		};
		
		this.addNewEnemy = true;
		
	}
	
}
