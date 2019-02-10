/**
 * 
 */
package com.exercise.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author ILIAN Kutkurov
 * This class will be the base for AI and Players game objects  
 */
public abstract class GameOneObject {
	
	protected ID id;
	
	protected float x, y;
	
	protected float velX, velY;
	
	public GameOneObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

}
