/**
 * 
 */
package com.exercise.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author ILIAN Kutkurov
 *
 */
public class Window extends Canvas {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 5708824702997805540L;
	
	public Window(int width, int height, String title, GameSpaceRunner game) {
		
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		
		frame.setVisible(true);
		
		game.start();
	}
	
}
