/**
 * 
 */
package com.spacerunner.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.spacerunner.main.GameSpaceRunner.STATE;

/**
 * @author ILIAN Kutkurov
 *
 */
public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	private boolean[] keyDown = new boolean[4];
	
	private GameSpaceRunner game;
	
	public KeyInput(Handler handler, GameSpaceRunner game) {
		this.handler = handler;
		this.game = game;
		 
		for(boolean b : this.keyDown) {
			b = false;
		};
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
		 
		 for(int i = 0; i < handler.gameObjects.size(); ++i) {
			 SpaceRunnerObject o = handler.gameObjects.get(i);
					 
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
		 
		 if(key == KeyEvent.VK_SPACE && game.gameState == STATE.Game) GameSpaceRunner.paused = !GameSpaceRunner.paused;
		 
		 if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); ++i) {
			 SpaceRunnerObject o = handler.gameObjects.get(i);
					 
			 if(o.getId() == ID.Player) {
				 // events player 1
				 if(key == KeyEvent.VK_UP) this.keyDown[0] = false;
				 if(key == KeyEvent.VK_DOWN) this.keyDown[1] = false;
				 if(key == KeyEvent.VK_LEFT) this.keyDown[2] = false;
				 if(key == KeyEvent.VK_RIGHT) this.keyDown[3] = false;
				 
				 // vertical movement
				 if(!this.keyDown[0] && !this.keyDown[1]) o.setVelY(0);
				 // horizontal movement
				 if(!this.keyDown[2] && !this.keyDown[3]) o.setVelX(0);
			 };
			 
		 }
	}
	
}
