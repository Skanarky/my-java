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
import javafx.scene.layout.BorderPane;
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
	
	public void main(String[] args) {
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
	
	private void moveTurn(Form theFormInp) {
		int theF = theFormInp.form;
		Rectangle a = theFormInp.a;
		Rectangle b = theFormInp.b;
		Rectangle c = theFormInp.c;
		Rectangle d = theFormInp.d;
		
		switch (theFormInp.getName()) {
			case "j":
				if (theF == 1 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					moveRight(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.c);
					moveDown(theFormInp.c);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.d);
					moveDown(theFormInp.d);
					moveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					moveRight(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.c);
					moveDown(theFormInp.c);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.d);
					moveDown(theFormInp.d);
					moveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					moveRight(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.c);
					moveDown(theFormInp.c);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.d);
					moveDown(theFormInp.d);
					moveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					moveRight(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.c);
					moveDown(theFormInp.c);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.d);
					moveDown(theFormInp.d);
					moveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
			case "l":
				if (theF == 1 && checkRect(a, 1, -1) && checkRect(c, 1, 1) && checkRect(b, 2, 2)) {
					moveRight(theFormInp.a);
					moveDown(theFormInp.a);
					moveUp(theFormInp.c);
					moveRight(theFormInp.c);
					moveUp(theFormInp.b);
					moveUp(theFormInp.b);
					moveRight(theFormInp.b);
					moveRight(theFormInp.b);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, -1, -1) && checkRect(b, 2, -2) && checkRect(c, 1, -1)) {
					moveDown(theFormInp.a);
					moveLeft(theFormInp.a);
					moveRight(theFormInp.b);
					moveRight(theFormInp.b);
					moveDown(theFormInp.b);
					moveDown(theFormInp.b);
					moveRight(theFormInp.c);
					moveDown(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, -1, 1) && checkRect(c, -1, -1) && checkRect(b, -2, -2)) {
					moveLeft(theFormInp.a);
					moveUp(theFormInp.a);
					moveDown(theFormInp.c);
					moveLeft(theFormInp.c);
					moveDown(theFormInp.b);
					moveDown(theFormInp.b);
					moveLeft(theFormInp.b);
					moveLeft(theFormInp.b);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, 1, 1) && checkRect(b, -2, 2) && checkRect(c, -1, 1)) {
					moveUp(theFormInp.a);
					moveRight(theFormInp.a);
					moveLeft(theFormInp.b);
					moveLeft(theFormInp.b);
					moveUp(theFormInp.b);
					moveUp(theFormInp.b);
					moveLeft(theFormInp.c);
					moveUp(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				break;
			case "o":
				break;
			case "s":
				if (theF == 1 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, 0, 2)) {
					moveDown(theFormInp.a);
					moveLeft(theFormInp.a);
					moveLeft(theFormInp.c);
					moveUp(theFormInp.c);
					moveUp(theFormInp.d);
					moveUp(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 0, -2)) {
					moveUp(theFormInp.a);
					moveRight(theFormInp.a);
					moveRight(theFormInp.c);
					moveDown(theFormInp.c);
					moveDown(theFormInp.d);
					moveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, 0, 2)) {
					moveDown(theFormInp.a);
					moveLeft(theFormInp.a);
					moveLeft(theFormInp.c);
					moveUp(theFormInp.c);
					moveUp(theFormInp.d);
					moveUp(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 0, -2)) {
					moveUp(theFormInp.a);
					moveRight(theFormInp.a);
					moveRight(theFormInp.c);
					moveDown(theFormInp.c);
					moveDown(theFormInp.d);
					moveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
			case "t":
				if (theF == 1 && checkRect(a, 1, 1) && checkRect(d, -1, -1) && checkRect(c, -1, 1)) {
					moveUp(theFormInp.a);
					moveRight(theFormInp.a);
					moveDown(theFormInp.d);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.c);
					moveUp(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, 1, -1) && checkRect(d, -1, 1) && checkRect(c, 1, 1)) {
					moveRight(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.d);
					moveUp(theFormInp.d);
					moveUp(theFormInp.c);
					moveRight(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, -1, -1) && checkRect(d, 1, 1) && checkRect(c, 1, -1)) {
					moveDown(theFormInp.a);
					moveLeft(theFormInp.a);
					moveUp(theFormInp.d);
					moveRight(theFormInp.d);
					moveRight(theFormInp.c);
					moveDown(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, -1, 1) && checkRect(d, 1, -1) && checkRect(c, -1, -1)) {
					moveLeft(theFormInp.a);
					moveUp(theFormInp.a);
					moveRight(theFormInp.d);
					moveDown(theFormInp.d);
					moveDown(theFormInp.c);
					moveLeft(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				break;
			case "z":
				if (theF == 1 && checkRect(b, 1, 1) && checkRect(c, -1, 1) && checkRect(d, -2, 0)) {
					moveUp(theFormInp.b);
					moveRight(theFormInp.b);
					moveLeft(theFormInp.c);
					moveUp(theFormInp.c);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(b, -1, -1) && checkRect(c, 1, -1) && checkRect(d, 2, 0)) {
					moveDown(theFormInp.b);
					moveLeft(theFormInp.b);
					moveRight(theFormInp.c);
					moveDown(theFormInp.c);
					moveRight(theFormInp.d);
					moveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(b, 1, 1) && checkRect(c, -1, 1) && checkRect(d, -2, 0)) {
					moveUp(theFormInp.b);
					moveRight(theFormInp.b);
					moveLeft(theFormInp.c);
					moveUp(theFormInp.c);
					moveLeft(theFormInp.d);
					moveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(b, -1, -1) && checkRect(c, 1, -1) && checkRect(d, 2, 0)) {
					moveDown(theFormInp.b);
					moveLeft(theFormInp.b);
					moveRight(theFormInp.c);
					moveDown(theFormInp.c);
					moveRight(theFormInp.d);
					moveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
			case "i":
				if (theF == 1 && checkRect(a, 2, 2) && checkRect(b, 1, 1) && checkRect(d, -1, -1)) {
					moveUp(theFormInp.a);
					moveUp(theFormInp.a);
					moveRight(theFormInp.a);
					moveRight(theFormInp.a);
					moveUp(theFormInp.b);
					moveRight(theFormInp.b);
					moveDown(theFormInp.d);
					moveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, -2, -2) && checkRect(b, -1, -1) && checkRect(d, 1, 1)) {
					moveDown(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.a);
					moveLeft(theFormInp.a);
					moveDown(theFormInp.b);
					moveLeft(theFormInp.b);
					moveUp(theFormInp.d);
					moveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, 2, 2) && checkRect(b, 1, 1) && checkRect(d, -1, -1)) {
					moveUp(theFormInp.a);
					moveUp(theFormInp.a);
					moveRight(theFormInp.a);
					moveRight(theFormInp.a);
					moveUp(theFormInp.b);
					moveRight(theFormInp.b);
					moveDown(theFormInp.d);
					moveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, -2, -2) && checkRect(b, -1, -1) && checkRect(d, 1, 1)) {
					moveDown(theFormInp.a);
					moveDown(theFormInp.a);
					moveLeft(theFormInp.a);
					moveLeft(theFormInp.a);
					moveDown(theFormInp.b);
					moveLeft(theFormInp.b);
					moveUp(theFormInp.d);
					moveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
		}
	}
	
	private void moveDownForm(Form form) {
		// move if bottom is full
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

		// move down is bottom is NOT full
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

	private void RemoveRows(Pane thePane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		ArrayList<Node> newRects = new ArrayList<Node>();
		
		int full = 0;
		
		for (int i = 0; i < MESH[0].length; ++i) {
			for (int j = 0; j < MESH.length; ++j) {
				if (MESH[j][i] == 1) {
					++full;
				}
			}
			if (full == MESH.length) {
				lines.add((i + lines.size()));
			} else {
				full = 0;
			}
		}
		
		if (lines.size() > 0) {
			
			do {
				
				for (Node aNode : thePane.getChildren()) {
					
					if (aNode instanceof Rectangle) {
						rects.add(aNode);
					}
					
				}
				
				score += 50;
				++linesNo;
				
				for (Node aNode : rects) {
					
					Rectangle a = (Rectangle) aNode;
					
					if (a.getY() == (lines.get(0) * SIZE)) {
						MESH[(int) (a.getX() / SIZE)][(int) (a.getY() / SIZE)] = 0;
						thePane.getChildren().remove(aNode);
					} else {
						newRects.add(aNode);
					}
					
				}
				
				for (Node aNode : newRects) {
					
					Rectangle a = (Rectangle) aNode;
					
					if (a.getY() < (lines.get(0) * SIZE)) {
						MESH[(int) (a.getX() / SIZE)][(int) (a.getY() / SIZE)] = 0;
						thePane.getChildren().remove(aNode);
						a.setY(a.getY() + SIZE);
					}
					
				}
				
				lines.remove(0);
				rects.clear();
				newRects.clear();
				
				for (Node aNode : thePane.getChildren()) {
					
					if (aNode instanceof Rectangle) {
						rects.add(aNode);
					}
					
				}
				
				for (Node aNode : rects) {
					
					Rectangle a = (Rectangle) aNode;
					
					try {
						MESH[(int) (a.getX() / SIZE)][(int) (a.getY() / SIZE)] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
						// ...!?
					}
					
				}
				
				rects.clear();

			} while (lines.size() > 0);
			
		}
	}
	
	private boolean moveA(Form theForm) {
		return (MESH[(int) theForm.a.getX() / SIZE][((int) theForm.a.getY() / SIZE) + 1] == 1);
	}

	private boolean moveB(Form theForm) {
		return (MESH[(int) theForm.b.getX() / SIZE][((int) theForm.b.getY() / SIZE) + 1] == 1);
	}

	private boolean moveC(Form theForm) {
		return (MESH[(int) theForm.c.getX() / SIZE][((int) theForm.c.getY() / SIZE) + 1] == 1);
	}

	private boolean moveD(Form theForm) {
		return (MESH[(int) theForm.d.getX() / SIZE][((int) theForm.d.getY() / SIZE) + 1] == 1);
	}
	
	private void moveDown(Rectangle rect) {
		if (rect.getY() + MOVE < MAXY) {
			rect.setY(rect.getY() + MOVE);
		}
	}

	private void moveRight(Rectangle rect) {
		if (rect.getX() + MOVE <= MAXX - SIZE) {
			rect.setX(rect.getX() + MOVE);
		}
	}

	private void moveLeft(Rectangle rect) {
		if (rect.getX() - MOVE >= 0) {
			rect.setX(rect.getX() - MOVE);
		}
	}

	private void moveUp(Rectangle rect) {
		if (rect.getY() - MOVE > 0) {
			rect.setY(rect.getY() - MOVE);
		}
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
