package cockroach;

import java.awt.Graphics;

public class Bar extends GameObject {
	private double Heath;
	

	public Bar(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		Heath = 100;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if (Heath > 0)
			Heath -= 0.4;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect(x, y, (int) Heath * 10, 10);
	}

	public double getHeath() {
		return Heath;
	}

	public void setHeath(double Heath) {
		this.Heath = Heath;
	}
}
