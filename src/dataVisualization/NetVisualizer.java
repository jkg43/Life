package dataVisualization;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import creatures.Herbivore;
import neuralNet.EvolutionaryNet;
import tools.Tools;

public class NetVisualizer extends PopupComp
{

	
	EvolutionaryNet net;
	int inputs,outputs,hidden;
	
	private int inOffset=125,inSpacing=75,inX=225,
			hidOffset=80,hidSpacing=75,hidX=425,
			outOffset=200,outSpacing=125,outX=625;
	
	private int weightScaleFactor = 3,nodeSize = 20,textSpacing = 15,textShift = 8;
	
	private int generation;
	
	public NetVisualizer(EvolutionaryNet n,int gen)
	{
		net = n.copy();
		inputs = n.getInputs();
		outputs = n.getOutputs();
		hidden = n.getHidden();
		generation = gen;
	}
	
	
	
	@Override
	public void update()
	{
//		int mx = w.in.mx,my = w.in.my;
		
		//TODO allow selection and deselection of nodes and make all other connections very transparent
		
		
	}
	
	
	@Override
	public void paint(Graphics2D g2d)
	{
		
		g2d.setColor(Color.BLACK);
		
		double[][] hiWeights = net.getHiWeights(),ohWeights = net.getOhWeights();
		
		for(int in=0;in<inputs;in++)
		{
			for(int hid=0;hid<hidden;hid++)
			{
				g2d.drawLine(inX, inOffset+inSpacing*in, hidX, hidOffset+hidSpacing*hid);
				
				int width = (int)(weightScaleFactor*hiWeights[hid][in]);
				
				if(width<0)
				{
					g2d.setColor(Color.RED);
					width = Math.abs(width);
				}
				else
				{
					g2d.setColor(Color.BLACK);
				}
				
				g2d.setStroke(new BasicStroke(width));
				
			}
		}
		
		for(int hid=0;hid<hidden;hid++)
		{
			for(int out=0;out<outputs;out++)
			{
				g2d.drawLine(hidX, hidOffset+hidSpacing*hid, outX, outOffset+outSpacing*out);
				
				int width = (int)(weightScaleFactor*ohWeights[out][hid]);
				
				if(width<0)
				{
					g2d.setColor(Color.RED);
					width = Math.abs(width);
				}
				else
				{
					g2d.setColor(Color.BLACK);
				}
				
				g2d.setStroke(new BasicStroke(width));
			}
		}
		
		
		
		g2d.setColor(Color.BLUE);
		
		for(int i=0;i<inputs;i++)
		{
			Tools.fillCircleAtPoint(g2d, inX, inOffset+inSpacing*i, nodeSize);
		}
		
		g2d.setColor(Color.RED);
		
		for(int i=0;i<hidden;i++)
		{
			Tools.fillCircleAtPoint(g2d, hidX, hidOffset+hidSpacing*i, nodeSize);
		}
		
		g2d.setColor(Color.GREEN);
		
		for(int i=0;i<outputs;i++)
		{
			Tools.fillCircleAtPoint(g2d, outX, outOffset+outSpacing*i, 20);
		}
		
		g2d.setColor(Color.BLACK);
		
		FontMetrics fm = g2d.getFontMetrics();
		
		for (int i = 0; i < inputs; i++)
		{
			String name = Herbivore.inputNames[i];
			g2d.drawString(name, inX-textSpacing-fm.stringWidth(name), inOffset+inSpacing*i+textShift);
		}
		
		for (int i = 0; i < outputs; i++)
		{
			String name = Herbivore.outputNames[i];
			g2d.drawString(name, outX+textSpacing, outOffset+outSpacing*i+textShift);
		}
		
		g2d.drawString("Generation: "+generation, 8, 30);
		
	}








	
	

	
	
}
