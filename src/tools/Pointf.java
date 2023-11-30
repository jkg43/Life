package tools;

import java.awt.geom.Point2D;

public class Pointf extends Point2D
{

	public float x,y;
	
	public Pointf()
	{
		
	}
	
	public Pointf(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public Pointf(double x,double y)
	{
		this.x = (float)x;
		this.y = (float)y;
	}
	
	
	@Override
	public double getX()
	{
		return x;
	}

	@Override
	public double getY()
	{
		return y;
	}

	@Override
	public void setLocation(double x, double y)
	{
		this.x=(float)x;
		this.y=(float)y;
	}

}
