package cockroach;
//Class chính của game

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	// Khai báo biến
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;
	private int thisFl;
	private int Score = 0;
	private boolean running = false;
	private static Game gm = new Game();
	private Thread thread;
	private Handler handler;
	private Random r = new Random();
	private Floor fl1, fl2, fl3, fl4;
	private Player p;
	private KeyBoard keyboard;
	private Score sc;
	private GameOver go;
	private Floor currentFloor, nextFloor;
	private Line2D bottom;
	private Rectangle a;
	private Bar bar;
	private Line ln1, ln2, ln3, ln4;
	private int stage;

	public Game() // Hàm Constructor
	{
		stage = 1;
		keyboard = new KeyBoard();
		bar = new Bar(10, 10);
		bottom = new Line2D.Float(0, HEIGHT, WIDTH, HEIGHT);
		handler = new Handler();
		sc = new Score(0, 0);
		go = new GameOver(0, 0);
		go.sc(sc);
		fl1 = new Floor(200, HEIGHT - 200);
		fl2 = new Floor(200 + 400 - 85 + r.nextInt(170), HEIGHT - 200 - 85 + r.nextInt(170)); // Khai báo các cột để nhảy																		
		fl3 = new Floor(200 + 800 - 85 + r.nextInt(170), HEIGHT - 200 - 85 + r.nextInt(170));
		fl4 = new Floor(200 + 1200 - 85 + r.nextInt(170), HEIGHT - 200 - 85 + r.nextInt(170));
		p = new Player((int) fl1.getX() + 32, (int) fl1.getY() - 32); // Khai báo nhân vật của mình
		thisFl = 1;
		ln1 = new Line(fl1.getX(), fl1.getY());
		ln2 = new Line(fl2.getX(), fl2.getY());
		ln3 = new Line(fl3.getX(), fl3.getY());
		ln4 = new Line(fl4.getX(), fl4.getY());
		ln1.setFLoor(fl1);
		ln2.setFLoor(fl2);
		ln3.setFLoor(fl3);
		ln4.setFLoor(fl4);
		handler.addObject(p); // add các cột vào Handler
		handler.addObject(fl1);
		handler.addObject(fl2);
		handler.addObject(fl3);
		handler.addObject(fl4);
		handler.addObject(sc);
		handler.addObject(ln1);
		handler.addObject(ln2);
		handler.addObject(ln3);
		handler.addObject(ln4);
		bottom = new Line2D.Float(0, HEIGHT, WIDTH, HEIGHT);
		handler.addObject(bar);
		currentFloor = fl1;
		nextFloor = fl2;
		keyboard.setPlayer(p);
		// add sự kiện bàn phím

	}

	public synchronized void stop() // Hàm dừng MutiThreading
	{
		try {
			thread.join();
			running = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void start() // Hàm bắt đầu MultiThreading
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public static void main(String args[]) // Hàm Main
	{
		new Window(WIDTH, HEIGHT, "Mirror", gm);
	}

	@Override
	public void run() // Hàm Run MultiThreading
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
		}

	}

	private void tick() // Hàm xử Lý sau mỗi Khung Hình
	{
		
		if (stage == 2) {
			this.addKeyListener(keyboard);
			if (keyboard.getIsSpace())
				p.setVelY(p.getVelY() + 0.7);

			a = p.getBound();
			if (p.getVelY() != 0 && keyboard.getIsSpace() == true) {
				fl1.setVelX(7.6925); // làm các cột di chuyển khi nhân vật nhảy
				fl2.setVelX(7.6925);
				fl3.setVelX(7.6925);
				fl4.setVelX(7.6925);
			} else if (p.getVelY() == 0) {
				fl1.setVelX(0);
				fl2.setVelX(0);
				fl3.setVelX(0);
				fl4.setVelX(0);
			}
			handler.tick(); // xử lý các vật thể trên mỗi khung hình (bao gồm nhân vật và các cột )
			if (bottom.intersects(a) || ln1.getLine().intersects(a) || ln2.getLine().intersects(a)
					|| ln3.getLine().intersects(a) || ln4.getLine().intersects(a) || bar.getHeath() <= 1) {
				handler.addObject(go);
				handler.removeObject(p);
				handler.removeObject(fl1);
				handler.removeObject(fl2); // khi nhân vật chết xóa hết các vật thể và để lại mỗi dòng game over
				handler.removeObject(fl3);
				handler.removeObject(fl4);
				handler.removeObject(sc);
				handler.removeObject(bar);
				stage = 3;
			}
			if (p.getX() > nextFloor.getX() - 32 && keyboard.getIsSpace()) {
				sc.setScore(sc.getScore() + 1);// tăng 1 điểm mỗi khi nhảy đến cột
				thisFl++;
				if (thisFl % 4 == 1) {
					currentFloor = fl1;
					nextFloor = fl2;
				}
				if (thisFl % 4 == 2) {
					currentFloor = fl2;
					nextFloor = fl3;
				}
				if (thisFl % 4 == 3) {
					currentFloor = fl3;
					nextFloor = fl4;
				}
				if (thisFl % 4 == 0) {
					currentFloor = fl4;
					nextFloor = fl1;
				}
			}

			if ((p.getX() >= fl1.getX() && p.getX() <= fl1.getX() + 100 && p.getY() >= fl1.getY() - 32 && p.getVelY() > 0)
			 || (p.getX() >= fl2.getX() && p.getX() <= fl2.getX() + 100 && p.getY() >= fl2.getY() - 32 && p.getVelY() > 0)
			 || (p.getX() >= fl3.getX() && p.getX() <= fl3.getX() + 100 && p.getY() >= fl3.getY() - 32 && p.getVelY() > 0)
			 || (p.getX() >= fl4.getX() && p.getX() <= fl4.getX() + 100 && p.getY() >= fl4.getY() - 32 && p.getVelY() > 0)) // hàm dừng nhân vật khi va chạm vào cột
			{

				p.setVelY(0);
				if (keyboard.getIsSpace()) {
					bar.setHeath(100);
					keyboard.setIsSpace(false);
				}

			}

			if (p.getY() > currentFloor.getY() - 35 && keyboard.getIsSpace() == false) {
				p.setY(currentFloor.getY() - 32);
			} else {
				p.setX(p.getX() + (int) p.getVelX());
				p.setY(p.getY() + (int) p.getVelY());
			}
		}

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) // Hàm khoanh vùng click chuột
	{
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private void render() // Hàm render hình ảnh game
	{
		BufferStrategy bs = this.getBufferStrategy(); // tạo BufferStrategy để render game
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(39, 40, 34));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.gray);
		if (stage == 1) {
			g.setFont(new Font("Dialog", Font.PLAIN, 48));
			g.drawString("CockroachesJumper", 300, 80);
			g.setColor(Color.white);
			g.setFont(new Font("Dialog", Font.PLAIN, 24));
			g.drawString("Start", 205, 189);
			g.drawString("Help", 405, 189);
			g.drawString("Quit", 605, 189);
			g.drawRect(170, 150, 120, 64);
			g.drawRect(370, 150, 120, 64);
			g.drawRect(570, 150, 120, 64);
			g.setColor(Color.gray);
			addMouseListener(new MouseAdapter() // thêm sự kiện cho chuột
					{
						public void mousePressed(MouseEvent e) {
							int mx = e.getX();
							int my = e.getY();
							if (mouseOver(mx, my, 170, 150, 120, 64)) // chuột click ở trong vùng nút retry
							{
								stage = 2;

							}
							if (mouseOver(mx, my, 370, 150, 120, 64)) // chuột click ở trong vùng nút retry
							{
								stage = 4;

							}
							if (mouseOver(mx, my, 570, 150, 120, 64)) {
								System.exit(0);
							}
						}
					});
		
			
		}
		if (stage == 3) {
			addMouseListener(new MouseAdapter() // thêm sự kiện cho chuột
			{
				public void mousePressed(MouseEvent e) {
					int mx = e.getX();
					int my = e.getY();
					if (mouseOver(mx, my, 400, 400, 100, 50)) // chuột click ở trong vùng nút retry
					{
						keyboard = new KeyBoard();
						bar = new Bar(10, 10);
						bottom = new Line2D.Float(0, HEIGHT, WIDTH, HEIGHT);
						handler = new Handler();
						sc = new Score(0, 0);
						go = new GameOver(0, 0);
						go.sc(sc);
						fl1 = new Floor(200, HEIGHT - 200);
						fl2 = new Floor(200 + 400 - 85 + r.nextInt(170), HEIGHT - 200 - 85 + r.nextInt(170)); // Khai báo các cột để nhảy
						fl3 = new Floor(200 + 800 - 85 + r.nextInt(170), HEIGHT - 200 - 85 + r.nextInt(170));
						fl4 = new Floor(200 + 1200 - 85 + r.nextInt(170), HEIGHT - 200 - 85 + r.nextInt(170));
						p = new Player((int) fl1.getX() + 32, (int) fl1.getY() - 32); // Khai báo nhân vật của mình
						thisFl = 1;
						ln1 = new Line(fl1.getX(), fl1.getY());
						ln2 = new Line(fl2.getX(), fl2.getY());
						ln3 = new Line(fl3.getX(), fl3.getY());
						ln4 = new Line(fl4.getX(), fl4.getY());
						ln1.setFLoor(fl1);
						ln2.setFLoor(fl2);
						ln3.setFLoor(fl3);
						ln4.setFLoor(fl4);
						handler.addObject(p); // add các cột vào Handler
						handler.addObject(fl1);
						handler.addObject(fl2);
						handler.addObject(fl3);
						handler.addObject(fl4);
						handler.addObject(sc);
						handler.addObject(ln1);
						handler.addObject(ln2);
						handler.addObject(ln3);
						handler.addObject(ln4);
						bottom = new Line2D.Float(0, HEIGHT, WIDTH, HEIGHT);
						handler.addObject(bar);
						currentFloor = fl1;
						nextFloor = fl2;
						keyboard.setPlayer(p);
						gm.addKeyListener(keyboard); // add sự kiện bàn phím
						stage = 2;

					}
					if(mouseOver(mx,my,600, 400, 100, 50))
					{
						System.exit(0);
					}
				}
			});
		}

		if (stage == 4) {
			g.setColor(Color.white);
			g.setFont(new Font("Dialog", Font.PLAIN, 48));
			g.drawString("Hold Space To Jump..... Over =)))", 205, 189);
			g.setFont(new Font("Dialog", Font.PLAIN, 24));
			g.drawString("Back", 205 + 200, 189 + 200);
			g.drawRect(170 + 200, 150 + 200, 120, 64);
			g.setColor(Color.gray);
			addMouseListener(new MouseAdapter() // thêm sự kiện cho chuột
					{
						public void mousePressed(MouseEvent e) {
							int mx = e.getX();
							int my = e.getY();
							if (mouseOver(mx, my, 170 + 200, 150 + 200, 120, 64)) // chuột click ở trong vùng nút retry
							{
								stage = 1;

							}
						

						}
					});
		}

		if (stage == 3 || stage == 2)
			handler.render(g);
		g.dispose();
		g.dispose();
		bs.show();
	}

	public int getScore() {

		// TODO Auto-generated method stub
		return Score;
	}

}