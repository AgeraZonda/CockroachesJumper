package cockroach;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
			//class của thông báo gameover
public  class GameOver extends GameObject{
private Score sc ;
	public GameOver(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	public void sc(Score sc)
	{
		this.sc=sc;
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.gray);			//vẽ các dòng chữ gameover
		g.setFont(new Font("Dialog", Font.PLAIN, 42)); 
		g.drawString("Game Over!!! Your Score :  "+Integer.toString(sc.getScore()), 200, 200);
		g.setColor(Color.gray);
		g.fillRect(400, 400, 100, 50);
		g.fillRect(600, 400, 100, 50);
		g.setColor(new Color(39, 40, 34));
		g.setFont(new Font("Dialog", Font.PLAIN, 18)); 
		g.drawString("Retry",430,430);
		g.drawString("Quit",630,430);
		
		
	}

}
