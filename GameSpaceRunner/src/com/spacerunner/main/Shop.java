/**
 * 
 */
package com.spacerunner.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.spacerunner.main.GameSpaceRunner.STATE;

/**
 * @author ILIAN Kutkurov
 *
 */
public class Shop extends MouseAdapter {
	
	private GameSpaceRunner game;
	
	private Handler handler;
	
	private HUD hud;
	
	private int priceUH = 250;
	private int priceUS = 250;
	private int priceRH = 200;
	
	private static String enoughOrNot = "";
	
	public Shop(GameSpaceRunner g, Handler h, HUD hd) {
		this.game = g;
		this.handler = h;
		this.hud = hd;
	}
	
	
	public int getPriceUH() {
		return priceUH;
	}


	public void setPriceUH(int priceUH) {
		this.priceUH = priceUH;
	}


	public int getPriceUS() {
		return priceUS;
	}


	public void setPriceUS(int priceUS) {
		this.priceUS = priceUS;
	}


	public int getPriceRH() {
		return priceRH;
	}


	public void setPriceRH(int priceRH) {
		this.priceRH = priceRH;
	}


	public void render(Graphics g) {
		Font fntMain = new Font("arial", 1, 50);
		Font fntOption = new Font("arial", 1, 35);
		Font fntSmall = new Font("arial", 1, 15);
		
		g.setColor(Color.white);
		g.setFont(fntMain);
		g.drawString("SHOP", 445, 150);
		
		g.setFont(fntOption);
		g.drawRect(375, 200, 275, 65);
		g.drawString("Upgrade Health", 384, 235);
		g.setFont(fntSmall);
		g.drawString("Cost: " + this.priceUH, 475, 258);
		
		g.setFont(fntOption);
		g.drawRect(375, 315, 275, 65);
		g.drawString("Upgrade Speed", 386, 350);
		g.setFont(fntSmall);
		g.drawString("Cost: " + this.priceUS, 475, 373);
		
		g.setFont(fntOption);
		g.drawRect(375, 430, 275, 65);
		g.drawString("Refill Health", 409, 465);
		g.setFont(fntSmall);
		g.drawString("Cost: " + this.priceRH, 475, 488);
		
		g.setFont(fntOption);
		g.drawString("Score: " + (int)this.hud.getScore(), 375, 560);
		g.setColor(Color.orange);
		g.drawString(this.getEnoughOrNot(), 375, 630);
		
		g.setColor(Color.white);
		g.setFont(fntSmall);
		g.drawString(" > Hit Enter to Go Back in the Game < ", 380, 700);
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		// Upgrade Health
		if(this.mouseOver(mx, my, 375, 200, 275, 65)) {
			
			if (this.game.gameState == STATE.Shop) {
				
				if (this.hud.getScore() >= this.priceUH) {
					
					this.hud.setScore(this.hud.getScore() - this.priceUH);
					
					this.priceUH += 50;
					
					this.hud.setBounds(this.hud.getBounds() + 20);
					
					HUD.HEALTH = (100f + ((float) this.hud.getBounds() / 2));
					
					this.hud.setColorsArr(90, 210, 30);
					
					this.setEnoughOrNot("You upgraded HEALTH!");
					
				} else {
					
					this.setEnoughOrNot("Not enough POINTS!");
					
				}
				
				
				AudioPlayer.getSound("clickFX").play();
		
			}
			
		}
		
		// Upgrade Speed
		if(this.mouseOver(mx, my, 375, 315, 275, 65)) {
			
			if (this.game.gameState == STATE.Shop) {
				
				if (this.hud.getScore() >= this.priceUS) {
					
					this.hud.setScore(this.hud.getScore() - this.priceUS);
					
					this.priceUS += 50;
					
					this.handler.setPlayerSpd(this.handler.getPlayerSpd() + 1);
					
					this.setEnoughOrNot("You upgraded SPEED!");
					
				} else {
					
					this.setEnoughOrNot("Not enough POINTS!");
					
				}
				
				
				AudioPlayer.getSound("clickFX").play();
		
			}
			
		}
		
		// Refill Health
		if(this.mouseOver(mx, my, 375, 430, 275, 65)) {
			
			if (this.game.gameState == STATE.Shop) {
				
				if (this.hud.getScore() >= this.priceRH) {
					
					this.hud.setScore(this.hud.getScore() - this.priceRH);
					
					HUD.HEALTH = (100f + ((float) this.hud.getBounds() / 2));
					
					this.hud.setColorsArr(90, 210, 30);
					
					this.setEnoughOrNot("You refilled HEALTH!");
					
				} else {
					
					this.setEnoughOrNot("Not enough POINTS!");
					
				}
				
				
				AudioPlayer.getSound("clickFX").play();
		
			}
			
		}
		
	}
	
	public String getEnoughOrNot() {
		return enoughOrNot;
	}

	public void setEnoughOrNot(String enoughOrNot) {
		Shop.enoughOrNot = enoughOrNot;
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
}
