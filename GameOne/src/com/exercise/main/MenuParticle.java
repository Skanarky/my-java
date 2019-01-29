/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author Ilian Kutkurov
 *
 */
public class MenuParticle extends GameOneObject {
	private Handler handler;
	
	private Random r;

	private int red;
	private int blue;
	private int green;
	
	private Color col;
	
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		r = new Random();
		
		this.handler = handler;
		
		velX = (float) (r.nextInt(6 - -6) + -6);
		velY = (float) (r.nextInt(6 - -6) + -6);
		if(velX == 0) velX = 1f;
		if(velY == 0) velY = 1f;
		
		this.red = r.nextInt(255);
		this.blue = r.nextInt(255);
		this.green = r.nextInt(255);
		
		col = new Color(this.red, this.blue, this.green);
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= GameOne.HEIGHT - 16) velY *= -1;
		if(x <= 0 || x >= GameOne.WIDTH - 8) velX *= -1;
		
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.EnemyTrail, this.col, 7, 7, 0.15f, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(this.col);
		g.fillRect((int)x, (int)y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}
}
