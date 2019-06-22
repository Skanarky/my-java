import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 */

/**
 * @author Kutkurov
 *
 */
public class BodyPart {
	
	private int xCoor, yCoor, width, height;
	
	public BodyPart(int xCoor, int yCoor, int blockSize) {
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = blockSize;
		height = blockSize;

	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

}
