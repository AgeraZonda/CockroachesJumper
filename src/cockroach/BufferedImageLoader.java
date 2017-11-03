package cockroach;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {	// class đọc file 
private BufferedImage image;
public BufferedImage loadImage(String path)
{
	try {
		image = ImageIO.read(getClass().getResource(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return image;
}
}
