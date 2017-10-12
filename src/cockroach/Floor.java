package cockroach;
			//class của các cột ở dưới 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Floor extends GameObject{
	public static final int WIDTH = 1080 ,HEIGHT = WIDTH/12*9;
	private Random r = new Random();
	public Floor(double x, double y, ID id, String a) {
		super(x, y, id, a);
		// TODO Auto-generated constructor stub
		velX=0;
		velY=0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x-=velX;
		y-=velY;
		if(x<-100) {
			x=200+900-60+r.nextInt(170)+400;
			//khi các cột đi về bên trái thì gán cho nó vị trí ở bên phải-> tạo hiệu ứng vô tận
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(76, 74, 72));			//render hình dạng của cột 
		g.fillRect((int)x, (int)y, 100, 800);
		g.setColor(Color.gray);
		g.fillRect((int)x+2, (int)y+2, 96, 796);
		
	}
	public Rectangle getBound() {
		  return new Rectangle((int)x, (int)y, 100, 400);		// hàm lấy thông tin hình dạng vật thể
		 }


}
