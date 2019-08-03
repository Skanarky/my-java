package application;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	
	// main state
	static double speed = 4;
	static int score = -1;
	static int appleType = 1;
	static int width = 20;
	static int height = 20;

	// food
	static Apple greenApple = new Apple("res/greenapSm.png");
	static Apple redApple = new Apple("res/redapSm.png");
	static Apple yellowApple = new Apple("res/yellowapSm.png");
	static int foodX = 0;
	static int foodY = 0;

	// snake and controls
	static int bodyPartSize = 25; // not really body part size... more like corner size
	static List<BodyPart> theSnake = new ArrayList<>();
	static Direction direction = Direction.left;
	static boolean gameIsOver = false;
	static boolean gameIsPaused = false;
	static boolean gameIsAboutToStart = true;
	static Random random = new Random();
	
	
	public void start(Stage primaryStage) {
		try {
			makeFood();

			VBox root = new VBox();
			Canvas canvas = new Canvas(width * bodyPartSize, height * bodyPartSize);
			GraphicsContext grCtxt = canvas.getGraphicsContext2D();
			root.getChildren().add(canvas);
			
			// the AnimationTimer is like flip book
			new AnimationTimer() {

				long lastTick = 0;

				public void handle(long now) {

					if (lastTick == 0) {

						lastTick = now;
						tick(grCtxt);

						return;

					}

					// frames every speed second -> more frames the snake is faster
					if ((now - lastTick) > (1750000000 / speed)) {

						lastTick = now;
						tick(grCtxt);

					}

				}

			}.start();

			// scene
			Scene scene = new Scene(root, (width * bodyPartSize), (height * bodyPartSize));

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// key events
			scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

				// start game
				if (key.getCode() == KeyCode.ENTER && gameIsAboutToStart) {
					gameIsAboutToStart = false;
				}
				// pause
				if (key.getCode() == KeyCode.SPACE) {
					gameIsPaused = !gameIsPaused;
				}

				// quit game
				if (key.getCode() == KeyCode.ESCAPE) {
					System.exit(1);
				}

				// controls - I, J, K, L
				if (checkBorders()) {
					
					if (key.getCode() == KeyCode.I) {
						if (direction != Direction.down)
							direction = Direction.up;
					}
					if (key.getCode() == KeyCode.J) {
						if (direction != Direction.right)
							direction = Direction.left;
						
					}
					if (key.getCode() == KeyCode.K) {
						if (direction != Direction.up)
							direction = Direction.down;
					}
					if (key.getCode() == KeyCode.L) {
						if (direction != Direction.left)
							direction = Direction.right;
					}
					
				}
				
			});
			
			// initial creation of the snake - 3 body parts
			theSnake.add(new BodyPart((width / 2), (height / 2)));
			theSnake.add(new BodyPart((width / 2), (height / 2)));
			theSnake.add(new BodyPart((width / 2), (height / 2)));

			primaryStage.setScene(scene);
			primaryStage.setTitle("GOOD OL' SNAKE GAME");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void tick(GraphicsContext gc) {

		if (gameIsOver) {
			gc.setFill(Color.MAROON);
			gc.setFont(new Font("", 50));
			gc.fillText("GAME OVER", 100, 250);
			
			gc.setFont(new Font("", 35));
			gc.fillText("SCORE: " + score, 170, 350);
			return;
		}
		
		if (gameIsAboutToStart) {
			
			gc.setFill(Color.DARKSEAGREEN);
			gc.setFont(new Font("", 35));
			gc.fillText("PRESS 'ENTER' TO START", 33, 230);
			
			gc.setFill(Color.ORANGERED);
			gc.setFont(new Font("", 20));
			gc.fillText("USE 'I', 'J', 'K', 'L' FOR CONTROLS", 90, 260);
			gc.fillText("'SPACE' TO PAUSE GAME", 130, 290);
			gc.fillText("'ESCAPE' TO QUIT GAME", 133, 320);
			
		} else {
			
			if (gameIsPaused) {

				gc.setFill(Color.CORNFLOWERBLUE);
				gc.setFont(new Font("", 90));
				gc.fillText("PAUSE", 100, 260);
				
			} else {
				
				for (int i = (theSnake.size() - 1); i >= 1; --i) {
					
					theSnake.get(i).x = theSnake.get(i - 1).x;
					theSnake.get(i).y = theSnake.get(i - 1).y;
					
				}
				
				// the game will NOT be over if the snake touches the border
				switch (direction) {
				
					case up:
						--theSnake.get(0).y;
						if (theSnake.get(0).y < 0) {
							theSnake.get(0).y = height;
//							gameIsOver = true;
						}
						break;
					case down:
						++theSnake.get(0).y;
						if (theSnake.get(0).y > (height - 1)) {
							theSnake.get(0).y = 0;
//							gameIsOver = true;
						}
						break;
					case left:
						--theSnake.get(0).x;
						if (theSnake.get(0).x < 0) {
							theSnake.get(0).x = width;
//							gameIsOver = true;
						}
						break;
					case right:
						++theSnake.get(0).x;
						if (theSnake.get(0).x > (width - 1)) {
							theSnake.get(0).x = 0;
//							gameIsOver = true;
						}
						break;

				}

				// eating food
				if (foodX == theSnake.get(0).x && foodY == theSnake.get(0).y) {
					
					theSnake.add(new BodyPart(-1, -1));
					makeFood();

				}
				
				// self snake collision
				for (int i = 1; i < theSnake.size(); ++i) {
					
					if (theSnake.get(0).x == theSnake.get(i).x && theSnake.get(0).y == theSnake.get(i).y ) {
						gameIsOver = true;
					}
					
				}
				
				// fill the background
				gc.setFill(Color.FLORALWHITE);
				gc.fillRect(0, 0, (width * bodyPartSize), (height * bodyPartSize));
				
				// set food/Apple
				switch (appleType) {
				
					case 1:
						gc.drawImage(greenApple.image, (foodX * bodyPartSize), (foodY * bodyPartSize), bodyPartSize, bodyPartSize);
						break;
					case 2:
						gc.drawImage(redApple.image, (foodX * bodyPartSize), (foodY * bodyPartSize), bodyPartSize, bodyPartSize);
						break;
					case 3:
						gc.drawImage(yellowApple.image, (foodX * bodyPartSize), (foodY * bodyPartSize), bodyPartSize, bodyPartSize);
						break;
				}

				// show the snake (2 colors - shadow and foreground)
				for (BodyPart bp : theSnake) {
					
					// shadow
					gc.setFill(Color.OLIVEDRAB);
					gc.fillRect((bp.x * bodyPartSize), (bp.y * bodyPartSize), (bodyPartSize), (bodyPartSize));

					// foreground
					gc.setFill(Color.DARKOLIVEGREEN);
					gc.fillRect((bp.x * bodyPartSize), (bp.y * bodyPartSize), (bodyPartSize - 2), (bodyPartSize - 2));
					
				}
				
				// show score
				gc.setFill(Color.BURLYWOOD);
				gc.setFont(new Font("", 20));
				gc.fillText("Score: " + score, 10, 30);
				
			}
			
		}
		
	}
	
	public static boolean checkBorders() {
		if (theSnake.get(0).y > 0 && theSnake.get(0).y < height && theSnake.get(0).x > 0 && theSnake.get(0).x < width)
			return true;
		return false;
	}
	
	public static void makeFood() {
		
		start: while(true) {

			foodX = random.nextInt(width);
			foodY = random.nextInt(height);
			
			for(BodyPart bp : theSnake) {
				if (bp.x == foodX && bp.y == foodY) {
					continue start;
				}
			}

			if (appleType < 3) {
				++appleType;
			} else {
				appleType = 1;
			}

			++score;
			speed += .25;

			break;

		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
