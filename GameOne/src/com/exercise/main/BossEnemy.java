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
public class BossEnemy extends GameOneObject {
	
	private Handler handler;

	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0f;
		velY = 5f;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= GameOne.HEIGHT - 120) velY *= -1;
		if(x <= 0 || x >= GameOne.WIDTH - 32) velX *= -1;
		
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.EnemyTrail, Color.cyan, 96, 96, 0.1f, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 96, 96);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}
	
}
