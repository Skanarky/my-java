/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author ILIAN Kutkurov
 *
 */
public class Player extends GameOneObject {
	
	public Random r = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = GameOne.clamp(x, 0, GameOne.WIDTH - 33);
		y = GameOne.clamp(y, 0, GameOne.HEIGHT - 54);
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 32, 32);
	}
	
}
