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
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	// state
	static int speed = 5;
	static int foodColor = 0;
	static int width = 20;
	static int height = 20;
	static int foodX = 0;
	static int foodY = 0;
	static int bodyPartSize = 25;
	static List<BodyPart> theSnake = new ArrayList<>();
	static Direction direction = Direction.left;
	static boolean gameIsOver = false;
	static Random random = new Random();
	
	
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Canvas canvas = new Canvas(width * bodyPartSize, height * bodyPartSize);
			GraphicsContext grCtxt = canvas.getGraphicsContext2D();
			root.getChildren().add(canvas);
			
			// this is the flip book
			new AnimationTimer() {

				long lastTick = 0;

				public void handle(long now) {

					if (lastTick == 0) {

						lastTick = now;

						return;

					}

					// frames every speed second -> more frames the snake is faster
					if ((now - lastTick) > (1000000000 / speed)) {

						lastTick = now;

					}

				}

			}.start();
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
			
			foodColor = random.nextInt(5);
			++speed;
			break;

		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
