package cockroach;

//class Quả bóng ta điều khiển
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
public int type;
BufferedImageLoader loader = new BufferedImageLoader();
Texture tex = Game.getInstance();
	public Player(int x, int y,int type) {
		super(x, y);
		// TODO Auto-generated constructor stub
		velX = 0;
		velY = 0;
		this.type=type;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	
if(type>1)type=1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		if(type==0)g.drawImage(tex.ball[type],(int)x,(int)y, null);// vẽ hình dạng cho nhân vật
		if(type==1)g.drawImage(tex.ball[type],(int)x-4,(int)y, null);// vẽ hình dạng cho nhân vật
		
	}

	public Rectangle getBound() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	public void setType(int type)
	{
		this.type=type;
	}


}
