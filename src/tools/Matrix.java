package tools;

import java.util.ArrayList;
import java.util.List;

public class Matrix
{

	
	double[][] data;
	int rows,cols;
	
	
	

	public Matrix(int rows,int cols,boolean random)
	{
		data = new double[rows][cols];
		this.rows=rows;
		this.cols=cols;
		if(random)
		{
			for(int x=0;x<rows;x++)
			{
				for(int y=0;y<cols;y++)
				{
					
					data[x][y] = Math.random()*2-1;
				}
			}
		}
		else
		{
			for(int x=0;x<rows;x++)
			{
				for(int y=0;y<cols;y++)
				{
					data[x][y] = 0;
				}
			}
		}
	}
	
	public Matrix(int rows,int cols)
	{
		
	}
	
	
	public void add(double scalar)
	{
		for(int x=0;x<rows;x++)
		{
			for(int y=0;y<cols;y++)
			{
				this.data[x][y]+= scalar;
			}
		}
	}
	
	public void add(Matrix m)
	{
		if(cols!=m.cols || rows!=m.rows)
		{
			System.out.println("Matrix shape mismatch");
			return;
		}
		
		for(int x=0;x<rows;x++)
		{
			for(int y=0;y<cols;y++)
			{
				this.data[x][y]+=m.data[x][y];
			}
		}
	}
	
	public static Matrix subtract(Matrix a,Matrix b)
	{
		Matrix temp = new Matrix(a.rows, a.cols);
		for(int x=0;x<a.rows;x++)
		{
			for(int y=0;y<a.cols;y++)
			{
				temp.data[x][y] = a.data[x][y]-b.data[x][y];
			}
		}
		return temp;
	}
	
	public static Matrix transpose(Matrix a)
	{
		Matrix temp = new Matrix(a.rows, a.cols);
		for(int x=0;x<a.rows;x++)
		{
			for(int y=0;y<a.cols;y++)
			{
				temp.data[y][x] = a.data[x][y];
			}
		}
		return temp;
	}
	
	
	public static Matrix multiply(Matrix a, Matrix b)
	{
		Matrix temp=new Matrix(a.rows,b.cols);
		for(int i=0;i<temp.rows;i++)
		{
			for(int j=0;j<temp.cols;j++)
			{
				double sum=0;
				for(int k=0;k<a.cols;k++)
				{
					sum+=a.data[i][k]*b.data[k][j];
				}
				temp.data[i][j]=sum;
			}
		}
		return temp;
	}

	public void multiply(Matrix a)
	{
	    for(int i=0;i<a.rows;i++)
	    {
	        for(int j=0;j<a.cols;j++)
	        {
	            this.data[i][j]*=a.data[i][j];
	        }
	    }
	    
	}
	
	public void multiply(double a)
	{
	    for(int i=0;i<rows;i++)
	    {
	        for(int j=0;j<cols;j++)
	        {
	            this.data[i][j]*=a;
	        }
	    }
	    
	}
	
	
	public void sigmoid()
	{
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
        }
        
    }
    
    public Matrix dsigmoid()
    {
        Matrix temp=new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
                temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
        }
        return temp;
        
    }
	
	
    public static Matrix fromArray(double[]x)
    {
        Matrix temp = new Matrix(x.length,1);
        for(int i =0;i<x.length;i++)
            temp.data[i][0]=x[i];
        return temp;
        
    }
    
    public List<Double> toArray()
    {
        List<Double> temp= new ArrayList<Double>();
       
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                temp.add(data[i][j]);
            }
        }
        return temp;
   }
	
	
	
	
}
