/**
 * 
 */
package com.spacerunner.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author ILIAN Kutkurov
 *
 */
public class BossEnemy extends SpaceRunnerObject {
	
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
			
			velX = GameSpaceRunner.clamp(velX, -10f, 10f);
			
			// shoot bullets
			int spawn = r.nextInt(7);
			if(spawn == 0) this.handler.addObject(new BossEnemyBullet((float) this.x + 48, (float) this.y + 48, ID.BossEnemyBullet, this.handler));
			
		};
		
		if(x <= 0 || x >= GameSpaceRunner.WIDTH - 96) velX *= -1;
		
		// BossEnemyTrail ID is only an id without a designated class for it, just using the EnemyTrail class
		// -> so I can keep track of enemy objects in Handler for the copy of main array of objects
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.BossEnemyTrail, Color.cyan, 96, 96, 0.1f, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 96, 96);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}
	
}
