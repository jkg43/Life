package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import javax.swing.JPanel;

import tools.Pointf;



public class UI extends JPanel implements ActionListener
{
	
	
	public final int WIDTH = 1000,HEIGHT=700;
	
	
	public final double SCALE = 1.1,INVERSE_SCALE = 1/SCALE;



	
	public float camMoveSpeed = 10;
	
	
	public AffineTransform camTransform = new AffineTransform();
	
	
	

	
	private void draw(Graphics2D g2d)
	{
		for(World w : d.worlds)
		{
			w.draw(g2d,w==d.currentWorld);
		}
	}
	
	private void drawUI(Graphics2D g2d)
	{

		if(d.drawDebug)
		{
			drawDebug(g2d);			
		}	
	}
	
	private void drawDebug(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
//		Pointf camMousePos = getWorldPos(input.mx,input.my);
//		Pointf camPos = getCamPos();
//		
		String[] debugInfo = {
				"T: "+(d.currentWorld==null ? "No World" : ""+d.currentWorld.getTime()),
//				"MX: "+input.mx+", MY: "+input.my,
//				"CMX: "+camMousePos.x+", CMY: "+camMousePos.y,
//				"SX: "+camTransform.getScaleX()+", SY: "+camTransform.getScaleY(),
//				"CX: "+camPos.x+", CY: "+camPos.y,
				"E: "+(d.currentWorld==null ? "No World" : ""+d.currentWorld.debugCell.energy),
				"Generation: "+d.manager.currentGen(),
				"Delay: "+d.updateTimer.getDelay(),
				"Longest Life: "+d.manager.longestLife
		};
		int y=HEIGHT-15;
		for(String s : debugInfo)
		{
			g2d.drawString(s, 15, y);
			y-=30;
		}
	}
	
	
	


	
	
	private void initGraphics(Graphics2D g2d)
	{
		g2d.setTransform(camTransform);
		
		g2d.setFont(font);
		
		
	}
	
	
	public Pointf getWorldPos(float x,float y)
	{
		Pointf out = new Pointf();
		try
		{
			camTransform.inverseTransform(new Pointf(x,y), out);
		} catch (NoninvertibleTransformException e)
		{
			e.printStackTrace();
		}
		return out;
	}
	
	public Pointf getWorldPos(Pointf p)
	{
		Pointf out = new Pointf();
		try
		{
			camTransform.inverseTransform(p, out);
		} catch (NoninvertibleTransformException e)
		{
			e.printStackTrace();
		}
		return out;
	}

	public Pointf getCamPos()
	{
		Pointf out = new Pointf();
		try
		{
			camTransform.inverseTransform(new Pointf(0,0), out);
		} catch (NoninvertibleTransformException e)
		{
			e.printStackTrace();
		}
		return out;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	Input input;
	Font font = new Font("arial",Font.PLAIN,25);
	Driver d;
	@Override
	public void actionPerformed(ActionEvent a)
	{
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		initGraphics(g2d);
		draw(g2d);
		
		g2d.setTransform(new AffineTransform());
		drawUI(g2d);
	}
	


	public UI(Driver d)
	{
		this.d=d;
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		
		input = new Input(d,this);
		
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
		
	}

	
	
	
}
