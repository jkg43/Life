package dataVisualization;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import tools.Tools;

public class Graph extends PopupComp
{

	
	HashMap<Integer, Integer> values;
	
	private static final int xOffset=80,yOffset=60,textOffset = 50,numOffset = 10,textHeight=25;
	
	private static final Font font = new Font("arial",Font.PLAIN,textHeight);
	
	private int width,height;
	
	private String xLabel,yLabel;
	
	
	
	public Graph(int w,int h,String x,String y,HashMap<Integer,Integer> m)
	{
		width=w;
		height=h;
		xLabel = x;
		yLabel = y;
		
		values = m;
		
	}
	
	
	public void addValue(int k,int v)
	{
		values.put(k, v);
	}
	
	public void setDataMap(HashMap<Integer,Integer> m)
	{
		values = m;
	}
	
	@Override
	public void paint(Graphics2D g2d)
	{
		
		int maxKey=0,maxVal=0;
		
		if(values.size()>0)
		{
			maxKey = values.keySet().stream().max(Integer::compare).get();
			maxVal = values.values().stream().max(Integer::compare).get();
		}
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(4));
		
		g2d.drawLine(xOffset, yOffset, xOffset, yOffset+height);
		g2d.drawLine(xOffset, yOffset+height, xOffset+width, yOffset+height);
		
		g2d.setFont(font);
		g2d.drawString(xLabel, xOffset+width/2-g2d.getFontMetrics().stringWidth(xLabel)/2, yOffset+textOffset+height);
		
		
		g2d.drawString("0", xOffset-numOffset, yOffset+height+textHeight);
		
		if(maxKey!=0)
		{
			g2d.drawString(""+maxKey,xOffset+width,yOffset+height+textHeight);
		}
		if(maxVal!=0)
		{
			g2d.drawString(""+maxVal,xOffset-g2d.getFontMetrics().stringWidth(""+maxVal)-2,yOffset);
		}
		
		
		
		AffineTransform og = g2d.getTransform();
		
		g2d.rotate(Math.PI/2);
		
		g2d.drawString(yLabel, yOffset+height/2-g2d.getFontMetrics().stringWidth(yLabel)/2, textOffset-xOffset);
		
		g2d.setTransform(og);
		
		
		
		g2d.setColor(Color.GREEN);
		
		if((maxKey==0 || maxVal==0) && values.size()>0)
		{
			Tools.fillCircleAtPoint(g2d, xOffset, yOffset+height, 8);
		}
		else
		{
			for(Map.Entry<Integer,Integer> pair : values.entrySet())
			{
				Tools.fillCircleAtPoint(g2d, xOffset+(width*pair.getKey()/maxKey), yOffset+height-(height*pair.getValue()/maxVal), 8);
			}
		}
		
		
		
	}


	@Override
	public void update()
	{
		
	}
	
	
	
	
	
}
