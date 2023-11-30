package engine;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import creatures.Cell;
import creatures.Herbivore;
import creatures.Organism;
import creatures.Plant;
import neuralNet.EvolutionaryNet;
import tools.Tools;

public class World
{

	
	
	public final int WIDTH = 800,HEIGHT = 800;
	
	private long simulationTime = 0;
	
	public ArrayList<Organism> organisms;
	Driver d;
	
	public Cell debugCell;
	
	int startingPlants = 50;
	
	public ArrayList<Organism> organismsToRemove = new ArrayList<Organism>();
	
	private boolean active = true;
	
	public World(Driver d)
	{
		this.d=d;
		organisms = new ArrayList<Organism>();
		
		Random rand = new Random(d.manager.seed);
		
		for(int i=0;i<startingPlants;i++)
		{
			organisms.add(new Plant(this,rand.nextInt(WIDTH),rand.nextInt(HEIGHT),20));
		}
		
		organisms.add(debugCell = new Herbivore(this,100,100,30));
	}
	
	public void reset(EvolutionaryNet net)
	{
		organisms.clear();
		simulationTime=0;
		
		Random rand = new Random(d.manager.seed);
		
		for(int i=0;i<startingPlants;i++)
		{
			organisms.add(new Plant(this,rand.nextInt(WIDTH),rand.nextInt(HEIGHT),20));
		}
		
		organisms.add(debugCell = new Herbivore(this,100,100,30,net));
	}
	
	public void update()
	{
		simulationTime++;
		
		organismsToRemove.clear();
		boolean anyActive = false;
		for(Organism o : organisms)
		{
			o.update();
			if(o.isActive())
			{
				anyActive = true;
			}
		}
				
		active = anyActive;
		
		for(Organism o : organismsToRemove)
		{
			organisms.remove(o);
		}
	}
	
	
	
	
	
	public void draw(Graphics2D g2d,boolean current)
	{
		if(active)
		{
			if(!current)
			{
				g2d = (Graphics2D) g2d.create();
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .2f));
			}
			
			
			
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(10));
			
			g2d.drawRect(-5, -5, WIDTH+10, HEIGHT+10);
			
			
			if(current)
			{
				for(Organism o : organisms)
				{
					o.draw(g2d);
				}
				
				if(d.drawBounds)
				{
					g2d.setColor(Color.BLACK);
					g2d.setStroke(new BasicStroke(2));
					for(Organism o : organisms)
					{
						Tools.drawRect(g2d, o.getBounds());
					}
				}			
			}
			else
			{
				for(Organism o : organisms)
				{
					if(o instanceof Cell)
					{
						o.draw(g2d);
					}
				}
			}
		}
		
	}
	
	public void draw(Graphics2D g2d)
	{
		draw(g2d,true);
	}
	
	public long getTime()
	{
		return simulationTime;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	
}
