package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	private static boolean gameOn = true;
	private static Form nextObject = Controller.makeRect();
	private static int linesNo = 0;


	@Override
	public void start(Stage arg0) throws Exception {

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

}
