import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Kutkurov
 *
 */
public class Gamepanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	private boolean isRunning;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private BodyPart bodyPart;
	private ArrayList<BodyPart> theSnake;
	private int xCoor = 10, yCoor = 10, size = 5;
	
	private int ticks = 0;

	public ArrayList<BodyPart> getTheSnake() {
		return theSnake;
	}

	public Gamepanel() {

		this.setFocusable(true);  
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		this.bodyPart = new BodyPart(xCoor, yCoor, 10);

		this.theSnake = new ArrayList<BodyPart>();

		this.start();
		
	}
	
	public void start() {
		
		isRunning = true;
		
		thread = new Thread();
		thread.start();
		
	}
	
	public void stop() {
		
		isRunning = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void tick() {
		
		if (theSnake.size() == 0) {
			theSnake.add(bodyPart);
		}
		
		++ticks;
		
		if (ticks > 1000000) {
			
			if (right) ++xCoor;
			if (left) --xCoor;
			if (up) --yCoor;
			if (down) ++yCoor;
			
			ticks = 0;

			theSnake.add(bodyPart);
			
			if (theSnake.size() > size) {
				theSnake.remove(0);
			}
			
		}
		
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
//		g.setColor(Color.WHITE);
		
		for(int i = 0; i < WIDTH/10; ++i) {
			
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
			
		}
		
		for(int i = 0; i < HEIGHT/10; ++i) {
			
			g.drawLine(0, i * 10, HEIGHT, i * 10);
			
		}
		
		for(int i = 0; i < theSnake.size(); ++i) {

			theSnake.get(i).draw(g);

		}
		
	}

	@Override
	public void run() {

		while(isRunning) {
			
			this.tick();
			this.repaint();
			
		}
		
	}

}
