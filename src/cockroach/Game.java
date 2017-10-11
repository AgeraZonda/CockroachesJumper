package cockroach;
					//Class chính của game

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

public class Game extends Canvas implements Runnable {
//Khai báo biến
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1080 ,HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r = new Random();
	private static Game gm = new Game();
	private Floor fl1,fl2,fl3,fl4;
	private Player p;
	private KeyBoard keyboard;




	public Game()		//Hàm Constructor 
	{
		
		handler = new Handler();
		fl1=new Floor(200,HEIGHT-200,ID.Floor,"");
		fl2=new Floor(200+200-100+r.nextInt(200),HEIGHT-200,ID.Floor,"");		//Khai báo các cột để nhảy
		fl3=new Floor(200+600-100+r.nextInt(200),HEIGHT-200,ID.Floor,"");
		fl4=new Floor(200+900-100+r.nextInt(200),HEIGHT-200,ID.Floor,"");
		p= new Player((int)fl1.getX()+32,(int)fl1.getY()-100+68,ID.Player,"");		//Khai báo nhân vật của mình
		p.fl(fl1);
		handler.addObject(p);		//add các cột vào Handler
		handler.addObject(fl1);
		handler.addObject(fl2);
		handler.addObject(fl3);
		handler.addObject(fl4);
		keyboard = new KeyBoard(handler);		
		this.addKeyListener(keyboard);			//add sự kiện bàn phím
		
	
	}

	
	public synchronized void stop()		// Hàm dừng MutiThreading
	{
		try {
			thread.join();
			running = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void start()		// Hàm bắt đầu MultiThreading
	{
		thread = new Thread(this) ;
		thread.start();
		running = true;
	}
	public static void main(String args[])		//Hàm Main
	{
		
		new Window(WIDTH, HEIGHT,"Mirror",gm);
		
	}
	

	@Override
	public void run() 	//Hàm Run MultiThreading
	{
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns ;
			lastTime = now;
			while(delta>=1) {
				tick();
				updates++;
				delta--;
				
			}
			render();
			frames++;
			if(System.currentTimeMillis()- timer > 1000)
			{
				timer +=1000;
//				System.out.println("FPS: "+ frames + "TICKS: "+ updates);
				frames = 0;
				updates =0;
				
			}
		}
		
	}
	
	private void tick()		//Hàm xử Lý sau mỗi Khung Hình
	{
		
	
		
			handler.tick();		//xử lý các vật thể trên mỗi khung hình (bao gồm nhân vật và các cột )
			if(p.getVelY()!=0)
			{
				fl1.setVelX(7.6925);	//làm các cột di chuyển khi nhân vật nhảy 
				fl2.setVelX(7.6925);		
				fl3.setVelX(7.6925);
				fl4.setVelX(7.6925);
			}else if(p.getVelY()==0){
				fl1.setVelX(0);
				fl2.setVelX(0);
				fl3.setVelX(0);
				fl4.setVelX(0);
			}
			Rectangle a = p.getBound();
		if(a.intersects(fl1.getBound())==true || a.intersects(fl2.getBound())==true || a.intersects(fl3.getBound())==true || a.intersects(fl4.getBound())==true)		// hàm dừng nhân vật khi va chạm vào cột
				{
				p.setVelY(0);
				p.setY((int)fl1.getY()-100+68);
				fl1.setVelX(0);
				fl2.setVelX(0);
				fl3.setVelX(0);
				fl4.setVelX(0);
				}
			if(p.getY()>=(int)fl1.getY()-100+190)
			{
				p.setVelY(0);
				fl1.setVelX(0);
				fl2.setVelX(0);
				fl3.setVelX(0);
				fl4.setVelX(0);
				p.setVelY(0);
				p.setY((int)fl1.getY()-100+190);
			}
			
		
				
			
			
		
	}

	public void menurender(Graphics g)		//Hàm render menu game (chưa dùng )
	{
		g.setColor(Color.gray);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 48)); 
		g.drawString("Menu", 375, 80);
		
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
		g.drawString("Start", 205, 189);
		g.drawString("Help", 405, 189);
		g.drawString("Quit", 605, 189);
		
		g.drawRect(170,150,120,64);
		g.drawRect(370,150,120,64);
		g.drawRect(570,150,120,64);
		
	}
	private boolean mouseOver(int mx, int my, int x, int y, int width , int height)		// Hàm khoanh vùng click chuột
	{
		if(mx> x && mx<x+width)
		{
			if(my>y && my<y+height) {
				return true;
				
			}else return false;
		}else return false;
	}
	public static int clamp(int var, int min, int max)		// Hàm giới hạn minmax
	{
		if(var>=max)
			return var=max;
		if(var<=min)
			return var = min;
		else return var;
	}
	private void render()			//Hàm render hình ảnh game
	{

			
	
		
		
		BufferStrategy bs= this.getBufferStrategy();			//tạo BufferStrategy để render game
		if(bs ==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();		
			g.setColor(new Color(39, 40, 34));
			g.fillRect(0,0,WIDTH,HEIGHT);
			g.setColor(Color.gray);
			
			handler.render(g);	
		
			
			
			
			
		g.dispose();
		bs.show();
	}
}