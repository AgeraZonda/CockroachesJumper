package cockroach;
								// class cha của các vật thể trong game

import java.awt.Graphics;

public abstract class GameObject {
	protected int x,y;
	protected double velX,velY;


	public GameObject(int x, int y)
	{
		this.x=x;
		this.y=y;
		
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public void setX(int x) {
		this.x=x;
	}
	public int getX()
	{
		return x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getY()
	{
		return y;
	}
	public void setVelX(double velX)
	{
		this.velX=velX;
	}
	public double getVelX()
	{
		return velX;
	}
	public void setVelY(double velY)
	{
		this.velY=velY;
	}
	public double getVelY()
	{
		return velY;
	}
	
}
