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
				 if(key == KeyEvent.VK_W) o.setVelY(-3);
				 if(key == KeyEvent.VK_S) o.setVelY(3);
				 if(key == KeyEvent.VK_A) o.setVelX(-3);
				 if(key == KeyEvent.VK_D) o.setVelX(3);
			 };
			 
			 if(o.getId() == ID.PlayerTwo) {
				 // events player 2
				 if(key == KeyEvent.VK_UP) o.setVelY(-3);
				 if(key == KeyEvent.VK_DOWN) o.setVelY(3);
				 if(key == KeyEvent.VK_LEFT) o.setVelX(-3);
				 if(key == KeyEvent.VK_RIGHT) o.setVelX(3);
			 };
			 
		 }
		 
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); ++i) {
			 GameOneObject o = handler.gameObjects.get(i);
					 
			 if(o.getId() == ID.Player) {
				 // events player 1
				 if(key == KeyEvent.VK_W) o.setVelY(0);
				 if(key == KeyEvent.VK_S) o.setVelY(0);
				 if(key == KeyEvent.VK_A) o.setVelX(0);
				 if(key == KeyEvent.VK_D) o.setVelX(0);
			 };
			 
			 if(o.getId() == ID.PlayerTwo) {
				 // events player 2
				 if(key == KeyEvent.VK_UP) o.setVelY(0);
				 if(key == KeyEvent.VK_DOWN) o.setVelY(0);
				 if(key == KeyEvent.VK_LEFT) o.setVelX(0);
				 if(key == KeyEvent.VK_RIGHT) o.setVelX(0);
			 };
			 
		 }
	}
	
}
