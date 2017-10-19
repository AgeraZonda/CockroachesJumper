package cockroach;

import java.awt.Graphics;


public class LandScape extends GameObject{
	public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;

	Texture tex = Game.getInstance();
	public LandScape(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(tex.landscape[0],x,y,null);
	}
	

}
