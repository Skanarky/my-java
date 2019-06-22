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
		
//		RUN and STOP methods in Panel?!?!
		
		JFrame frame = new JFrame();
		Gamepanel gamePanel = new Gamepanel();
		
		frame.add(gamePanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Good Ol' Snake");
		
		frame.setResizable(false);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {

		new MainGame();

	}

}
