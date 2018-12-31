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
public class SmartEnemy extends GameOneObject {
	private Handler handler;
	private GameOneObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < this.handler.gameObjects.size(); ++i) {
			if(this.handler.gameObjects.get(i).getId() == ID.Player) this.player = this.handler.gameObjects.get(i);
		}
		
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		// - 6 is to get the center of enemy 
		float diffX = x - this.player.getX() - 6;
		float diffY = y - this.player.getY() - 6;
		
		float distance = (float) Math.sqrt((x - this.player.getX())*(x - this.player.getX()) + (y - this.player.getY())*(y - this.player.getY()));
		
		velX = (int) ((-1.0/distance) * diffX);
		velY = (int) ((-1.0/distance) * diffY);
		
		if(y <= 0 || y >= GameOne.HEIGHT - 24) velY *= -1;
		if(x <= 0 || x >= GameOne.WIDTH - 12) velX *= -1;
		
		this.handler.addObject(new EnemyTrail(this.x, this.y, ID.EnemyTrail, Color.yellow, 11, 11, 0.15f, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 12, 12);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 12, 12);
	}
}