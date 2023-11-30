package neuralNet;

public class ReinforcementNeuralNet
{

	
	
	int inputs = 3,outputs = 3,hidden = 4;
	
	
	Node[] inputNodes = new Node[inputs],hiddenNodes = new Node[hidden],outputNodes = new Node[outputs];
	
	
	
	
	public double[] calculateOutput(double[] input)
	{
		double[] output = new double[outputs];
		
		for(int i=0;i<input.length;i++)
		{
			
		}
		
		
		
		return output;
	}
	
	
	public void init()
	{
		
		for(Node n : inputNodes)
		{
			n.setNodes(hiddenNodes);
			n.setWeights(genRandomWeights(hidden));
		}
		for(Node n : hiddenNodes)
		{
			n.setNodes(outputNodes);
			n.setWeights(genRandomWeights(outputs));
		}

		
	}
	
	
	
	private double[] genRandomWeights(int length)
	{
		double[] out = new double[length];
		for(int i=0;i<length;i++)
		{
			out[i] = Math.random()*2-1;
		}
		return out;
	}
	
	
	public static void main(String[] args)
	{
		ReinforcementNeuralNet net = new ReinforcementNeuralNet();
		net.init();
	}
	
	
}
