package dataVisualization;

import java.awt.Color;
import java.awt.Graphics2D;

public class DebugComp extends PopupComp
{

	
	
	
	public DebugComp()
	{
	}
	
	
	
	@Override
	public void paint(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		
		g2d.drawString(w.in.mx+", "+w.in.my, w.in.mx, w.in.my);
	}



	@Override
	public void update()
	{
		
	}
	
	
	
}
