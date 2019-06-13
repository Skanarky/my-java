import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Kutkurov
 *
 */
public class Panel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	private boolean isRunning;

	public Panel() {
		
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
		
		// add tick element... bodyPart
		
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
		
	}

	@Override
	public void run() {

		while(isRunning) {
			
			this.tick();
			this.repaint();
			
		}
		
	}

}
