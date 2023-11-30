package creatures;

import java.awt.Color;
import java.awt.Graphics2D;

import creatureComponents.Sensor;
import engine.World;
import neuralNet.EvolutionaryNet;

public class Herbivore extends Cell
{

	
	public static String[] inputNames = {"Forward Sensor","Right Sensor","Left Sensor","Right Mid Sensor",
			"Left Mid Sensor","Age","Energy"};
	
	public static String[] outputNames = {"Move Forward","Turn Left","Turn Right"};
	
	
	private boolean nearOtherHerbivores=false;
	
	
	private static int numInputs = 7;
	
	
	public Herbivore(World w, float x, float y, float size,EvolutionaryNet n)
	{
		super(w,x,y,size,5,5,n);
		init();
		
	}

	public Herbivore(World w,float x,float y,float size)
	{
		super(w,x,y,size,5,5,new EvolutionaryNet(numInputs, 3, numInputs+1));
		init();
	}
	
	private void init()
	{
		int l = 200;
		
		sensors[0] = new Sensor(w, this, l, 0,Plant.class);
		sensors[1] = new Sensor(w, this, l, Math.PI/4,Plant.class);
		sensors[2] = new Sensor(w, this, l, -Math.PI/4,Plant.class);
		sensors[3] = new Sensor(w, this, l, Math.PI/8,Plant.class);
		sensors[4] = new Sensor(w, this, l, -Math.PI/8,Plant.class);
	}
	
	@Override
	public double[] getInputs()
	{
		double[] inputs = new double[numInputs];
		
		for(int i=0;i<sensors.length;i++)
		{
			inputs[i] = sensors[i].objectDetected() ? 1.0 : 0.0;
		}
		inputs[5] = (double)getAge()/1000d;
		
		inputs[6] = (double)energy/500d;
		
		return inputs;
	}
	
	
	@Override
	public void update()
	{
		super.update();
		
//		nearOtherHerbivores = false;
		for(Organism o : w.organisms)
		{
			if(o instanceof Plant)
			{
				if(o.getBounds().intersects(getBounds()))
				{
					w.organismsToRemove.add(o);
					Plant p = (Plant)o;
					energy += p.getEnergy();
				}
			}
//			if(o != this && o instanceof Herbivore)
//			{
//				if(Tools.checkDistances(x,y,o.x,o.y,30))
//				{
//					nearOtherHerbivores = true;
//				}
//			}
		}
//		if(nearOtherHerbivores)
//		{
//			speed = 8;
//		}
//		else
//		{
//			speed = 5;
//		}
//		

		
	}

	
	@Override
	public void draw(Graphics2D g2d)
	{
		for(Sensor s : sensors)
		{
			s.draw(g2d);
		}
		g2d.setColor(new Color(0,75,0));
		g2d.fillOval((int)x, (int)y, (int)size, (int)size);
	}
	
	

	public boolean isNearOtherHerbivores()
	{
		return nearOtherHerbivores;
	}
	
	
	
	
	
}
