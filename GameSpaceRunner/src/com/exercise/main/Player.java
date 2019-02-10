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
public class Player extends SpaceRunnerObject {
	
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
		
		x = GameSpaceRunner.clamp(x, 0, GameSpaceRunner.WIDTH - 33);
		y = GameSpaceRunner.clamp(y, 0, GameSpaceRunner.HEIGHT - 54);
		
		this.handler.addObject(new PlayerTrail((float) (this.x + 7), (float) (this.y + 25), ID.PlayerTrail, Color.white, 7, 7, 0.18f, this.handler));
		this.handler.addObject(new PlayerTrail((float) (this.x + 21), (float) (this.y + 25), ID.PlayerTrail, Color.white, 7, 7, 0.18f, this.handler));
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < this.handler.gameObjects.size(); ++i) {
			SpaceRunnerObject tempObj = this.handler.gameObjects.get(i);
	
			// looking for Basic enemy
			if(tempObj.getId() == ID.BasicEnemy) {
			
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					if (GameSpaceRunner.difficulty == 2) {
						HUD.HEALTH -= 0.8f;
					} else {
						HUD.HEALTH -= 0.4f;
					}
				};
				
			};
			
			// looking for Fast enemy
			if(tempObj.getId() == ID.FastEnemy) {
			
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					if (GameSpaceRunner.difficulty == 2) {
						HUD.HEALTH -= 1.2f;
					} else {
						HUD.HEALTH -= 0.6f;
					}
				};
				
			};
			
			// looking for Smart enemy
			if(tempObj.getId() == ID.SmartEnemy) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					if (GameSpaceRunner.difficulty == 2) {
						HUD.HEALTH -= 0.4f;
					} else {
						HUD.HEALTH -= 0.2f;
					}
				};
				
			};
			
			// looking for Boss Enemy enemy
			if(tempObj.getId() == ID.BossEnemy) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					if (GameSpaceRunner.difficulty == 2) {
						HUD.HEALTH -= 3f;
					} else {
						HUD.HEALTH -= 1.5f;
					}
				};
				
			};
			
			// looking for Boss Enemy Bullet enemy
			if(tempObj.getId() == ID.BossEnemyBullet) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					// collision code
					if (GameSpaceRunner.difficulty == 2) {
						HUD.HEALTH -= 1.4f;
					} else {
						HUD.HEALTH -= 0.7f;
					}
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
