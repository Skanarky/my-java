import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author Kutkurov
 *
 */
public class MainGame {
	
	public MainGame() {
		
		JFrame frame = new JFrame();
		Panel gamePanel = new Panel();
		
		frame.setPreferredSize(new Dimension(Panel.WIDTH, Panel.HEIGHT));
		frame.setMaximumSize(new Dimension(Panel.WIDTH, Panel.HEIGHT));
		frame.setMinimumSize(new Dimension(Panel.WIDTH, Panel.HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Good Ol' Snake");
		
		frame.setResizable(false);
		
//		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.add(gamePanel);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {

		new MainGame();

	}

}
