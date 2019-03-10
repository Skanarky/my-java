/**
 * 
 */
package com.spacerunner.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author ILIAN Kutkurov
 * Heads-up Display
 */
public class HUD {
	
	public static float HEALTH = 100f;
	
	private int bounds = 0;

	private int[] colors = new int[3];
	
	private float score = 0;
	private int level = 1;
	
	private int finalHealth;
	private int finalScore;
	
	public HUD() {
		this.setColorsArr(90, 210, 30);
	}
	
	public void tick() {
		HEALTH = GameSpaceRunner.clamp(HEALTH, 0f, 100f  + (float)this.bounds);
		
		this.setColorsArr(
			(int)GameSpaceRunner.clamp(colors[0], 0, 255),
			(int)GameSpaceRunner.clamp(colors[1], 0, 255),
			(int)GameSpaceRunner.clamp(colors[2], 0, 255)
		);
		
		if(HEALTH <= 50) {
			this.setColorsArr(244, 194, 13);
		};
		
		if(HEALTH <= 20) {
			this.setColorsArr(219, 50, 54);
		};
		
		score = (score + 0.05f);
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(20, 10, 204 + this.bounds, 24);
		
		g.setColor(new Color(colors[0], colors[1], colors[2]));
		g.fillRect(22, 12, (int)HEALTH * 2, 20);
		
		g.setColor(Color.white);
		g.drawRect(20, 10, 204 + this.bounds, 24);
		
		g.drawString("Score: " + (int)score, 915, 20);
		g.drawString("Level: " + level, 915, 35);
		g.drawString("Hit 'Enter' for Shop", 25, 55);
		g.drawString((int)HEALTH + "%", 110, 27);
	}
	
	public void setColorsArr(int vOne, int vTwo, int vThree) {
		this.colors[0] = vOne;
		this.colors[1] = vTwo;
		this.colors[2] = vThree;
	}
	
	public int getBounds() {
		return bounds;
	}

	public void setBounds(int bounds) {
		this.bounds = bounds;
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

	public int getFinalHealth() {
		return finalHealth;
	}

	public void setFinalHealth(int finalHealth) {
		this.finalHealth = finalHealth;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
}
