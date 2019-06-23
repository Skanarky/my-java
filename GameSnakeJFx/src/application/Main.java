package application;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
