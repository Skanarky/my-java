/**
 * 
 */
package com.spacerunner.main;

import java.util.Random;

import com.spacerunner.main.GameSpaceRunner.STATE;

/**
 * @author ILIAN Kutkurov
 * Spawning system
 */
public class Spawner {

	private GameSpaceRunner game;
	private Handler handler;
	private HUD hud;
	private Shop shop;
	
	private boolean bossMode;
	
	private Random r;
	
	private double keepScore = 0;
	
	public Spawner(GameSpaceRunner game, Handler handler, HUD hud, Shop shop) {
		this.bossMode = false;
		
		r = new Random();
		
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.shop = shop;
	}
	
	public void tick() {
		
		if(HUD.HEALTH > 0) {
			
			keepScore = keepScore + 0.05;
			
			if(keepScore >= 25) {
				
				// reset keepScore
				keepScore = 0;
				
				// add a level
				hud.setLevel(hud.getLevel() + 1);
				
				// remove Boss enemy and add back all enemies - up to level 42 - then maybe You Win!
				// HAS TO BE ON TOP, first condition
				if(this.bossMode && (hud.getLevel() == 12 || hud.getLevel() == 22 || hud.getLevel() == 32 || hud.getLevel() == 42 || hud.getLevel() == 52)) {		
					
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
					this.handler.addObject(new BossEnemy((float) (GameSpaceRunner.WIDTH / 2) - 64, (float) -102, ID.BossEnemy, this.handler));
					
					this.bossMode = true;
					
				};
				
				// add Basic enemy every 2 levels but not divisible by 3 and 7 levels
				if(!this.bossMode && hud.getLevel() % 2 == 0 && hud.getLevel() % 3 != 0 && hud.getLevel() % 7 != 0) {

					if (GameSpaceRunner.DIFFICULTY == 2) {
						this.handler.addObject(new HardEnemy((float) r.nextInt(GameSpaceRunner.WIDTH - 5), (float) r.nextInt(GameSpaceRunner.HEIGHT - 5), ID.BasicEnemy, this.handler));
					} else {
						this.handler.addObject(new BasicEnemy((float) r.nextInt(GameSpaceRunner.WIDTH - 5), (float) r.nextInt(GameSpaceRunner.HEIGHT - 5), ID.BasicEnemy, this.handler));
					}

				};
				
				// add Fast enemy every 3 levels but not divisible by 2 and 7 levels
				if(!this.bossMode && hud.getLevel() % 3 == 0 && hud.getLevel() % 2 != 0 && hud.getLevel() % 7 != 0) {
					this.handler.addObject(new FastEnemy((float) r.nextInt(GameSpaceRunner.WIDTH - 10), (float) r.nextInt(GameSpaceRunner.HEIGHT - 10), ID.FastEnemy, this.handler));
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
					this.handler.addObject(new SmartEnemy((float) r.nextInt(GameSpaceRunner.WIDTH - 10), (float) r.nextInt(GameSpaceRunner.HEIGHT - 10), ID.SmartEnemy, this.handler));
				};
				
				// stop boss mode
				if(this.bossMode && (hud.getLevel() == 12 || hud.getLevel() == 22 || hud.getLevel() == 32 || hud.getLevel() == 42 || hud.getLevel() == 52)) {		
					
					if(hud.getLevel() < 52) {
						
						// adding Smart enemies, since I didn't keep them in the gameObjectsEnemies array in Handler
						for(int i = 0; i < ((hud.getLevel() - 2) / 10); ++i) {
							this.handler.addObject(new SmartEnemy((float) r.nextInt(GameSpaceRunner.WIDTH - 10), (float) r.nextInt(GameSpaceRunner.HEIGHT - 10), ID.SmartEnemy, this.handler));
						};
						
						// no more Boss time :-(
						this.bossMode = false;
						
					} else if (hud.getLevel() == 52) {
						
						// finish game - A Win
						this.finishGame();
					};
					
				};
				
			};
			
		} else if (HUD.HEALTH <= 0) {
			
			// finish game - Game Over
			this.finishGame();
			
		};
		
	}
	
	public void finishGame() {
		this.handler.gameObjects.clear();
		this.handler.gameObjectsEnemies.clear();
		this.handler.setPlayerSpd(3);
		
		this.game.addMenuParticles();
		
		this.game.gameState = STATE.End;
		
		this.hud.setFinalHealth((int) HUD.HEALTH);
		this.hud.setFinalScore((int) this.hud.getScore());
		
		this.hud.setScore(0f);
		this.hud.setLevel(1);
		this.hud.setBounds(0);
		HUD.HEALTH = 100f;
		
		this.hud.setColorsArr(90, 210, 30);
		
		this.shop.setPriceUH(250);
		this.shop.setPriceUS(250);
		this.shop.setPriceRH(200);
	}
}
