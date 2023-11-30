package dataVisualization;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PopupInput implements KeyListener,MouseListener,MouseMotionListener
{

	
	public int mx=0,my=0;
	
	PopupWindow w;
	
	public PopupInput(PopupWindow w)
	{
		this.w=w;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

}
