package com.company;

import java.util.Arrays;

public class NeuralStructure
{
	private int[][] array;
	private int counter;
	private boolean virgin;
	private char character;
	
	public NeuralStructure(int verses, int columns)
	{
		counter = 0;
		array = new int[verses][columns];
		virgin = true;
	}

	public NeuralStructure(int verses, int columns, char charRepresented)
	{
		counter = 0;
		array = new int[verses][columns];
		virgin = true;
		character = charRepresented;
	}
	
	public void overwrite(int[][] newArray, char newChar)
	{
		array = newArray;
		counter++;
		virgin = false;
		character = newChar;
		//System.out.println(Arrays.deepToString(array));
	}

	public char getCharacter()
	{
		return character;
	}

	public int[][] getArray()
	{
		return array;
	}
}
