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
		
		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Good Ol' Snake");
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {

		new MainGame();

	}

}
