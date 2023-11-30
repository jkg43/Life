package creatures;

import java.awt.Color;
import java.awt.Graphics2D;

import creatureComponents.Sensor;
import engine.World;
import tools.Tools;

public class Carnivore extends Cell
{

	private boolean nearOtherCarnivores=false;
	
	
	public Carnivore(World w, float x, float y, float size)
	{
		super(w, x, y, size);
		
		sensors[0] = new Sensor(w, this, 100, 0,Cell.class);
		sensors[1] = new Sensor(w, this, 100, Math.PI/6,Cell.class);
		sensors[2] = new Sensor(w, this, 100, -Math.PI/6,Cell.class);
		
	}
	
	
	
	
	
	@Override
	public void update()
	{
		super.update();
		
		for(Organism o : w.organisms)
		{
			if(o instanceof Herbivore)
			{
				if(o.getBounds().intersects(getBounds()))
				{
					w.organismsToRemove.add(o);
					energy += 100;
				}
			}
			if(o != this && o instanceof Carnivore)
			{
				if(Tools.checkDistances(x,y,o.x,o.y,30))
				{
					nearOtherCarnivores = true;
				}
			}
		}
		if(nearOtherCarnivores)
		{
			speed = 8;
		}
		else
		{
			speed = 5;
		}
	}


	@Override
	public void draw(Graphics2D g2d)
	{
		for(Sensor s : sensors)
		{
			s.draw(g2d);
		}
		g2d.setColor(new Color(200,50,50));
		g2d.fillOval((int)x, (int)y, (int)size, (int)size);
	}


	public boolean isNearOtherCarnivores()
	{
		return nearOtherCarnivores;
	}
	
	
	
	
	
	
	
	

}
