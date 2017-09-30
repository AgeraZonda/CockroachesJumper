package cockroach;


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

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1080 ,HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r = new Random();
	private static Game gm = new Game();
	private Floor fl;
	private Player p;

	




	public Game()
	{
		
	}

	
	public synchronized void stop()
	{
		try {
			thread.join();
			running = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void start()
	{
		thread = new Thread(this) ;
		thread.start();
		running = true;
	}
	public static void main(String args[])
	{
		
		new Window(WIDTH, HEIGHT,"Mirror",gm);
		
	}
	

	@Override
	public void run() 
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
	
	private void tick()
	{
		
		
		
			handler.tick();
			
			
		
	}

	public void menurender(Graphics g)
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
	private boolean mouseOver(int mx, int my, int x, int y, int width , int height)
	{
		if(mx> x && mx<x+width)
		{
			if(my>y && my<y+height) {
				return true;
				
			}else return false;
		}else return false;
	}
	public static int clamp(int var, int min, int max)
	{
		if(var>=max)
			return var=max;
		if(var<=min)
			return var = min;
		else return var;
	}
	private void render()
	{
		handler = new Handler();	
		fl=new Floor(200,HEIGHT-200,ID.Floor,"");
		p= new Player(0,0,ID.Player,"");
		p.fl(fl);
		handler.addObject(fl);
		handler.addObject(p);
		BufferStrategy bs= this.getBufferStrategy();
		if(bs ==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();		
			g.setColor(new Color(39, 40, 34));
			g.fillRect(0,0,WIDTH,HEIGHT);
			g.setColor(Color.gray);
			g.drawString("Nguyen quoc duy",100,100);
			handler.render(g);	
		
			
			
			
			
		g.dispose();
		bs.show();
	}
}