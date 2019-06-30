package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Apple {
	
	Image image;
	
	public Apple(String imgPath) {

		try {
			image = new Image(new FileInputStream(imgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
