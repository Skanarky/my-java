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
	
	private int colorVal = 255;
	
	private double score = 0;
	private int level = 1;
	
	public void tick() {
		HEALTH = GameOne.clamp(HEALTH, 0, 100);
		
		colorVal = GameOne.clamp(colorVal, 0, 255);
		colorVal = 255 * HEALTH / 100;
		
		score = score + 0.05;
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(20, 10, 204, 24);
		
		g.setColor(new Color(150, colorVal, 0));
		g.fillRect(22, 12, HEALTH * 2, 20);
		
		g.setColor(Color.white);
		g.drawRect(20, 10, 204, 24);
		
		g.drawString("Score: " + (int)score, 550, 20);
		g.drawString("Level: " + level, 550, 35);
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
