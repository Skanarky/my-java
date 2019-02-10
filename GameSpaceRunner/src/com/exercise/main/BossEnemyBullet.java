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
public class BossEnemyBullet extends SpaceRunnerObject {
	
	private Handler handler;
	private Random r;

	public BossEnemyBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		r = new Random();
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5f;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
//		if(y <= 0 || y >= GameOne.HEIGHT - 32) velY *= -1;
//		if(x <= 0 || x >= GameOne.WIDTH - 16) velX *= -1;
		
		if(this.y >= GameSpaceRunner.HEIGHT) this.handler.removeObject(this);
		
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.EnemyTrail, Color.cyan, 15, 15, 0.1f, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
}