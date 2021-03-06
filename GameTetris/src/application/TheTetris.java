package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TheTetris extends Application {

	public static final int MOVE = 25;
	public static final int SIZE = 25;
	public static int MAXX = SIZE * 12;
	public static int MAXY = SIZE * 24;
	public static int [][] MESH = new int [MAXX/SIZE][MAXY/SIZE];

	private static Pane theGroup = new Pane();
	private static Form object;
	private static Scene scene = new Scene(theGroup, (MAXX + 150), MAXY);

	public static int score = 0;
	public static int top = 0;
	private static boolean gameOn = true;
	private static Form nextObject = Controller.makeRect();
	private static int linesNo = 0;
	
	// scene and start game
	
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stg) throws Exception {
		
		for (int[] a : MESH) {
			Arrays.fill(a, 0);
		}

		// score and level text
		Line line = new Line(MAXX, 0, MAXX, MAXY);

		Text theScore = new Text("SCORE: ");
		theScore.setStyle("-fx-font: 20 arial;");
		theScore.setY(50);
		theScore.setX((MAXX + 5));
		
		Text theLevel = new Text("LEVEL: ");
		theLevel.setStyle("-fx-font: 20 arial;");
		theLevel.setY(100);
		theLevel.setX((MAXX + 5));
		theLevel.setFill(Color.GREENYELLOW);
		
		theGroup.getChildren().addAll(line, theScore, theLevel);
		
		// first block and game stage
		Form a = nextObject;
		theGroup.getChildren().addAll(a.a, a.b, a.c, a.d);
		
		moveOnKeyPress(a);
		
		object = a;
		
		nextObject = Controller.makeRect();
		
		stg.setScene(scene);
		stg.setTitle("TETRIS IS AWESOME");
		stg.show();
		
		// timer
		
		Timer theFall = new Timer();
		TimerTask tTask = new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0 || object.d.getY() == 0) {
							top++;
						} else {
							top = 0;
						}
						
						// game over
						if (top == 2) {
							Text gOver = new Text("GAME OVER");
							gOver.setStyle("-fx-font: 70 arial;");
							gOver.setFill(Color.DARKRED);
							gOver.setY(250);
							gOver.setX(10);
							
							theGroup.getChildren().add(gOver);
							
							gameOn = false;
						}
						
						// exit
						if (top == 15) {
							System.exit(0);
						}
						
						// game 
						if (gameOn) {
							moveDownForm(object);
							theScore.setText("SCORE: " + Integer.toString(score));
							theLevel.setText("LEVEL: " + Integer.toString(linesNo));
							
						}
					}
				});
			}
		};
		
		theFall.schedule(tTask, 0, 300);

	}
	
	private void moveOnKeyPress(Form theFormInp) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ev) {
				switch (ev.getCode()) {
					case RIGHT:
						Controller.moveRight(theFormInp);
						break;
					case DOWN:
						moveDownForm(theFormInp);
						score++;
						break;
					case LEFT:
						Controller.moveLeft(theFormInp);
						break;
					case UP:
						moveTurn(theFormInp);
						break;
					default:
						return;
				}
			}
		});
	}
	
	private void moveTurn(Form form) {
		int f = form.form;
		Rectangle a = form.a;
		Rectangle b = form.b;
		Rectangle c = form.c;
		Rectangle d = form.d;
		switch (form.getName()) {
		case "j":
			if (f == 1 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, -2, -2)) {
				moveRight(form.a);
				moveDown(form.a);
				moveDown(form.c);
				moveLeft(form.c);
				moveDown(form.d);
				moveDown(form.d);
				moveLeft(form.d);
				moveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, -2, 2)) {
				moveDown(form.a);
				moveLeft(form.a);
				moveLeft(form.c);
				moveUp(form.c);
				moveLeft(form.d);
				moveLeft(form.d);
				moveUp(form.d);
				moveUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && checkRect(a, -1, 1) && checkRect(c, 1, 1) && checkRect(d, 2, 2)) {
				moveLeft(form.a);
				moveUp(form.a);
				moveUp(form.c);
				moveRight(form.c);
				moveUp(form.d);
				moveUp(form.d);
				moveRight(form.d);
				moveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 2, -2)) {
				moveUp(form.a);
				moveRight(form.a);
				moveRight(form.c);
				moveDown(form.c);
				moveRight(form.d);
				moveRight(form.d);
				moveDown(form.d);
				moveDown(form.d);
				form.changeForm();
				break;
			}
			break;
		case "l":
			if (f == 1 && checkRect(a, 1, -1) && checkRect(c, 1, 1) && checkRect(b, 2, 2)) {
				moveRight(form.a);
				moveDown(form.a);
				moveUp(form.c);
				moveRight(form.c);
				moveUp(form.b);
				moveUp(form.b);
				moveRight(form.b);
				moveRight(form.b);
				form.changeForm();
				break;
			}
			if (f == 2 && checkRect(a, -1, -1) && checkRect(b, 2, -2) && checkRect(c, 1, -1)) {
				moveDown(form.a);
				moveLeft(form.a);
				moveRight(form.b);
				moveRight(form.b);
				moveDown(form.b);
				moveDown(form.b);
				moveRight(form.c);
				moveDown(form.c);
				form.changeForm();
				break;
			}
			if (f == 3 && checkRect(a, -1, 1) && checkRect(c, -1, -1) && checkRect(b, -2, -2)) {
				moveLeft(form.a);
				moveUp(form.a);
				moveDown(form.c);
				moveLeft(form.c);
				moveDown(form.b);
				moveDown(form.b);
				moveLeft(form.b);
				moveLeft(form.b);
				form.changeForm();
				break;
			}
			if (f == 4 && checkRect(a, 1, 1) && checkRect(b, -2, 2) && checkRect(c, -1, 1)) {
				moveUp(form.a);
				moveRight(form.a);
				moveLeft(form.b);
				moveLeft(form.b);
				moveUp(form.b);
				moveUp(form.b);
				moveLeft(form.c);
				moveUp(form.c);
				form.changeForm();
				break;
			}
			break;
		case "o":
			break;
		case "s":
			if (f == 1 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, 0, 2)) {
				moveDown(form.a);
				moveLeft(form.a);
				moveLeft(form.c);
				moveUp(form.c);
				moveUp(form.d);
				moveUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 0, -2)) {
				moveUp(form.a);
				moveRight(form.a);
				moveRight(form.c);
				moveDown(form.c);
				moveDown(form.d);
				moveDown(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, 0, 2)) {
				moveDown(form.a);
				moveLeft(form.a);
				moveLeft(form.c);
				moveUp(form.c);
				moveUp(form.d);
				moveUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 0, -2)) {
				moveUp(form.a);
				moveRight(form.a);
				moveRight(form.c);
				moveDown(form.c);
				moveDown(form.d);
				moveDown(form.d);
				form.changeForm();
				break;
			}
			break;
		case "t":
			if (f == 1 && checkRect(a, 1, 1) && checkRect(d, -1, -1) && checkRect(c, -1, 1)) {
				moveUp(form.a);
				moveRight(form.a);
				moveDown(form.d);
				moveLeft(form.d);
				moveLeft(form.c);
				moveUp(form.c);
				form.changeForm();
				break;
			}
			if (f == 2 && checkRect(a, 1, -1) && checkRect(d, -1, 1) && checkRect(c, 1, 1)) {
				moveRight(form.a);
				moveDown(form.a);
				moveLeft(form.d);
				moveUp(form.d);
				moveUp(form.c);
				moveRight(form.c);
				form.changeForm();
				break;
			}
			if (f == 3 && checkRect(a, -1, -1) && checkRect(d, 1, 1) && checkRect(c, 1, -1)) {
				moveDown(form.a);
				moveLeft(form.a);
				moveUp(form.d);
				moveRight(form.d);
				moveRight(form.c);
				moveDown(form.c);
				form.changeForm();
				break;
			}
			if (f == 4 && checkRect(a, -1, 1) && checkRect(d, 1, -1) && checkRect(c, -1, -1)) {
				moveLeft(form.a);
				moveUp(form.a);
				moveRight(form.d);
				moveDown(form.d);
				moveDown(form.c);
				moveLeft(form.c);
				form.changeForm();
				break;
			}
			break;
		case "z":
			if (f == 1 && checkRect(b, 1, 1) && checkRect(c, -1, 1) && checkRect(d, -2, 0)) {
				moveUp(form.b);
				moveRight(form.b);
				moveLeft(form.c);
				moveUp(form.c);
				moveLeft(form.d);
				moveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && checkRect(b, -1, -1) && checkRect(c, 1, -1) && checkRect(d, 2, 0)) {
				moveDown(form.b);
				moveLeft(form.b);
				moveRight(form.c);
				moveDown(form.c);
				moveRight(form.d);
				moveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && checkRect(b, 1, 1) && checkRect(c, -1, 1) && checkRect(d, -2, 0)) {
				moveUp(form.b);
				moveRight(form.b);
				moveLeft(form.c);
				moveUp(form.c);
				moveLeft(form.d);
				moveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && checkRect(b, -1, -1) && checkRect(c, 1, -1) && checkRect(d, 2, 0)) {
				moveDown(form.b);
				moveLeft(form.b);
				moveRight(form.c);
				moveDown(form.c);
				moveRight(form.d);
				moveRight(form.d);
				form.changeForm();
				break;
			}
			break;
		case "i":
			if (f == 1 && checkRect(a, 2, 2) && checkRect(b, 1, 1) && checkRect(d, -1, -1)) {
				moveUp(form.a);
				moveUp(form.a);
				moveRight(form.a);
				moveRight(form.a);
				moveUp(form.b);
				moveRight(form.b);
				moveDown(form.d);
				moveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && checkRect(a, -2, -2) && checkRect(b, -1, -1) && checkRect(d, 1, 1)) {
				moveDown(form.a);
				moveDown(form.a);
				moveLeft(form.a);
				moveLeft(form.a);
				moveDown(form.b);
				moveLeft(form.b);
				moveUp(form.d);
				moveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && checkRect(a, 2, 2) && checkRect(b, 1, 1) && checkRect(d, -1, -1)) {
				moveUp(form.a);
				moveUp(form.a);
				moveRight(form.a);
				moveRight(form.a);
				moveUp(form.b);
				moveRight(form.b);
				moveDown(form.d);
				moveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && checkRect(a, -2, -2) && checkRect(b, -1, -1) && checkRect(d, 1, 1)) {
				moveDown(form.a);
				moveDown(form.a);
				moveLeft(form.a);
				moveLeft(form.a);
				moveDown(form.b);
				moveLeft(form.b);
				moveUp(form.d);
				moveRight(form.d);
				form.changeForm();
				break;
			}
			break;
		}
	}
	
	private void moveDown(Rectangle rect) {
		if (rect.getY() + MOVE < MAXY)
			rect.setY(rect.getY() + MOVE);

	}

	private void moveRight(Rectangle rect) {
		if (rect.getX() + MOVE <= MAXX - SIZE)
			rect.setX(rect.getX() + MOVE);
	}

	private void moveLeft(Rectangle rect) {
		if (rect.getX() - MOVE >= 0)
			rect.setX(rect.getX() - MOVE);
	}

	private void moveUp(Rectangle rect) {
		if (rect.getY() - MOVE > 0)
			rect.setY(rect.getY() - MOVE);
	}

	private void moveDownForm(Form form) {
		if (form.a.getY() == MAXY - SIZE || form.b.getY() == MAXY - SIZE || form.c.getY() == MAXY - SIZE
				|| form.d.getY() == MAXY - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
			MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
			MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
			MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
			MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
			RemoveRows(theGroup);

			Form a = nextObject;
			nextObject = Controller.makeRect();
			object = a;
			theGroup.getChildren().addAll(a.a, a.b, a.c, a.d);
			moveOnKeyPress(a);
		}

		if (form.a.getY() + MOVE < MAXY && form.b.getY() + MOVE < MAXY && form.c.getY() + MOVE < MAXY
				&& form.d.getY() + MOVE < MAXY) {
			int movea = MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
			int moveb = MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
			int movec = MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
			int moved = MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				form.a.setY(form.a.getY() + MOVE);
				form.b.setY(form.b.getY() + MOVE);
				form.c.setY(form.c.getY() + MOVE);
				form.d.setY(form.d.getY() + MOVE);
			}
		}
	}

	private void RemoveRows(Pane pane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		ArrayList<Node> newrects = new ArrayList<Node>();
		int full = 0;
		for (int i = 0; i < MESH[0].length; i++) {
			for (int j = 0; j < MESH.length; j++) {
				if (MESH[j][i] == 1)
					full++;
			}
			if (full == MESH.length)
				lines.add(i + lines.size());
			full = 0;
		}
		if (lines.size() > 0)
			do {
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle)
						rects.add(node);
				}
				score += 50;
				linesNo++;

				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() == lines.get(0) * SIZE) {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						pane.getChildren().remove(node);
					} else
						newrects.add(node);
				}

				for (Node node : newrects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() < lines.get(0) * SIZE) {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						a.setY(a.getY() + SIZE);
					}
				}
				lines.remove(0);
				rects.clear();
				newrects.clear();
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle)
						rects.add(node);
				}
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					try {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				rects.clear();
			} while (lines.size() > 0);
	}
	
	private boolean moveA(Form form) {
		return (MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
	}

	private boolean moveB(Form form) {
		return (MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
	}

	private boolean moveC(Form form) {
		return (MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
	}

	private boolean moveD(Form form) {
		return (MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
	}
	
	private boolean checkRect(Rectangle rect, int x, int y) {
		
		boolean xb = false;
		boolean yb = false;
		
		if (x >= 0) {
			xb = (rect.getX() + (x * MOVE)) <= (MAXX - SIZE);
		}
		if (x < 0) {
			xb = (rect.getX() + (x * MOVE)) >= 0; 		
		}
		if (y >= 0) {
			yb = (rect.getY() + (y * MOVE)) > 0;
		}
		if (y < 0) {
			yb = (rect.getY() + (y * MOVE)) < MAXY;
		}
		
		return xb && yb && MESH[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
		
	}

}
