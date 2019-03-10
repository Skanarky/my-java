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
	
	private boolean[] keysDown = new boolean[4];
	
	private GameSpaceRunner game;
	
	private Shop shop;
	
	public KeyInput(Handler handler, GameSpaceRunner game, Shop sh) {
		this.handler = handler;
		this.game = game;
		this.shop = sh;
		 
		for(@SuppressWarnings("unused") boolean b : this.keysDown) {
			b = false;
		};
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
		 
		 // player controls
		 if (handler.gameObjects.size() > 0) {

			 for(int i = 0; i < handler.gameObjects.size(); ++i) {

				 SpaceRunnerObject o = handler.gameObjects.get(i);
						 
				 if(o.getId() == ID.Player && game.gameState == STATE.Game) {
					 // events player 1
					 if(key == KeyEvent.VK_UP) {
						 o.setVelY(- this.handler.getPlayerSpd());
						 this.keysDown[0] = true;
					 };
					 if(key == KeyEvent.VK_DOWN) {
						 o.setVelY(this.handler.getPlayerSpd());
						 this.keysDown[1] = true;
					 };
					 if(key == KeyEvent.VK_LEFT) {
						 o.setVelX(- this.handler.getPlayerSpd());
						 this.keysDown[2] = true;
					 };
					 if(key == KeyEvent.VK_RIGHT) {
						 o.setVelX(this.handler.getPlayerSpd());
						 this.keysDown[3] = true;
					 };
					 
					 // avoiding Null Pointer exception
					 break;

				 };
				 
			 }

		 }
		 
		 // pausing; leaving it able to pause first and then shop -> no change of game STATE
		 if (key == KeyEvent.VK_SPACE && game.gameState == STATE.Game) GameSpaceRunner.PAUSED = !GameSpaceRunner.PAUSED;
		 
		// shopping
		 if (key == KeyEvent.VK_ENTER) {
			 
			 if (game.gameState == STATE.Game) {
				 game.gameState = STATE.Shop;
			 } else if (game.gameState == STATE.Shop) {
				 game.gameState = STATE.Game;
				 this.shop.setEnoughOrNot("");
			 } 
			 
		 }
		 
		 // quit game
		 if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		// player controls
		if (handler.gameObjects.size() > 0) {

			for(int i = 0; i < handler.gameObjects.size(); ++i) {

				 SpaceRunnerObject o = handler.gameObjects.get(i);
						 
				 if(o.getId() == ID.Player && game.gameState == STATE.Game) {
					 // events player 1
					 if(key == KeyEvent.VK_UP) this.keysDown[0] = false;
					 if(key == KeyEvent.VK_DOWN) this.keysDown[1] = false;
					 if(key == KeyEvent.VK_LEFT) this.keysDown[2] = false;
					 if(key == KeyEvent.VK_RIGHT) this.keysDown[3] = false;
					 
					 // vertical movement
					 if(!this.keysDown[0] && !this.keysDown[1]) o.setVelY(0);
					 // horizontal movement
					 if(!this.keysDown[2] && !this.keysDown[3]) o.setVelX(0);
					 
					 // avoiding Null Pointer exception
					 break;
					 
				 };

			}

		}
	}
	
}
