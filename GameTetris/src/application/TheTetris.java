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
