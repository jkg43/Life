package dataVisualization;

import java.awt.Graphics2D;

public abstract class PopupComp
{

	
	protected PopupWindow w;
	
	
	
	
	public void setWindow(PopupWindow w)
	{
		this.w=w;
	}
	
	
	public abstract void paint(Graphics2D g2d);
	public abstract void update();
	
	
	
	
	
	
}
