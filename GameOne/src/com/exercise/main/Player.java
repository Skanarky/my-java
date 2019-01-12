/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author ILIAN Kutkurov
 *
 */
public class Player extends GameOneObject {
	
	private Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x + 4, (int)y, 24, 35);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = GameOne.clamp(x, 0, GameOne.WIDTH - 33);
		y = GameOne.clamp(y, 0, GameOne.HEIGHT - 54);
		
		this.handler.addObject(new PlayerTrail((float) (this.x + 7), (float) (this.y + 25), ID.PlayerTrail, Color.white, 7, 7, 0.18f, this.handler));
		this.handler.addObject(new PlayerTrail((float) (this.x + 21), (float) (this.y + 25), ID.PlayerTrail, Color.white, 7, 7, 0.18f, this.handler));
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < this.handler.gameObjects.size(); ++i) {
			GameOneObject tempObj = this.handler.gameObjects.get(i);
	
			// looking for Basic enemy
			if(tempObj.getId() == ID.BasicEnemy) {
			
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 0.3f;
				};
				
			};
			
			// looking for Fast enemy
			if(tempObj.getId() == ID.FastEnemy) {
			
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 0.5f;
				};
				
			};
			
			// looking for Smart enemy
			if(tempObj.getId() == ID.SmartEnemy) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 0.15f;
				};
				
			};
			
			// looking for Boss Enemy enemy
			if(tempObj.getId() == ID.BossEnemy) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 1f;
				};
				
			};
			
			// looking for Boss Enemy Bullet enemy
			if(tempObj.getId() == ID.BossEnemyBullet) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 0.6f;
				};
				
			};
			
		};
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x + 7, (int)y - 14, 20, 22);
		
		g.fillRect((int)x + 4, (int)y, 26, 28);
		
		g.fillOval((int)x + 6, (int)y + 24, 8, 8);
		g.fillOval((int)x + 20, (int)y + 24, 8, 8);
	}
	
}
