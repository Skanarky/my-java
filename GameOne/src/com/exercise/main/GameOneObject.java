/**
 * 
 */
package com.exercise.main;

import java.awt.Graphics;

/**
 * @author ILIAN Kutkurov
 * This class will be the base for all game objects (player, enemy, health, coins, etc...)  
 */
public abstract class GameOneObject {
	
	protected ID id;
	
	protected int x, y;
	
	protected int velX, velY;
	
	public GameOneObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

}
