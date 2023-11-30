package tools;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tools
{

	
	
	
	
	
	public static BufferedImage scaleImage(BufferedImage b, double scale)
	{
		BufferedImage out = new BufferedImage( (int)(b.getWidth()*scale), (int)(b.getHeight()*scale), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < out.getWidth(); x++)
		{
			for (int y = 0; y < out.getHeight(); y++)
			{
				out.setRGB(x, y, b.getRGB((int)(x/scale), (int)(y/scale)));
			}
		}
		return out;
	}

	
	public static void fillCircleAtPoint(Graphics2D g2d,int x,int y,int diameter)
	{
		g2d.fillOval(x-diameter/2, y-diameter/2, diameter, diameter);
	}
	
	public static void fillCircleAtPoint(Graphics2D g2d,float x,float y,float diameter)
	{
		fillCircleAtPoint(g2d, (int)x, (int)y, (int)diameter);
		
	}
	
	public static void drawRect(Graphics2D g2d,Rectangle r)
	{
		g2d.drawRect(r.x, r.y, r.width, r.height);
	}
	
	
	public static double dist(double x1,double y1,double x2,double y2)
	{
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	
	
	public static boolean checkDistances(double x1,double y1,double x2,double y2,double distance)
	{
		return (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1) <= distance*distance;
	}
	
	
	
	
	
	
	
	
	
}
