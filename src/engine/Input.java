package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import dataVisualization.Graph;
import dataVisualization.NetVisualizer;
import dataVisualization.PopupComp;
import dataVisualization.PopupWindow;
import tools.Pointf;

public class Input implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener
{
	
	Driver d;
	UI ui;
	
	
	public int mx=0,my=0;
	

	public static final int UP=0,RIGHT=1,DOWN=2,LEFT=3;
	boolean[] motion = {false,false,false,false};
	
	
	Pointf pastDragPos;
	
	boolean dragging = false;
	
	
	public Input(Driver d,UI ui)
	{
		this.d=d;
		this.ui = ui;
	}
	
	
	public void update()
	{
		double adjustedSpeed = ui.camMoveSpeed / ui.camTransform.getScaleX();
		if(motion[UP])
		{
			ui.camTransform.translate(0, adjustedSpeed);
		}
		if(motion[DOWN])
		{
			ui.camTransform.translate(0,-adjustedSpeed);
		}
		if(motion[LEFT])
		{
			ui.camTransform.translate(adjustedSpeed,0);
		}
		if(motion[RIGHT])
		{
			ui.camTransform.translate(-adjustedSpeed,0);
		}
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(dragging)
		{
			float dx = e.getX() - pastDragPos.x;
			float dy = e.getY() - pastDragPos.y;
			
			pastDragPos.setLocation(e.getX(), e.getY());
			
			double cdx = dx / ui.camTransform.getScaleX();
			double cdy = dy / ui.camTransform.getScaleY();
			
			ui.camTransform.translate(cdx, cdy);
		}
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
		
		dragging = true;
		pastDragPos = new Pointf(mx,my);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		dragging = false;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_SPACE:
			if(d.updateTimer.isRunning())
			{
				d.updateTimer.stop();
			}
			else
			{
				d.updateTimer.start();
			}
			break;
		case KeyEvent.VK_W:
			motion[UP]=true;
			break;
		case KeyEvent.VK_D:
			motion[RIGHT]=true;
			break;
		case KeyEvent.VK_S:
			motion[DOWN]=true;
			break;
		case KeyEvent.VK_A:
			motion[LEFT]=true;
			break;
		case KeyEvent.VK_B:
			d.drawBounds = !d.drawBounds;
			break;
		case KeyEvent.VK_G:
			d.drawDebug = !d.drawDebug;
			break;
		case KeyEvent.VK_RIGHT:
			if(d.updateTimer.getDelay()!=0)
			{
				d.updateTimer.setDelay(d.updateTimer.getDelay()-1);
			}
			break;
		case KeyEvent.VK_LEFT:
			d.updateTimer.setDelay(d.updateTimer.getDelay()+1);
			break;
		case KeyEvent.VK_0:
			d.updateTimer.setDelay(0);
			break;
		case KeyEvent.VK_P:
			PopupWindow.createPopup(600, 600, new PopupComp[] {
					new Graph(400,400,"Generation","Longest life",d.manager.longestLives)});
			break;
		case KeyEvent.VK_X:
			incrementCurrentWorld();
			break;
		case KeyEvent.VK_Z:
			decrementCurrentWorld();
			break;
		case KeyEvent.VK_N:
			PopupWindow.createPopup(900, 700, new PopupComp[] {
					new NetVisualizer(d.currentWorld.debugCell.getNet(),d.manager.currentGen())});
			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_W:
			motion[UP]=false;
			break;
		case KeyEvent.VK_D:
			motion[RIGHT]=false;
			break;
		case KeyEvent.VK_S:
			motion[DOWN]=false;
			break;
		case KeyEvent.VK_A:
			motion[LEFT]=false;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{		
		Pointf mWorldPos = ui.getWorldPos(mx, my);
		
		ui.camTransform.translate(mWorldPos.getX(), mWorldPos.getY());

		if(e.getWheelRotation()==1)
		{
			ui.camTransform.scale(ui.INVERSE_SCALE, ui.INVERSE_SCALE);
		}
		if(e.getWheelRotation()==-1)
		{
			ui.camTransform.scale(ui.SCALE, ui.SCALE);
		}
		ui.camTransform.translate(-mWorldPos.getX(), -mWorldPos.getY());
	}
	
	public void incrementCurrentWorld()
	{
		int currentIndex = d.worlds.indexOf(d.currentWorld);
		if(currentIndex<d.worlds.size()-1)
		{
			d.currentWorld = d.worlds.get(currentIndex+1);
		}
		else
		{
			d.currentWorld = d.worlds.get(0);
		}
		if(!d.currentWorld.isActive())
		{
			incrementCurrentWorld();
		}
	}
	
	public void decrementCurrentWorld()
	{
		int currentIndex = d.worlds.indexOf(d.currentWorld);
		if(currentIndex>0)
		{
			d.currentWorld = d.worlds.get(currentIndex-1);
		}
		else
		{
			d.currentWorld = d.worlds.get(d.worlds.size()-1);
		}
		if(!d.currentWorld.isActive())
		{
			decrementCurrentWorld();
		}
	}
	
	
	
	

}
