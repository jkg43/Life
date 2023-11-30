package engine;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Driver extends JFrame
{

	private int FRAMERATE = 50,GAMEDELAY=0;
	Timer drawTimer,updateTimer;
	
	
	//Settings
	public boolean drawBounds = true,drawDebug=true;
	
	
	
	//TODO Add save system to record weights
	
	
	public UI ui;
	public Updater updater;
	public GenerationManager manager;
	
	
	public ArrayList<World> worlds;
	
	World currentWorld;
	
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			JFrame game = new Driver();
			game.setVisible(true);
		});
	}
	
	public Driver()
	{
		
		setTitle("Genetic Evolution Simulation");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		ui = new UI(this);
		updater = new Updater(this);
		
		manager = new GenerationManager(this);
		
		
		
		add(ui);
		pack();
		
		drawTimer = new Timer(FRAMERATE,ui);
		updateTimer = new Timer(GAMEDELAY,updater);
		
		
		worlds = new ArrayList<World>();

		manager.genNewWorlds();
		
		
		
		drawTimer.start();
		updateTimer.start();
		
	}
	
	
}
