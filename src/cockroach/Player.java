package cockroach;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	public static final int WIDTH = 1080 ,HEIGHT = WIDTH/12*9;
	Floor fl;
	

	public Player(double x, double y, ID id, String a) {
		super(x, y, id, a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	public void fl(Floor fl)
	{
		this.fl=fl;
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillOval((int)fl.getX()+32,(int)fl.getY()-100+68,32,32);
	}

}
