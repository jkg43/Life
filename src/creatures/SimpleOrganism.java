package creatures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.World;
import tools.Pointf;

public class SimpleOrganism extends Organism
{
	
	
	private Color color;
	
	private int size;
	
	private static int speed = 10;

	public SimpleOrganism(World w,Color c,int size)
	{
		super(w,size,size);
		color = c;
		this.size=size;
	}

	
	
	
	@Override
	public void update()
	{
		x+=speed;
		y+=speed;
		if(x+size/2>w.WIDTH || y+size/2>w.HEIGHT || x<size/2 || y<size/2)
		{
			speed = -speed;
		}
	}

	
	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(color);
		g2d.fillOval((int)(x-size/2), (int)(y-size/2), size, size);
		
	}




	public Color getColor()
	{
		return color;
	}




	public void setColor(Color color)
	{
		this.color = color;
	}




	public int getSize()
	{
		return size;
	}




	public void setSize(int size)
	{
		this.size = size;
	}




	@Override
	public Rectangle getBounds()
	{
		return null;
	}




	@Override
	public Pointf getCenter()
	{
		return null;
	}
	
	
	
	
	
	


}
