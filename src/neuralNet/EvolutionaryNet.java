package neuralNet;

import java.util.Random;

public class EvolutionaryNet
{

	
	
	private int inputs = 3,outputs = 3,hidden = 4;
	
	
	//weights for input --> hidden layers
	//1st term is hidden node index, 2nd is input node index
	double[][] hiWeights;
	
	
	//weights for hidden --> output layers
	//1st is output index,2nd is hidden index
	double[][] ohWeights;
	
	
	public static final double standardChangeRate = 0.1;
	private static final Random signRand = new Random();
	
	
	public EvolutionaryNet(int in,int out,int hid)
	{
		inputs = in;
		outputs = out;
		hidden = hid;
		hiWeights = new double[hidden][inputs];
		ohWeights = new double[outputs][hidden];
		
		for(int i=0;i<hiWeights.length;i++)
		{
			for(int j=0;j<hiWeights[0].length;j++)
			{
				hiWeights[i][j] = Math.random()*2-1;
			}
		}

		for(int i=0;i<ohWeights.length;i++)
		{
			for(int j=0;j<ohWeights[0].length;j++)
			{
				ohWeights[i][j] = Math.random()*2-1;
			}
		}
	}
	
	public EvolutionaryNet randomize()
	{
		return new EvolutionaryNet(inputs, outputs, hidden);
	}
	
	private EvolutionaryNet(int in,int out,int hid,double[][] hi,double[][] oh)
	{
		inputs = in;
		outputs = out;
		hidden = hid;
		hiWeights=hi;
		ohWeights=oh;
	}
	
	
	public EvolutionaryNet copy()
	{
		double[][] hi = new double[hidden][inputs],oh = new double[outputs][hidden];
		
		for(int i=0;i<hi.length;i++)
		{
			for(int j=0;j<hi[0].length;j++)
			{
				hi[i][j] = hiWeights[i][j];
			}
		}

		for(int i=0;i<oh.length;i++)
		{
			for(int j=0;j<oh[0].length;j++)
			{
				oh[i][j] = ohWeights[i][j];
			}
		}
		
		return new EvolutionaryNet(inputs, outputs, hidden, hi, oh);
	}
	
	
	public double[] processInputs(double[] input)
	{
		double[] output = new double[outputs];
		
		double[] hiddenOutputs = new double[hidden];
		
		for(int i=0;i<hidden;i++)
		{
			hiddenOutputs[i] = processNode(input, hiWeights[i]);
		}
		
		for(int i=0;i<outputs;i++)
		{
			output[i] = processNode(hiddenOutputs, ohWeights[i]);
		}
		
		
		return output;
	}
	
	
	
	private double processNode(double[] nodeInputs,double[] nodeWeights)
	{
		double out = 0;
		
		for(int i=0;i<nodeInputs.length;i++)
		{
			out += nodeInputs[i] * nodeWeights[i];
		}
		
		
		return sigmoid(out);
	}
	
	
	public static double sigmoid(double input)
	{
		return 1/(1+Math.exp(-input));
	}
	

	
	
	public EvolutionaryNet evolve(double changeRate)
	{
		for(int i=0;i<hiWeights.length;i++)
		{
			for(int j=0;j<hiWeights[0].length;j++)
			{
				hiWeights[i][j] += changeRate * (signRand.nextBoolean() ? 1 : -1);
			}
		}
		
		for(int i=0;i<ohWeights.length;i++)
		{
			for(int j=0;j<ohWeights[0].length;j++)
			{
				ohWeights[i][j] += changeRate * (signRand.nextBoolean() ? 1 : -1);
			}
		}
		return this;
	}
	
	public EvolutionaryNet evolve()
	{
		return this.evolve(standardChangeRate);
	}
	
	
	public void run()
	{
		double[] result = processInputs(new double[] {-0.5,0,0.5});
		print(result);
	}
	
	public static void print(double[][] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void print(double[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+"\t");
		}
	}

	public int getInputs()
	{
		return inputs;
	}

	public int getOutputs()
	{
		return outputs;
	}

	public int getHidden()
	{
		return hidden;
	}

	public double[][] getHiWeights()
	{
		return hiWeights;
	}

	public double[][] getOhWeights()
	{
		return ohWeights;
	}
	
//	public static void main(String[] args)
//	{
//		EvolutionaryNet net = new EvolutionaryNet(3,3,4);
//		net.run();
//
//	}
	
	
	
	
}
