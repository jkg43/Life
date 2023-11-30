package creatures;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import engine.World;
import tools.Pointf;

public abstract class Organism
{

	public static final String filePath = "src/res/",defaultFileName = "missing_texture.png";
	
	private long creationTime=0;
	
	
	protected float x,y;
	
	protected double angle;
	
	private BufferedImage img;
	
	protected World w;
	
	protected boolean active=true;
	
	
	public Organism(World w,float x,float y,String fileName)
	{
		this.w=w;
		this.x=x;
		this.y=y;
		angle = 0;
		
		creationTime = w.getTime();
		
//		try
//		{
//			img = Tools.scaleImage( ImageIO.read(new File(filePath+fileName)),0.1);
//		}
//		catch (IOException e)
//		{
//			System.out.println("File "+fileName+" not found");
//			img = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
//			img.setRGB(0, 0, 0);
//		}
	}
	
	public Organism(World w,float x,float y)
	{
		this(w,x,y,defaultFileName);
	}
	
	
	//called every world tick
	public abstract void update();
	
	//gets bounding box
	public abstract Rectangle getBounds();
	
	//gets the center reference point(for components)
	public abstract Pointf getCenter();
	
	public void draw(Graphics2D g2d)
	{
		float centerX = img.getWidth()/2,centerY = img.getHeight()/2;
		
		AffineTransform at = new AffineTransform();
		at.rotate(angle, centerX,centerY);
		
		g2d.drawImage(img, at,null);
	}

	//indicates direction visually
	protected final void drawDirection(Graphics2D g2d,int length)
	{
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		Pointf center = getCenter();
		g2d.drawLine((int)center.x, (int)center.y, (int)(center.x+length*Math.cos(angle)), (int)(center.y+length*Math.sin(angle)));
	}
	
	//makes sure the organism isn't out of the world
	protected final void checkBoundsAndCorrect()
	{
		Rectangle r = getBounds();
		if(r.x<0)
		{
			x=0;
		}
		if(r.y<0)
		{
			y=0;
		}
		if(r.x+r.width>w.WIDTH)
		{
			x=w.WIDTH-r.width;
		}
		if(r.y+r.height>w.HEIGHT)
		{
			y=w.HEIGHT-r.height;
		}
	}

	

	public long getAge()
	{
		return w.getTime() - creationTime;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	
}
