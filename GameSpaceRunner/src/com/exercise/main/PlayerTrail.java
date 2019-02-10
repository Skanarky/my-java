/**
 * 
 */
package com.exercise.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author ILIAN Kutkurov
 *
 */
public class PlayerTrail extends SpaceRunnerObject {
	
	private float alpha = 1;
	
	// life between 0.1 and 0.001
	private float life;
	
	private Handler handler;
	
	private Color color;
	private int width, height;
	
	public PlayerTrail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		this.color = color;
		this.width = width;
		this.height = height;
		
		this.life = life;
	}

	public void tick() {
		if(this.alpha > this.life) {
			this.alpha -= (this.life - 0.072f);
		} else this.handler.removeObject(this);
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(this.makeTransparent(this.alpha));
		
		g.setColor(this.color);
		g.fillOval((int)x, (int)y, this.width, this.height);
		
		g2d.setComposite(this.makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
