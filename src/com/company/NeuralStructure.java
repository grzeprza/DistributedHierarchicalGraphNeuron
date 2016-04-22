package com.company;

import java.util.Arrays;

public class NeuralStructure
{
	private int[][] array;
	private int counter;
	private boolean virgin;
	
	public NeuralStructure(int verses, int columns)
	{
		counter = 0;
		array = new int[verses][columns];
		virgin = true;
	}
	
	public void overwrite(int[][] newArray)
	{
		array = newArray;
		counter++;
		virgin = false;
		System.out.println(Arrays.deepToString(array));
	}
	
	public int[][] getArray()
	{
		return array;
	}
}
