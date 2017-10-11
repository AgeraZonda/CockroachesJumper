package cockroach;
								// class cha của các vật thể trong game

import java.awt.Graphics;

public abstract class GameObject {
	protected double x,y;
	protected ID id;
	protected double velX,velY;
	protected double radian;
	String a;

	public GameObject(double x, double y,ID id, String a)
	{
		this.x=x;
		this.y=y;
		this.id=id;
		this.a=a;
		
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public void setX(double x) {
		this.x=x;
	}
	public double getRadian()
	{
		return radian;
	}
	
	public double getX()
	{
		return x;
	}
	public void setY(double y) {
		this.y=y;
	}
	public double getY()
	{
		return y;
	}
	public void setId(ID id)
	{
		this.id=id;
	}
	public ID getId()
	{
		return id;
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
