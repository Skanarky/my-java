package application;

public class Controller {
	
	// getting numbers and mesh from Tetris class
	public static final int MOVE = TheTetris.MOVE;
	public static final int SIZE = TheTetris.SIZE;
	public static int MAXX = TheTetris.MAXX;
	public static int MAXY = TheTetris.MAXY;
	public static int [][] MESH = TheTetris.MESH;
	
	// moving the blocks, controls
	public static void moveRight(Form form) {
		if ((form.a.getX() + MOVE) <= (MAXX - SIZE) && (form.b.getX() + MOVE) <= (MAXX - SIZE) &&
				(form.c.getX() + MOVE) <= (MAXX - SIZE) && (form.d.getX() + MOVE) <= (MAXX - SIZE)) {
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
		// ...
	}

	// create stones
	public static Form makeRect() {
		// ...
	}

}
