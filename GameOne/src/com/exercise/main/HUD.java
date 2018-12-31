/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author ILIAN Kutkurov
 * Heads-up Display
 */
public class HUD {
	
	public static float HEALTH = 100f;
	
	private int[] colors = new int[3];
	
	private float score = 0;
	private int level = 1;
	
	public HUD() {
		colors[0] = 90;
		colors[1] = 210;
		colors[2] = 30;
	}
	
	public void tick() {
		HEALTH = GameOne.clamp(HEALTH, 0f, 100f);
		
		colors[0] = (int)GameOne.clamp(colors[0], 0, 255);
		colors[1] = (int)GameOne.clamp(colors[1], 0, 255);
		colors[2] = (int)GameOne.clamp(colors[2], 0, 255);
		
		if(HEALTH <= 50) {
			colors[0] = 244;
			colors[1] = 194;
			colors[2] = 13;
		};
		
		if(HEALTH <= 20) {
			colors[0] = 219;
			colors[1] = 50;
			colors[2] = 54;
		};
		
		score = (score + 0.05f);
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(20, 10, 204, 24);
		
		g.setColor(new Color(colors[0], colors[1], colors[2]));
		g.fillRect(22, 12, (int)HEALTH * 2, 20);
		
		g.setColor(Color.white);
		g.drawRect(20, 10, 204, 24);
		
		g.drawString("Score: " + (int)score, 550, 20);
		g.drawString("Level: " + level, 550, 35);
		g.drawString((int)HEALTH + "%", 110, 27);
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
