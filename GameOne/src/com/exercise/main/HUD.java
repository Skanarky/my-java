/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author ILIAN Kutkurov
 * 
 * Heads Up Display
 */
public class HUD {
	
	public static int HEALTH = 100;
	
	public void tick() {
		HEALTH = GameOne.clamp(HEALTH, 0, 100);
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(20, 10, 204, 24);
		
		g.setColor(Color.green);
		g.fillRect(22, 12, HEALTH * 2, 20);
		
		g.setColor(Color.white);
		g.drawRect(20, 10, 204, 24);
	}
}
