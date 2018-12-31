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
		
		if(keepScore >= 5) {
			keepScore = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			// add Basic enemy
			if(hud.getLevel() % 2 == 0 && hud.getLevel() % 3 != 0 && hud.getLevel() % 7 != 0) {
				this.handler.addObject(new BasicEnemy((float) r.nextInt(GameOne.WIDTH - 5), (float) r.nextInt(GameOne.HEIGHT - 5), ID.BasicEnemy, this.handler));
			};
			
			// add Fast enemy
			if(hud.getLevel() % 3 == 0 && hud.getLevel() % 2 != 0 && hud.getLevel() % 7 != 0) {
				this.handler.addObject(new FastEnemy((float) r.nextInt(GameOne.WIDTH - 10), (float) r.nextInt(GameOne.HEIGHT - 10), ID.FastEnemy, this.handler));
			};
			
			// add increase speed every 7 levels + new Smart enemy
			if(hud.getLevel() % 7 == 0) {
				for(int i = 0; i < this.handler.gameObjects.size(); ++i) {
					if(this.handler.gameObjects.get(i).getId() == ID.BasicEnemy) {
						this.handler.gameObjects.get(i).setVelX((float) (this.handler.gameObjects.get(i).getVelX() * 1.25));
						this.handler.gameObjects.get(i).setVelY((float) (this.handler.gameObjects.get(i).getVelY() * 1.25));
					}
				};
				
				this.handler.addObject(new SmartEnemy((float) r.nextInt(GameOne.WIDTH - 10), (float) r.nextInt(GameOne.HEIGHT - 10), ID.SmartEnemy, this.handler));
			};
			
		};
	}
}
