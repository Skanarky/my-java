/**
 * 
 */
package com.spacerunner.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.util.Random;

/**
 * @author ILIAN Kutkurov
 *
 */
public class HardEnemy extends SpaceRunnerObject {
	
	private Handler handler;
	
//	private Random r;

	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
//		r = new Random();
		
		// the only difference with the Basic Enemy is the initial speed for now
		velX = 3.5f;
		velY = 3.5f;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		 
		if(y <= 0 || y >= GameSpaceRunner.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= GameSpaceRunner.WIDTH - 16) velX *= -1;
		
		// unstable - not using it for nor
		// BELOW is the difference with Basic Enemy / unpredictable bouncing of the edges
//		if(y <= 16 || y >= GameSpaceRunner.HEIGHT - 32) {
//			
//			// 1 - works better
//			if (velY < 0) {
//				velY = 1.5f + (float) r.nextInt(7);
//			} else {
//				velY = -1.5f - (float) r.nextInt(7);
//			}
//			
//			// 2 - no
//			if (velY < 0) {
//				velY = (float)(-(r.nextInt(7) + 1) * -1);
//			} else {
//				velY = (float)((r.nextInt(7) + 1) * -1);
//			}
//		}
//		if(x <= 16 || x >= GameSpaceRunner.WIDTH - 32) {
//			
//			// 1 - works better
//			if (velX < 0) {
//				velX = 1.5f + (float) r.nextInt(7);
//			} else {
//				velX = -1.5f - (float) r.nextInt(7);
//			}
//			
//			// 2 - no
//			if (velX < 0) {
//				velX = (float)(-(r.nextInt(7) + 1) * -1);
//			} else {
//				velX = (float)((r.nextInt(7) + 1) * -1);
//			}
//		}
		
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
