/**
 * 
 */
package com.exercise.main;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author ILIAN Kutkurov
 * Spawning system
 */
public class Spawner {
	private Handler handler;
	private HUD hud;
	
	private boolean bossMode;
	
	private Random r = new Random();
	
	private double keepScore = 0;
	
	public Spawner(Handler handler, HUD hud) {
		this.bossMode = false;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		keepScore = keepScore + 0.05;
		
		if(keepScore >= 5) {
			
			// reset keepScore
			keepScore = 0;
			
			// add a level
			hud.setLevel(hud.getLevel() + 1);
			
			// remove Boss enemy and add back all enemies - up to level 42 - then maybe You Win!
			// HAS TO BE ON TOP, first condition
			if(this.bossMode && (hud.getLevel() == 12 || hud.getLevel() == 22 || hud.getLevel() == 32 || hud.getLevel() == 42)) {		
				
				// clearing all objects in Handler BUT the Player
				this.handler.clearAllEnemies();
				
				// adding the old enemies back, the Boss enemy is cleared in the Handler method
				this.handler.addAllEnemies();
				
			};
			
			// add Boss enemy and clear all enemies temporary
			// HAS TO BE ON TOP, second condition
			if(!this.bossMode && hud.getLevel() % 10 == 0) {
				
				// clearing all objects in Handler BUT the Player
				this.handler.clearAllEnemies();
				
				// Boss mode :-) -> the object Boss enemy + bossMode state
				this.handler.addObject(new BossEnemy((float) (GameOne.WIDTH / 2) - 64, (float) -102, ID.BossEnemy, this.handler));
				
				this.bossMode = true;
				
			};
			
			// add Basic enemy every 2 levels but not divisible by 3 and 7 levels
			if(!this.bossMode && hud.getLevel() % 2 == 0 && hud.getLevel() % 3 != 0 && hud.getLevel() % 7 != 0) {
				this.handler.addObject(new BasicEnemy((float) r.nextInt(GameOne.WIDTH - 5), (float) r.nextInt(GameOne.HEIGHT - 5), ID.BasicEnemy, this.handler));
			};
			
			// add Fast enemy every 3 levels but not divisible by 2 and 7 levels
			if(!this.bossMode && hud.getLevel() % 3 == 0 && hud.getLevel() % 2 != 0 && hud.getLevel() % 7 != 0) {
				this.handler.addObject(new FastEnemy((float) r.nextInt(GameOne.WIDTH - 10), (float) r.nextInt(GameOne.HEIGHT - 10), ID.FastEnemy, this.handler));
			};
			
			// add Smart enemy AND increase speed every 7 levels
			if(!this.bossMode && hud.getLevel() % 7 == 0) {
				
				// increase speed
				for(int i = 0; i < this.handler.gameObjects.size(); ++i) {
					if(this.handler.gameObjects.get(i).getId() == ID.BasicEnemy) {
						this.handler.gameObjects.get(i).setVelX((float) (this.handler.gameObjects.get(i).getVelX() * 1.25));
						this.handler.gameObjects.get(i).setVelY((float) (this.handler.gameObjects.get(i).getVelY() * 1.25));
					}
				};
				
				// add Smart enemy
				this.handler.addObject(new SmartEnemy((float) r.nextInt(GameOne.WIDTH - 10), (float) r.nextInt(GameOne.HEIGHT - 10), ID.SmartEnemy, this.handler));
			};
			
			// stop boss mode
			if(this.bossMode && (hud.getLevel() == 12 || hud.getLevel() == 22 || hud.getLevel() == 32 || hud.getLevel() == 42)) {		
				
				
				// adding Smart enemies, since I didn't keep them in the gameObjectsEnemies array in Handler
				for(int i = 0; i < ((hud.getLevel() - 2) / 10); ++i) {
					this.handler.addObject(new SmartEnemy((float) r.nextInt(GameOne.WIDTH - 10), (float) r.nextInt(GameOne.HEIGHT - 10), ID.SmartEnemy, this.handler));
				};
				
				// no more Boss time :-(
				this.bossMode = false;
			};
			
		};
	}
}
