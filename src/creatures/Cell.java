package creatures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import creatureComponents.Sensor;
import engine.World;
import neuralNet.EvolutionaryNet;
import tools.Pointf;

public class Cell extends Organism
{

	
	
	
	//inputs
	static final int FRONT_SENSOR = 0,RIGHT_SENSOR=1,LEFT_SENSOR=2;
	
	
	//outputs
	public static final int MOVE_FORWARD=0,TURN_LEFT=1,TURN_RIGHT=2;
	

	
	
	protected float size,speed;
	
	public double energy = 100;
	
	protected Sensor[] sensors;
	
	private EvolutionaryNet net;
	
	private long deathTime = -1;
	
	public Cell(World w, float x, float y,float size,float speed,int numSensors,EvolutionaryNet n)
	{
		super(w, x, y);
		this.size=size;
		this.speed=speed;
		
		sensors = new Sensor[numSensors];
		
		net = n;
		
	}
	
	public Cell(World w,float x,float y,float size)
	{
		this(w,x,y,size,5,3,new EvolutionaryNet(3,3,4));
	}

	public Cell(World w,float x,float y,float size,int numSensors)
	{
		this(w,x,y,size,5,numSensors,new EvolutionaryNet(numSensors,3,numSensors+1));
	}

	
	//processes the cell's environment given its inputs
	private void processEnvironment()
	{
		//the 3 forward facing sensors
		double[] inputs = getInputs();
		
		//move forward,turn left,turn right
		double[] outputs = new double[3];
		

		
		
//		calculateActionsManual(inputs, outputs);
		outputs = netCalcActions(inputs);
		
		performActions(outputs);
		
	}
	
	public double[] getInputs()
	{
		double[] inputs = new double[sensors.length];
		
		for(int i=0;i<sensors.length;i++)
		{
			inputs[i] = sensors[i].objectDetected() ? 1.0 : 0.0;
		}
		
		return inputs;
	}
	
	
	private double[] netCalcActions(double[] inputs)
	{
		return net.processInputs(inputs);
	}
	
//	private void calculateActionsManual(double[] inputs,double[] outputs)
//	{
//		outputs[MOVE_FORWARD] = 1.0;
//		if(inputs[LEFT_SENSOR]>0)
//		{
//			outputs[TURN_LEFT]=1.0;
//		}
//		if(inputs[RIGHT_SENSOR]>0)
//		{
//			outputs[TURN_RIGHT]=1.0;
//		}
//		if(inputs[FRONT_SENSOR]>0)
//		{
//			outputs[TURN_LEFT]=0;
//			outputs[TURN_RIGHT]=0;
//		}
//		
//		if(inputs[FRONT_SENSOR]==0 && inputs[LEFT_SENSOR]==0 && inputs[RIGHT_SENSOR]==0)
//		{
//			outputs[TURN_RIGHT]=0.3;
//		}
//	}
	
	
	
	
	
	private void performActions(double[] outputs)
	{
		
		angle += 0.2 * outputs[TURN_RIGHT];
		angle -= 0.2 * outputs[TURN_LEFT];
		
		
		x+= outputs[MOVE_FORWARD]*speed*Math.cos(angle);
		y+= outputs[MOVE_FORWARD]*speed*Math.sin(angle);
		
		energy -= (outputs[MOVE_FORWARD]/2 + outputs[TURN_RIGHT]/4 + outputs[TURN_LEFT]/4 + 0.5);
		
	}
	
	
	
	
	
	
	
	@Override
	public void update()
	{
		
		
		for(Sensor s : sensors)
		{
			s.update();
		}
		
		
		
		if(active)
		{
			processEnvironment();
		}

		if(energy<=0 && active)
		{
			active=false;
			deathTime = w.getTime();
		}
		
		checkBoundsAndCorrect();
		
		
	}
	
	
	@Override
	public void draw(Graphics2D g2d)
	{
		for(Sensor s : sensors)
		{
			s.draw(g2d);
		}
		g2d.setColor(new Color(255,150,150));
		g2d.fillOval((int)x, (int)y, (int)size, (int)size);
//		drawDirection(g2d, 50);
		
		
	}
	
	
	public long getDeathTime()
	{
		return deathTime;
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
	
	public EvolutionaryNet getNet()
	{
		return net;
	}
	

}
