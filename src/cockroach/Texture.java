package cockroach;

import java.awt.image.BufferedImage;

public class Texture {
	SpriteSheet bs,fs,ls;
	private BufferedImage ball_sheet = null;
	private BufferedImage floor_sheet = null;
	private BufferedImage landscape_sheet = null;
	public BufferedImage[] ball= new BufferedImage[2];
	public BufferedImage[] floor= new BufferedImage[2];
	public BufferedImage[] landscape = new BufferedImage[2];
	public Texture()
	{
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{ 
			landscape_sheet = loader.loadImage("/landscape.jpg");
			ball_sheet =loader.loadImage("/soccercutout.png");
			floor_sheet=loader.loadImage("/floorsheep.png");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		ls= new SpriteSheet(landscape_sheet);
		bs= new SpriteSheet(ball_sheet);
		fs = new SpriteSheet(floor_sheet);
	
		
		
		getTextures();
	}
	private void getTextures()
	{
		ball[0] = bs.grabImage(1, 1, 32, 32);
		ball[1] = bs.grabImage(2, 1, 41, 32);
		floor[0]=fs.grabImage(1, 1, 100, 300);
		landscape[0]=ls.grabImage(1, 1, 2448+2448, 810);
		landscape[1]=ls.grabImage(2, 1, 2448, 810);
		

	}

}
