package cockroach;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//class của thông báo gameover
public class GameOver extends GameObject {
	private Score sc;

	public GameOver(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	public void sc(Score sc) {
		this.sc = sc;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white); // vẽ các dòng chữ gameover
		g.setFont(new Font("Dialog", Font.PLAIN, 42));
		g.drawString("Game Over!!! Your Score :  " + Integer.toString(sc.getScore()), 200, 200);
		g.setColor(new Color (29, 145, 220));
		g.fillRect(350, 400, 100, 50);
		g.fillRect(550, 400, 100, 50);
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.PLAIN, 18));
		g.drawString("Retry", 380, 430);
		g.drawString("Quit", 580, 430);

	}

}
