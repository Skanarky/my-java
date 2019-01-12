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
public class BossEnemy extends GameOneObject {
	
	private Handler handler;
	private Random r = new Random();
	
	private int timerMoveDown = 60;
	private int timerMoveSidew = 50;

	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0f;
		velY = 2f;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(timerMoveDown <= 0) this.velY = 0;
		else timerMoveDown--;
		
		if(timerMoveDown <= 0) this.timerMoveSidew--;
		if(timerMoveSidew <= 0) {
			
			// start moving sideways
			if(velX == 0) velX = 1;
			
			// increase speed of sideways move
			if(velX < 0) velX -= 0.005f;
			if(velX > 0) velX += 0.005f;
			
			velX = GameOne.clamp(velX, -10f, 10f);
			
			// shoot bullets
			int spawn = r.nextInt(10);
			if(spawn == 0) this.handler.addObject(new BossEnemyBullet((float) this.x + 48, (float) this.y + 48, ID.BossEnemyBullet, this.handler));
			
		};
		
		if(x <= 0 || x >= GameOne.WIDTH - 96) velX *= -1;
		
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
