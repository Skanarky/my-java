package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Apple {
	
	// "res/greenap.png"
	// "res/redap.png"
	// "res/yellowap.png"
	ImageView theApple;
	Image image;
	
	public Apple(String imgPath) {
		// Creating image
		try {
			image = new Image(new FileInputStream(imgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Setting the image view
		theApple = new ImageView(image);
		
		// Setting the fit height and width of the image view
		theApple.setFitHeight((Main.height / 2));
		theApple.setFitWidth((Main.width / 2));
		
		// Setting the preserve ratio of the image view
		theApple.setPreserveRatio(true);
		
	}
	
    // Setting the position of the image
	public void setXPosition(int x) {
		theApple.setX(x);
	}
	
	public void setYPosition(int y) {
		theApple.setY(y);
	}

}
