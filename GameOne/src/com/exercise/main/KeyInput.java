/**
 * 
 */
package com.exercise.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author ILIAN Kutkurov
 *
 */
public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler h) {
		handler = h;
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
		 
		 for(int i = 0; i < handler.gameObjects.size(); ++i) {
			 GameOneObject o = handler.gameObjects.get(i);
					 
			 if(o.getId() == ID.Player) {
				 // events player 1
				 if(key == KeyEvent.VK_UP) o.setVelY(-5);
				 if(key == KeyEvent.VK_DOWN) o.setVelY(5);
				 if(key == KeyEvent.VK_LEFT) o.setVelX(-5);
				 if(key == KeyEvent.VK_RIGHT) o.setVelX(5);
			 };
			 
		 }
		 
		 if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); ++i) {
			 GameOneObject o = handler.gameObjects.get(i);
					 
			 if(o.getId() == ID.Player) {
				 // events player 1
				 if(key == KeyEvent.VK_UP) o.setVelY(0);
				 if(key == KeyEvent.VK_DOWN) o.setVelY(0);
				 if(key == KeyEvent.VK_LEFT) o.setVelX(0);
				 if(key == KeyEvent.VK_RIGHT) o.setVelX(0);
			 };
			 
		 }
	}
	
}
