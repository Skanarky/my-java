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
public class HardEnemy extends SpaceRunnerObject {
	
	private Handler handler;
	
	private Random r;

	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		r = new Random();
		
		velX = 2f;
		velY = 2f;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		// BELOW is the difference with Basic Enemy (unpredictable bouncing of the edges)
		if(y <= 0 || y >= GameSpaceRunner.HEIGHT - 32) {
//			velY *= -1;
			if (velY < 0) {
				velY = -(r.nextInt(7) + 1) * -1;
			} else {
				velY = (r.nextInt(7) + 1) * -1;
			}
		}
		if(x <= 0 || x >= GameSpaceRunner.WIDTH - 16) {
//			velX *= -1;
			if (velX < 0) {
				velX = -(r.nextInt(7) + 1) * -1;
			} else {
				velX = (r.nextInt(7) + 1) * -1;
			}
		}
		
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
