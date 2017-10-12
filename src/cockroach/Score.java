package cockroach;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
				//class cho chữ score đếm điểm
public class Score extends GameObject{
private int Score=0;
	public Score(double x, double y, ID id, String a) {
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
		g.setColor(Color.gray);
		g.setFont(new Font("Dialog", Font.PLAIN, 42)); 
		g.drawString("Score: "+Integer.toString(Score), 100, 100);
		g.setColor(Color.gray);
		
	}
	public int getScore()
	{
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}

}
