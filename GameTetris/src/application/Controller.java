package application;

import javafx.scene.shape.Rectangle;

public class Controller {
	
	// getting numbers and mesh from Tetris class
	public static final int MOVE = TheTetris.MOVE;
	public static final int SIZE = TheTetris.SIZE;
	public static int XMAX = TheTetris.MAXX;
	public static int YMAX = TheTetris.MAXY;
	public static int [][] MESH = TheTetris.MESH;
	
	// moving the blocks, controls
	public static void moveRight(Form form) {
		if ((form.a.getX() + MOVE) <= (XMAX - SIZE) && (form.b.getX() + MOVE) <= (XMAX - SIZE) &&
				(form.c.getX() + MOVE) <= (XMAX - SIZE) && (form.d.getX() + MOVE) <= (XMAX - SIZE)) {
			int moveA = MESH[((int) form.a.getX() / SIZE) + 1 ][(int) form.a.getY() / SIZE];
			int moveB = MESH[((int) form.b.getX() / SIZE) + 1 ][(int) form.b.getY() / SIZE];
			int moveC = MESH[((int) form.c.getX() / SIZE) + 1 ][(int) form.c.getY() / SIZE];
			int moveD = MESH[((int) form.d.getX() / SIZE) + 1 ][(int) form.d.getY() / SIZE];
			
			if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
				form.a.setX(form.a.getX() + MOVE);
				form.b.setX(form.b.getX() + MOVE);
				form.c.setX(form.c.getX() + MOVE);
				form.d.setX(form.d.getX() + MOVE);
			}
		}
	}
	
	public static void moveLeft(Form form) {
		if ((form.a.getX() - MOVE) >= 0 && (form.b.getX() - MOVE) >= 0 &&
				(form.c.getX() - MOVE) >= 0 && (form.d.getX() - MOVE) >= 0) {
			int moveA = MESH[((int) form.a.getX() / SIZE) - 1 ][(int) form.a.getY() / SIZE];
			int moveB = MESH[((int) form.b.getX() / SIZE) - 1 ][(int) form.b.getY() / SIZE];
			int moveC = MESH[((int) form.c.getX() / SIZE) - 1 ][(int) form.c.getY() / SIZE];
			int moveD = MESH[((int) form.d.getX() / SIZE) - 1 ][(int) form.d.getY() / SIZE];
			
			if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
				form.a.setX(form.a.getX() - MOVE);
				form.b.setX(form.b.getX() - MOVE);
				form.c.setX(form.c.getX() - MOVE);
				form.d.setX(form.d.getX() - MOVE);
			}
		}
	}

	// create stones
	public static Form makeRect() {
		int block = (int) (Math.random() * 100);
		String name;
		Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
				d = new Rectangle(SIZE-1, SIZE-1);
		if (block < 15) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			name = "j";
		} else if (block < 30) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			name = "l";
		} else if (block < 45) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 - SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2);
			d.setY(SIZE);
			name = "o";
		} else if (block < 60) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 - SIZE);
			d.setY(SIZE);
			name = "s";
		} else if (block < 75) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			name = "t";
		} else if (block < 90) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 + SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE + SIZE);
			d.setY(SIZE);
			name = "z";
		} else { 
			a.setX(XMAX / 2 - SIZE - SIZE);
			b.setX(XMAX / 2 - SIZE);
			c.setX(XMAX / 2);
			d.setX(XMAX / 2 + SIZE);
			name = "i";
		}
		return new Form(a, b, c, d, name);
	}

}
