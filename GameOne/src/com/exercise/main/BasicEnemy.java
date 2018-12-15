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

	public BasicEnemy(int x, int y, ID id, Handler h) {
		super(x, y, id);
		
		handler = h;
		
		velX = 5;
		velY = 5;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= GameOne.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= GameOne.WIDTH - 32) velX *= -1;

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	
}
