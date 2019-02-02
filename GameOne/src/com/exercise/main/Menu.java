/**
 * 
 */
package com.exercise.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.exercise.main.GameOne.STATE;

/**
 * @author Ilian Kutkurov
 *
 */
public class Menu extends MouseAdapter {
	
	private GameOne game;
	private Handler handler;
	private HUD hud;
	
	private String gameEnd;
	
	public Menu(GameOne g, Handler h, HUD hud) {
		this.game = g;
		
		this.handler = h;
		
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		// Start
		if(this.mouseOver(mx, my, 412, 200, 200, 65)) {
			if(this.game.gameState == STATE.Menu) {
				
				this.game.gameState = STATE.Game;
				
				this.handler.gameObjects.clear();
				
				this.game.addFirstObjects();
				
				AudioPlayer.getSound("clickFX").play();
		
			};
		};
		
		// Help
		if(this.mouseOver(mx, my, 412, 315, 200, 65)) {
			if(this.game.gameState == STATE.Menu) {
				
				this.game.gameState = STATE.Help;
				
				AudioPlayer.getSound("clickFX").play();
		
			};
		};
		
		// Back to Menu or Try Again
		if(this.mouseOver(mx, my, 150, 100, 242, 65)) {
			if(this.game.gameState == STATE.Help || this.game.gameState == STATE.End) {
				
				this.game.gameState = STATE.Menu;
				
				AudioPlayer.getSound("clickFX").play();
				
			};
		};
		
		// Quit
		if(this.mouseOver(mx, my, 412, 430, 200, 65)) {
			if(this.game.gameState == STATE.Menu) {
				
				AudioPlayer.getSound("clickFX").play();
				
				System.exit(1);
		
			};
		};
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fntMenu = new Font("arial", 1, 50);
		Font fntOption = new Font("arial", 1, 35);
		Font fntHelp = new Font("arial", 1, 20);
		g.setColor(Color.white);
		
		
		if(this.game.gameState == STATE.Menu) {
			
			g.setFont(fntMenu);
			g.drawString("Space Runner", 340, 150);
			
			g.setFont(fntOption);
			
			g.drawRect(412, 200, 200, 65);
			g.drawString("Play", 475, 245);
			
			g.drawRect(412, 315, 200, 65);
			g.drawString("Help", 475, 360);
			
			g.drawRect(412, 430, 200, 65);
			g.drawString("Quit", 475, 475);
			
		} else if (this.game.gameState == STATE.Help) {
			g.setFont(fntOption);
			g.drawRect(150, 100, 242, 65);
			g.drawString("Back to Menu", 158, 145);
			
			g.setFont(fntHelp);
			g.drawString("Welcome to Space Runner!", 300, 250);
			g.drawString("Try to run away from all the enemies by", 300, 290);
			g.drawString("using the ARROW keys (up, down, left, right).", 300, 315);
			g.drawString("Levels are changing every 30 points (points are", 300, 340);
			g.drawString("added automatically based on how long the player", 300, 365);
			g.drawString("has been alive/survives the impacts with the", 300, 390);
			g.drawString("enemies.", 300, 415);
			g.drawString("All enemies have different damage levels and there", 300, 440);
			g.drawString("are 4 types of enemies, from which the Boss is the", 300, 465);
			g.drawString("scariest (every 10th level) :) !", 300, 490);
			g.drawString("Good luck!", 300, 530);
		} else if (this.game.gameState == STATE.End) {
			g.setFont(fntOption);
			g.drawRect(150, 100, 242, 65);
			g.drawString("Play Again", 182, 145);
			
			g.setFont(fntHelp);
			
			if(this.hud.getFinalHealth() > 0) {
				this.gameEnd = "Y O U   W O N !";
			} else {
				this.gameEnd = "G A M E   O V E R !";
			};
			
			g.drawString(this.gameEnd, 300, 250);
			g.drawString("You reached a score of " + (int) this.hud.getFinalScore() + " points.", 300, 290);
			
		};
		
		
	}

}
