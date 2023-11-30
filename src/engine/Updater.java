package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Updater implements ActionListener
{
	private Driver d;
	
	
	public Updater(Driver d)
	{
		this.d=d;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		for(World w : d.worlds)
		{
			w.update();
		}
		boolean anyActive = false;
		
		for(World w : d.worlds)
		{
			if(w.isActive())
			{
				anyActive=true;
				if(!d.currentWorld.isActive())
				{
					d.currentWorld=w;
				}
			}
		}
		if(!anyActive)
		{
			d.manager.nextGeneration();
		}
//		for(int i=0;i<d.worlds.size();i++)
//		{
//			if(!d.worlds.get(i).isActive())
//			{
//				if(d.worlds.size()>1)
//				{
//					if(d.worlds.remove(i)==d.currentWorld)
//					{
//						d.currentWorld = d.worlds.get(0);						
//					}
//					i--;
//				}
//				else
//				{
//					d.manager.nextGeneration();
//				}
//			}
//		}
			
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
