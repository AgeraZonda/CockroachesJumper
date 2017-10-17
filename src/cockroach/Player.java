package cockroach;
					//class nhân vật ta điều khiển
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	
	

	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		velX=0;
		velY=0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
			//tạo hiệu ứng trọng lực cho vật thể
	
	
		
		
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillOval((int)x,(int)y,32,32);			//vẽ hình dạng cho nhân vật
	}
	 public Rectangle getBound() {
		  return new Rectangle((int)x, (int)y, 32, 32);
		 }


}
