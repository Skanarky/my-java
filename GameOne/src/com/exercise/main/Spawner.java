/**
 * 
 */
package com.exercise.main;

import java.util.Random;

/**
 * @author ILIAN Kutkurov
 * Spawning system
 */
public class Spawner {
	private Handler handler;
	private HUD hud;
	
	private Random r = new Random();
	
	private double keepScore = 0;
	
	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		keepScore = keepScore + 0.05;
		
		if(keepScore >= 50) {
			keepScore = 0;
			hud.setLevel(hud.getLevel() + 1);
				
			if(hud.getLevel() % 2 == 0 && hud.getLevel() % 3 != 0 && hud.getLevel() % 7 != 0) {
				this.handler.addObject(new BasicEnemy(r.nextInt(GameOne.WIDTH - 5), r.nextInt(GameOne.HEIGHT - 5), ID.BasicEnemy, this.handler));
			};
			
			if(hud.getLevel() % 3 == 0 && hud.getLevel() % 2 != 0 && hud.getLevel() % 7 != 0) {
				this.handler.addObject(new FastEnemy(r.nextInt(GameOne.WIDTH - 10), r.nextInt(GameOne.HEIGHT - 10), ID.FastEnemy, this.handler));
			};
//			if(hud.getLevel() % 7 == 0) {
//				this.handler.addObject(new SmartEnemy(r.nextInt(GameOne.WIDTH - 10), r.nextInt(GameOne.HEIGHT - 10), ID.SmartEnemy, this.handler));
//			};
			
			
		};
	}
}
