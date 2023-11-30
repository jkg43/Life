package creatureComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import creatures.Organism;
import engine.World;
import tools.Pointf;

public class Sensor
{

	
	private Class<?> target;
	
	protected float range;
	protected double relativeAngle;
	protected Organism base;
	protected World w;
	
	protected boolean detected=false;
	
	
	public Sensor(World w,Organism o,float r,double a,Class<?> t)
	{
		this.w=w;
		this.base=o;
		this.range = r;
		this.relativeAngle = a;
		this.target=t;
	}
	
	
	public void update()
	{
		detected = false;
		
		Pointf c = base.getCenter();
		Pointf end = getEndPoint();
		
		for(Organism o : w.organisms)
		{
			if(o!=base && o.getClass().isAssignableFrom(target))
			{
				if(o.getBounds().intersectsLine(c.x, c.y,end.x,end.y))
				{
					detected = true;
				}
			}
		}
	}

	
	
	public void draw(Graphics2D g2d)
	{
		if(detected)
		{
			g2d.setColor(Color.RED);
		}
		else
		{
			g2d.setColor(Color.BLUE);			
		}
		
		Pointf c = base.getCenter();
		
		Pointf end = getEndPoint();
		
		g2d.setStroke(new BasicStroke(3));
		
		g2d.drawLine((int)c.x, (int)c.y, (int)end.x, (int)end.y);
		
		
	}
	
	
	protected Pointf getEndPoint()
	{
		Pointf c = base.getCenter();
		double realAngle = base.getAngle()+relativeAngle;
		
		return new Pointf(c.x+range*Math.cos(realAngle), c.y+range*Math.sin(realAngle));
	}
	
	public boolean objectDetected()
	{
		return detected;
	}
	
	
	
}
