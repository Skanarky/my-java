/**
 * 
 */
package com.spacerunner.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author ILIAN Kutkurov
 *
 */
public class Shop extends MouseAdapter {
	
	private Handler handler;
	
	private int B1 = 1000;
	private int B2 = 1000;
	private int B3 = 1000;
	
	public Shop(Handler h) {
		this.handler = h;
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
		g.drawString("Cost: " + this.B1, 475, 258);
		
		g.setFont(fntOption);
		g.drawRect(375, 315, 275, 65);
		g.drawString("Upgrade Speed", 386, 350);
		g.setFont(fntSmall);
		g.drawString("Cost: " + this.B2, 475, 373);
		
		g.setFont(fntOption);
		g.drawRect(375, 430, 275, 65);
		g.drawString("Refill Health", 409, 465);
		g.setFont(fntSmall);
		g.drawString("Cost: " + this.B3, 475, 488);
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
}
