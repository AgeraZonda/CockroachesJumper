package cockroach;

//class của các cột ở dưới 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Floor extends GameObject {
	public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;
	private Random r = new Random();
	Texture tex = Game.getInstance();
	public Floor(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		velX = 0;
		velY = 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x -= velX;
		if (x < -100) {
			x = 200 + 1600 - 85 + r.nextInt(170);
			y = HEIGHT - 200 - 85 + r.nextInt(170);
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(tex.floor[0],(int)x,(int)y-10,null);

	}

	public Rectangle getBound() {
		return new Rectangle((int) x, (int) y, 100, 400); // hàm lấy thông tin hình dạng vật thể
	}

}
