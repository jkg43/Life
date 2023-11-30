package creatures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.World;
import tools.Pointf;

public class Plant extends Organism
{

	//how big the plant is
	private float size;
	
	//how much energy the plant gives when it dies
	private int energy = 35;
	
	public Plant(World w, float x, float y,float s)
	{
		super(w, x, y);
		this.size=s;
		active = false;
	}

	
	
	
	
	
	@Override
	public void update()
	{
		checkBoundsAndCorrect();
		if(w.getTime()%100==0)
		{
			size++;
			energy+=3;
		}
	}
	
	

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.GREEN);
		g2d.fillOval((int)x, (int)y, (int)size, (int)size);
	}


	



	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int)(x),(int)(y),(int)size,(int)size);
	}






	@Override
	public Pointf getCenter()
	{
		return new Pointf(x+size/2,y+size/2);
	}

	
	
	public int getEnergy()
	{
		return energy;
	}


	public float getSize()
	{
		return size;
	}
	
	
	
	
	
	
	
}
