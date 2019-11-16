package application;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
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

	private static Pane groupe = new Pane();
	private static Form object;
	private static Scene scene = new Scene(groupe, (MAXX + 150), MAXY);

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
		
		groupe.getChildren().addAll(line, theScore, theLevel);
		
		// first block and game stage
		Form a = nextObject;
		groupe.getChildren().addAll(a.a, a.b, a.c, a.d);
		
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
							
							groupe.getChildren().add(gOver);
							
							gameOn = false;
						}
						
						// exit
						if (top == 15) {
							System.exit(0);
						}
						
						// game 
						if (gameOn) {
							MoveDown(object);
							theScore.setText("SCORE: " + Integer.toString(score));
							theLevel.setText("LEVEL: " + Integer.toString(linesNo));
							
						}
					}
				});
			}
		};
		
		theFall.schedule(tTask, 0, 300);

//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}

	}
	
	private void moveOnKeyPress(Form theFormInp) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ev) {
				switch (ev.getCode()) {
					case RIGHT:
						Controller.moveRight(theFormInp);
						break;
					case DOWN:
						MoveDown(theFormInp);
						score++;
						break;
					case LEFT:
						Controller.moveLeft(theFormInp);
						break;
					case UP:
						MoveTurn(theFormInp);
						break;
					default:
						return;
				}
			}
		});
	}
	
	private void MoveTurn(Form theFormInp) {
		int theF = theFormInp.form;
		Rectangle a = theFormInp.a;
		Rectangle b = theFormInp.b;
		Rectangle c = theFormInp.c;
		Rectangle d = theFormInp.d;
		
		switch (theFormInp.getName()) {
			case "j":
				if (theF == 1 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.d);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.d);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.d);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, 1, -1) && checkRect(c, -1, -1) && checkRect(d, 2, -2)) {
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.d);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
			case "l":
				if (theF == 1 && checkRect(a, 1, -1) && checkRect(c, 1, 1) && checkRect(b, 2, 2)) {
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveUp(theFormInp.c);
					MoveRight(theFormInp.c);
					MoveUp(theFormInp.b);
					MoveUp(theFormInp.b);
					MoveRight(theFormInp.b);
					MoveRight(theFormInp.b);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, -1, -1) && checkRect(b, 2, -2) && checkRect(c, 1, -1)) {
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveRight(theFormInp.b);
					MoveRight(theFormInp.b);
					MoveDown(theFormInp.b);
					MoveDown(theFormInp.b);
					MoveRight(theFormInp.c);
					MoveDown(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, -1, 1) && checkRect(c, -1, -1) && checkRect(b, -2, -2)) {
					MoveLeft(theFormInp.a);
					MoveUp(theFormInp.a);
					MoveDown(theFormInp.c);
					MoveLeft(theFormInp.c);
					MoveDown(theFormInp.b);
					MoveDown(theFormInp.b);
					MoveLeft(theFormInp.b);
					MoveLeft(theFormInp.b);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, 1, 1) && checkRect(b, -2, 2) && checkRect(c, -1, 1)) {
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveLeft(theFormInp.b);
					MoveLeft(theFormInp.b);
					MoveUp(theFormInp.b);
					MoveUp(theFormInp.b);
					MoveLeft(theFormInp.c);
					MoveUp(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				break;
			case "o":
				break;
			case "s":
				if (theF == 1 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, 0, 2)) {
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveLeft(theFormInp.c);
					MoveUp(theFormInp.c);
					MoveUp(theFormInp.d);
					MoveUp(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 0, -2)) {
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveRight(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, -1, -1) && checkRect(c, -1, 1) && checkRect(d, 0, 2)) {
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveLeft(theFormInp.c);
					MoveUp(theFormInp.c);
					MoveUp(theFormInp.d);
					MoveUp(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, 1, 1) && checkRect(c, 1, -1) && checkRect(d, 0, -2)) {
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveRight(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
			case "t":
				if (theF == 1 && checkRect(a, 1, 1) && checkRect(d, -1, -1) && checkRect(c, -1, 1)) {
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.d);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.c);
					MoveUp(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, 1, -1) && checkRect(d, -1, 1) && checkRect(c, 1, 1)) {
					MoveRight(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.d);
					MoveUp(theFormInp.d);
					MoveUp(theFormInp.c);
					MoveRight(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, -1, -1) && checkRect(d, 1, 1) && checkRect(c, 1, -1)) {
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveUp(theFormInp.d);
					MoveRight(theFormInp.d);
					MoveRight(theFormInp.c);
					MoveDown(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, -1, 1) && checkRect(d, 1, -1) && checkRect(c, -1, -1)) {
					MoveLeft(theFormInp.a);
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.d);
					MoveDown(theFormInp.d);
					MoveDown(theFormInp.c);
					MoveLeft(theFormInp.c);
					theFormInp.changeForm();
					break;
				}
				break;
			case "z":
				if (theF == 1 && checkRect(b, 1, 1) && checkRect(c, -1, 1) && checkRect(d, -2, 0)) {
					MoveUp(theFormInp.b);
					MoveRight(theFormInp.b);
					MoveLeft(theFormInp.c);
					MoveUp(theFormInp.c);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(b, -1, -1) && checkRect(c, 1, -1) && checkRect(d, 2, 0)) {
					MoveDown(theFormInp.b);
					MoveLeft(theFormInp.b);
					MoveRight(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveRight(theFormInp.d);
					MoveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(b, 1, 1) && checkRect(c, -1, 1) && checkRect(d, -2, 0)) {
					MoveUp(theFormInp.b);
					MoveRight(theFormInp.b);
					MoveLeft(theFormInp.c);
					MoveUp(theFormInp.c);
					MoveLeft(theFormInp.d);
					MoveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(b, -1, -1) && checkRect(c, 1, -1) && checkRect(d, 2, 0)) {
					MoveDown(theFormInp.b);
					MoveLeft(theFormInp.b);
					MoveRight(theFormInp.c);
					MoveDown(theFormInp.c);
					MoveRight(theFormInp.d);
					MoveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
			case "i":
				if (theF == 1 && checkRect(a, 2, 2) && checkRect(b, 1, 1) && checkRect(d, -1, -1)) {
					MoveUp(theFormInp.a);
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveUp(theFormInp.b);
					MoveRight(theFormInp.b);
					MoveDown(theFormInp.d);
					MoveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 2 && checkRect(a, -2, -2) && checkRect(b, -1, -1) && checkRect(d, 1, 1)) {
					MoveDown(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveDown(theFormInp.b);
					MoveLeft(theFormInp.b);
					MoveUp(theFormInp.d);
					MoveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 3 && checkRect(a, 2, 2) && checkRect(b, 1, 1) && checkRect(d, -1, -1)) {
					MoveUp(theFormInp.a);
					MoveUp(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveRight(theFormInp.a);
					MoveUp(theFormInp.b);
					MoveRight(theFormInp.b);
					MoveDown(theFormInp.d);
					MoveLeft(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				if (theF == 4 && checkRect(a, -2, -2) && checkRect(b, -1, -1) && checkRect(d, 1, 1)) {
					MoveDown(theFormInp.a);
					MoveDown(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveLeft(theFormInp.a);
					MoveDown(theFormInp.b);
					MoveLeft(theFormInp.b);
					MoveUp(theFormInp.d);
					MoveRight(theFormInp.d);
					theFormInp.changeForm();
					break;
				}
				break;
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
