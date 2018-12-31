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
public class BasicEnemy extends GameOneObject {
	
	private Handler handler;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 2f;
		velY = 2f;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= GameOne.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= GameOne.WIDTH - 16) velX *= -1;
		
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.EnemyTrail, Color.red, 15, 15, 0.1f, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
}
