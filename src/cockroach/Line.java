package cockroach;

import java.awt.Graphics;
import java.awt.geom.Line2D;

public class Line extends GameObject {
	private Floor fl;

	public Line(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x = fl.getX();
		y = fl.getY();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void setFLoor(Floor fl) {
		this.fl = fl;
	}

	public Line2D getLine() {
		return new Line2D.Float(x, y + 20, x, y + 300);
	}

}
