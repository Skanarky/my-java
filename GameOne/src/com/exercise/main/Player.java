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
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = GameOne.clamp(x, 0, GameOne.WIDTH - 33);
		y = GameOne.clamp(y, 0, GameOne.HEIGHT - 54);
		
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.EnemyTrail, Color.white, 31, 31, 0.18f, this.handler));
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < this.handler.gameObjects.size(); ++i) {
			GameOneObject tempObj = this.handler.gameObjects.get(i);
	
			// looking for Basic enemy
			if(tempObj.getId() == ID.BasicEnemy) {
			
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 0.4f;
				};
				
			};
			
			// looking for Fast enemy
			if(tempObj.getId() == ID.FastEnemy) {
			
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 0.7f;
				};
				
			};
			
			// looking for Smart enemy
			if(tempObj.getId() == ID.SmartEnemy) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 1f;
				};
				
			};
			
		};
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
	
}
