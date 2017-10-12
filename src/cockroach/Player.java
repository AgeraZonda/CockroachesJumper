package cockroach;
					//class nhân vật ta điều khiển
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	public static final int WIDTH = 1080 ,HEIGHT = WIDTH/12*9;
	Floor fl;
	

	public Player(double x, double y, ID id, String a) {
		super(x, y, id, a);
		// TODO Auto-generated constructor stub
		velX=0;
		velY=0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(velY>=-30 && velY<0)velY+=0.7;				//tạo hiệu ứng trọng lực cho vật thể
		if(velY>=0 && y!=(int)fl.getY()-100+68)velY+=0.7;
		y+=velY;
		x+=velX;
		
		
	}
	public void fl(Floor fl)			//gán floor
	{
		this.fl=fl;
		
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
