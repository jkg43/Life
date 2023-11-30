package dataVisualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PopupWindow extends JFrame implements ActionListener
{

	
	
	public PopupInput in;
	
	public static final Font popupFont = new Font("arial", Font.PLAIN, 25);
	
	
	private PopupWindow(int width,int height,PopupComp[] components)
	{
		setTitle("Popup");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(width,height));
		setResizable(false);
		
		in = new PopupInput(this);
		
		add(new PopupPanel(this,width,height,components));
		
		pack();
		
		Timer drawTimer = new Timer(50,this);
		drawTimer.start();
		
		
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		repaint();
	}
	
	
	public static void createPopup(int width,int height,PopupComp[] components)
	{
		EventQueue.invokeLater(() -> {
			JFrame window = new PopupWindow(width,height,components);
			window.setVisible(true);
		});
	}



	
	
	
	
	
	@SuppressWarnings("unused")
	private class PopupPanel extends JPanel
	{
		private PopupComp[] comps;
		
		private PopupWindow w;
		
		public PopupPanel(PopupWindow w,int width,int height,PopupComp[] components)
		{
			this.w=w;
			comps = components;
			
			setPreferredSize(new Dimension(width,height));
			
			setFocusable(true);
			
			addKeyListener(in);
			addMouseListener(in);
			addMouseMotionListener(in);
			
			for(PopupComp c : comps)
			{
				c.setWindow(w);
			}
		}
		
		
		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D)g;
			
			g2d.setFont(popupFont);
			
			for(PopupComp c : comps)
			{
				c.paint(g2d);
			}
			
		}
		
		
		
	}
	
	
	
	
	
	
	
}
