package engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

import creatures.Cell;

@SuppressWarnings("unused")
public class GenerationManager
{
	
	Driver d;
	
	
	public static final int numWorlds = 48;
	
	
	private int generation = 0;
	
	public ArrayList<Cell> reproducingCells = new ArrayList<Cell>();
	
	public long longestLife=0;
	
	private Random seedGen = new Random();
	
	public long seed;
	
	public HashMap<Integer, Integer> longestLives = new HashMap<Integer,Integer>();
	
	public GenerationManager(Driver d)
	{
		this.d=d;
		seed = seedGen.nextLong();
	}
	
	
	public void genNewWorlds()
	{
		d.worlds.clear();
		for(int i=0;i<numWorlds;i++)
		{
			d.worlds.add(new World(d));
		}
		d.currentWorld = d.worlds.get(0);
	}
	
	
	public void nextGeneration()
	{
		generation++;
		
		changeSeed();
		
//		topHalfReproduce();
//		topTwoReproduce();
//		topReproduceStaggered(getCells()); //42
		topStaggeredKeepOriginal(getCells()); //48
	}
	
	private ArrayList<Cell> getCells()
	{
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		for(World w : d.worlds)
		{
			cells.add(w.debugCell);
		}
		
		cells.sort(Comparator.comparing(Cell::getDeathTime));
		
		long longest = cells.get(cells.size()-1).getDeathTime();
		
		longestLives.put(generation-1, (int)longest);
		
		System.out.println("Starting gen "+generation+" - Longest life: "+longest);
		
		if(longest>longestLife)
		{
			longestLife=longest;
		}
		
		return cells;
	}
	
	private void topStaggeredKeepOriginal(ArrayList<Cell> cells)
	{
		
		//same as normal staggered but one copy of original survives
		
		Cell[] reproducingCells = new Cell[6];
		for(int i=0;i<6;i++)
		{
			reproducingCells[i] = cells.get(cells.size()-1-i);
			d.worlds.get(i).reset(reproducingCells[i].getNet());
		}
		
		int n=6;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<12-2*i;k++)
			{
				d.worlds.get(n).reset(reproducingCells[i].getNet().copy().evolve());
				n++;
			}
		}
		
//		for(;n<numWorlds;n++)
//		{
//			d.worlds.get(n).reset(d.worlds.get(n).debugCell.getNet().randomize());
//		}
		
		
	}
	
	
	private void topReproduceStaggered(ArrayList<Cell> cells)
	{

		/* 1st: 12
		 * 2nd: 10
		 * 3rd: 8
		 * 4th: 6
		 * 5th: 4
		 * 6th: 2
		 * total 42
		 */

		Cell[] reproducingCells = new Cell[6];
		for(int i=0;i<6;i++)
		{
			reproducingCells[i] = cells.get(cells.size()-1-i);
		}
		int n=0;
		for(int i=0;i<5;i++)
		{
			for(int k=0;k<12-2*i;k++)
			{
				d.worlds.get(n).reset(reproducingCells[i].getNet().copy().evolve());
				n++;
			}
		}
		
		
		
	}
	
	
	
	
	
	private void topHalfReproduce()
	{
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		for(World w : d.worlds)
		{
//			w.reset(w.debugCell.getNet().evolve(0.05-0.0125*Math.log10(w.debugCell.getDeathTime())));
			cells.add(w.debugCell);
		}
		
		cells.sort(Comparator.comparing(Cell::getDeathTime));
		int half = cells.size()/2;
		for(int i=0;i<half;i++)
		{
			cells.remove(0);
		}
		
		//top half get to reproduce, not ideal selection criteria
		for(int i=0;i<cells.size();i++)
		{
			d.worlds.get(i).reset(cells.get(i).getNet().evolve());
			d.worlds.get(2*i).reset(cells.get(i).getNet().copy().evolve());
		}
	}
	
	private void topTwoReproduce()
	{
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		for(World w : d.worlds)
		{
			cells.add(w.debugCell);
		}
		
		cells.sort(Comparator.comparing(Cell::getDeathTime));
		
		Cell c1 = cells.get(cells.size()-1),c2 = cells.get(cells.size()-2);
		
		for(int i=0;i<numWorlds/2;i++)
		{
			d.worlds.get(i).reset(c1.getNet().copy().evolve());
			d.worlds.get(2*i).reset(c2.getNet().copy().evolve());
		}
		
		
		
	}
	
	
	public int currentGen()
	{
		return generation;
	}
	
	public void changeSeed()
	{
		seed = seedGen.nextLong();
	}
	
	
}