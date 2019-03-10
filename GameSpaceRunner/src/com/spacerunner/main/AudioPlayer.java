/**
 * 
 */
package com.spacerunner.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * @author Ilian Kutkurov
 *
 */
public class AudioPlayer {
	
	private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	private static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void loadSoundFX() {
		
		try {
			
			soundMap.put("clickFX", new Sound("res/dustyroom_click.ogg"));
			musicMap.put("backgroundMusic", new Music("res/quad_city.ogg"));
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
