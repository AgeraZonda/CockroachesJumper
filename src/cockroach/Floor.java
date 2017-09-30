package cockroach;

import java.awt.Color;
import java.awt.Graphics;

public class Floor extends GameObject{
	public static final int WIDTH = 1080 ,HEIGHT = WIDTH/12*9;

	public Floor(double x, double y, ID id, String a) {
		super(x, y, id, a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 100, 400);
		
	}

}
