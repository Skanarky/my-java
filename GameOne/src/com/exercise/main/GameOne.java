/**
 * 
 */
package com.exercise.main;

import java.awt.Canvas;

/**
 * @author ILIAN Kutkurov
 *
 */
public class GameOne extends Canvas implements Runnable {
	
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 870264530016327720L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	public GameOne() {
		new Window(WIDTH, HEIGHT, "Building My Game One", this);
	}
	
	public synchronized void start() {
		
	}
	
	public void run() {
		
	}
	
	public static void main(String args[]) {
		new GameOne();
	}
	
}
