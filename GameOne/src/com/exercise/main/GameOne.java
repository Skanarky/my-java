/**
 * 
 */
package com.exercise.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
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
	
	// started with 640px width
	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	private ArrayList<Integer> starsParams = new ArrayList<Integer>();
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	
	public GameOne() {
		this.handler = new Handler();
		
		r = new Random();
		
		for(int i = 0; i < 2000; i += 4) {
			this.addStar(r.nextInt(WIDTH));
			this.addStar(r.nextInt(HEIGHT));
			this.addStar(r.nextInt(4));
			this.addStar(r.nextInt(4));
		};

		
		this.addKeyListener(new KeyInput(this.handler));
		
		new Window(WIDTH, HEIGHT, "Space Runner", this);
		
		this.hud = new HUD();
		
		this.spawner = new Spawner(this.handler, this.hud);
		
		this.handler.addObject(new Player((float) WIDTH/2-32, (float) HEIGHT/2-32, ID.Player, this.handler));
		
//		this.handler.addObject(new BasicEnemy((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.BasicEnemy, this.handler));
		this.handler.addObject(new BossEnemy((float) r.nextInt(GameOne.WIDTH - 32), (float) r.nextInt(GameOne.HEIGHT - 32), ID.BossEnemy, this.handler));
		
//		for(int i = 0; i < 6; ++i) {
//			this.handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, this.handler));
//		};
		
	}
	
	private void addStar(int star) {
		this.starsParams.add(star);
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
		Graphics star = bs.getDrawGraphics();
		
		// background color
		g.setColor(Color.black);
		// stars color
		star.setColor(Color.white);
		
		// creating background 
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// creating stars
		for(int i = 0; i < 2000; i += 4) {
			star.fillOval(this.starsParams.get(i), this.starsParams.get(i + 1), this.starsParams.get(i + 2), this.starsParams.get(i + 3));
		};
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		star.dispose();
		
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
