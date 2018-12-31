/**
 * 
 */
package com.exercise.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * 
 * @author ILIAN Kutkurov
 * SINGLE threaded game
 * Main Class
 */
public class GameOne extends Canvas implements Runnable {
	
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 870264530016327720L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	
	public GameOne() {
		this.handler = new Handler();
		
		this.addKeyListener(new KeyInput(this.handler));
		
		new Window(WIDTH, HEIGHT, "Building My Game One", this);
		
		this.hud = new HUD();
		
		this.spawner = new Spawner(this.handler, this.hud);
		
		r = new Random();
		
		this.handler.addObject(new Player((float) WIDTH/2-32, (float) HEIGHT/2-32, ID.Player, this.handler));
		
		this.handler.addObject(new BasicEnemy((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.BasicEnemy, this.handler));
		
//		for(int i = 0; i < 6; ++i) {
//			this.handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, this.handler));
//		};
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		};
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) return var = max;
		if(var <= min) return var = min;
		return var;
	}
	
	public static void main(String args[]) {
		new GameOne();
	}
	
}
