/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author ILIAN Kutkurov
 *
 */
public class Player extends GameOneObject {
	
	private Random r = new Random();
	
	private Handler handler;
	
	public Player(int x, int y, ID id, Handler h) {
		super(x, y, id);
		handler = h;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = GameOne.clamp(x, 0, GameOne.WIDTH - 33);
		y = GameOne.clamp(y, 0, GameOne.HEIGHT - 54);
		
		collision();
	}
	
	private void collision() {
		for(GameOneObject o : handler.gameObjects) {
			
			// looking for the enemy
			if(o.getId() == ID.BasicEnemy) {
			
				if(getBounds().intersects(o.getBounds())) {
					// collision code
					HUD.HEALTH -= 1;
				};
				
			};
			
		};
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
}
