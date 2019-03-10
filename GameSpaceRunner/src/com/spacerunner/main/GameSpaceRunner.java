/**
 * 
 */
package com.spacerunner.main;

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
public class GameSpaceRunner extends Canvas implements Runnable {
	
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 870264530016327720L;
	
	private Handler handler;
	
	private Random r;
	
	private ArrayList<Integer> starsParams = new ArrayList<Integer>();
	
	// started with 640px width, now 1024
	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
	
	private Menu menu;
	
	private Shop shop;
	
	private HUD hud;
	
	private Spawner spawner;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean PAUSED = false;
	
	public static int DIFFICULTY = 1;
	// 1 -> normal
	// 2 -> hard
	
	public enum STATE {
		Menu,
		Select,
		Help,
		Shop,
		Game,
		End
	};
	
	public STATE gameState = STATE.Menu;
	
	public GameSpaceRunner() {
		this.handler = new Handler();
		
		this.hud = new HUD();
		
		this.menu = new Menu(this, this.handler, this.hud);
		
		this.shop = new Shop(this, this.handler, this.hud);
		
		this.addKeyListener(new KeyInput(this.handler, this, this.shop));
		
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		r = new Random();
		
		// music and sound FX
		AudioPlayer.loadSoundFX();
		AudioPlayer.getMusic("backgroundMusic").loop();
		
		// window and background
		for(int i = 0; i < 2000; i += 4) {
			this.addStar(r.nextInt(WIDTH));
			this.addStar(r.nextInt(HEIGHT));
			this.addStar(r.nextInt(4));
			this.addStar(r.nextInt(4));
		};

		new Window(WIDTH, HEIGHT, "Space Runner", this);
		
		this.spawner = new Spawner(this, this.handler, this.hud, this.shop);
		
		if(this.gameState == STATE.Menu || this.gameState == STATE.End) {
			this.addMenuParticles();
		};
		
	}
	
	public void addMenuParticles() {
		for(int i = 0; i < 45; ++i) {
			this.handler.addObject(new MenuParticle((float) r.nextInt(WIDTH - 10), (float) r.nextInt(HEIGHT - 10), ID.MenuParticle, this.handler));
		};
	}
	
	public void addFirstObjects() {
//		Instantiate first game objects -> Player and Enemy
		if(this.gameState == STATE.Game) {
			this.handler.addObject(new Player((float) WIDTH/2-32, (float) HEIGHT/2-32, ID.Player, this.handler));
			
			if (DIFFICULTY == 2) {

				this.handler.addObject(new HardEnemy((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.BasicEnemy, this.handler));

			} else {
				
				this.handler.addObject(new BasicEnemy((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.BasicEnemy, this.handler));
				
			}
			
		};
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
//		int frames = 0;
		
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
//			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println("FPS: " + frames);
//				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		
		if (this.gameState == STATE.Game) {
			
			if (!PAUSED) {
				this.handler.tick();
				this.hud.tick();
				this.spawner.tick();
			}
			
		} else if (this.gameState == STATE.Menu || this.gameState == STATE.Help || this.gameState == STATE.End || this.gameState == STATE.Select) {
			this.handler.tick();
			this.menu.tick();
		};
		
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
		
		if (PAUSED) {
			g.setColor(Color.red);
			g.drawString("PAUSED", 490, 360);
		}
		
		if(this.gameState == STATE.Game) {
			
			this.handler.render(g);
			this.hud.render(g);
			
		} else if (this.gameState == STATE.Shop) {
			
			this.shop.render(g);
			
		} else if (this.gameState == STATE.Menu || this.gameState == STATE.Help || this.gameState == STATE.End || this.gameState == STATE.Select) {
			
			this.handler.render(g);
			this.menu.render(g);
			
		};
		
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
		new GameSpaceRunner();
	}
	
}
