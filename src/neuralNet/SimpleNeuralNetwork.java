package neuralNet;

import java.util.List;

import tools.Matrix;

public class SimpleNeuralNetwork
{
	
	//https://towardsdatascience.com/understanding-and-implementing-neural-networks-in-java-from-scratch-61421bb6352c

	//ih - weights for input and hidden layers
	//ho - weights for hidden and output layers
	//h - bias for hidden
	//o - bias for output
	//l - learning rate
	Matrix weights_ih,weights_ho,bias_h,bias_o;
	
	double l_rate=0.01;
	
	
	
	public SimpleNeuralNetwork(int input,int hidden,int output)
	{
		weights_ih = new Matrix(hidden, input);
		weights_ih = new Matrix(output, hidden);
		
		bias_h = new Matrix(hidden, 1);
		bias_o = new Matrix(output, 1);
	}
	
	
	public List<Double> predict(double[] X)
	{
		Matrix input = Matrix.fromArray(X);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();
		
		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();
		
		return output.toArray();
	}
	
	
	public void train(double[] X,double[] Y)
	{
		Matrix input = Matrix.fromArray(X);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();
		
		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();
		
		Matrix target = Matrix.fromArray(Y);
		
		Matrix error = Matrix.subtract(target, output);
		Matrix gradient = output.dsigmoid();
		gradient.multiply(error);
		gradient.multiply(l_rate);
		
		Matrix hidden_T = Matrix.transpose(hidden);
		Matrix who_delta = Matrix.multiply(gradient, hidden_T);
		
		weights_ho.add(who_delta);
		bias_o.add(gradient);
		
		Matrix who_T = Matrix.transpose(weights_ho);
		Matrix hidden_errors = Matrix.multiply(who_T, error);
		
		Matrix h_gradient = hidden.dsigmoid();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(l_rate);
		
		Matrix i_T = Matrix.transpose(input);
		Matrix wih_delta = Matrix.multiply(h_gradient, i_T);
		
		weights_ih.add(wih_delta);
		bias_h.add(h_gradient);
		
	}
	
	
	public void fit(double[][] X,double[][] Y,int epochs)
	{
		for(int i=0;i<epochs;i++)
		{
			int sampleN = (int)(Math.random()*X.length);
			this.train(X[sampleN], Y[sampleN]);
		}
	}
	
	
	
	
	
	
}
