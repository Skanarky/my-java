import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Kutkurov
 *
 */
public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;

	public Panel() {
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
	}

}
