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
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		 
		for(boolean b : this.keyDown) {
			b = false;
		};
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
		 
		 for(int i = 0; i < handler.gameObjects.size(); ++i) {
			 GameOneObject o = handler.gameObjects.get(i);
					 
			 if(o.getId() == ID.Player) {
				 // events player 1
				 if(key == KeyEvent.VK_UP) {
					 o.setVelY(-5);
					 this.keyDown[0] = true;
				 };
				 if(key == KeyEvent.VK_DOWN) {
					 o.setVelY(5);
					 this.keyDown[1] = true;
				 };
				 if(key == KeyEvent.VK_LEFT) {
					 o.setVelX(-5);
					 this.keyDown[2] = true;
				 };
				 if(key == KeyEvent.VK_RIGHT) {
					 o.setVelX(5);
					 this.keyDown[3] = true;
				 };
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
				 if(key == KeyEvent.VK_UP) this.keyDown[0] = false; //o.setVelY(0);
				 if(key == KeyEvent.VK_DOWN) this.keyDown[1] = false; //o.setVelY(0);
				 if(key == KeyEvent.VK_LEFT) this.keyDown[2] = false; //o.setVelX(0);
				 if(key == KeyEvent.VK_RIGHT) this.keyDown[3] = false; //o.setVelX(0);
				 
				 // vertical movement
				 if(!this.keyDown[0] && !this.keyDown[1]) o.setVelY(0);
				 // horizontal movement
				 if(!this.keyDown[2] && !this.keyDown[3]) o.setVelX(0);
			 };
			 
		 }
	}
	
}
