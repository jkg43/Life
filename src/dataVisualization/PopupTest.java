package dataVisualization;

import java.util.HashMap;

import neuralNet.EvolutionaryNet;

public class PopupTest
{

	
	
	public static void main(String[] args)
	{
		
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		map.put(10, 4);
		map.put(5,3);
		map.put(1, 6);
		map.put(7, 9);
		
		PopupWindow.createPopup(600, 600, new PopupComp[] {
				new NetVisualizer(new EvolutionaryNet(3,3,4),0)
				});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
