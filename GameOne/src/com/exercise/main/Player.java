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

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.green);
		if(id == ID.PlayerTwo) g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
	
}
