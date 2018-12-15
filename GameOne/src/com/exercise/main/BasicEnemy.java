/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author ILIAN Kutkurov
 *
 */
public class BasicEnemy extends GameOneObject {

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
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
	
}
