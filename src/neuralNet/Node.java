package neuralNet;

public class Node
{

	
	
	Node[] connections=null;
	double[] weights=null;
	
	public static final int INPUT=0,HIDDEN=1,OUTPUT=2;
	
	int location;
	int index;
	
	public Node(int l,int i)
	{
		location=l;
		index=i;
	}
	
	public void setNodes(Node[] c)
	{
		connections = c;
	}
	
	public void setWeights(double[] w)
	{
		weights = w;
	}
	
	
	
	
	
}
