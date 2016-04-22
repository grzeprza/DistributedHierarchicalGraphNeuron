package com.company;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NeuralMemory
{
	private HashMap<Character, NeuralStructure> patterns;
	
	public static int[][] shitToIntArray(BufferedImage im, int[] dim, int res)
	{
		int width = im.getWidth();
		int height = im.getHeight();
		int[][] result = new int[dim[0]][dim[1]];
		
		for (int i = 0; i < dim[0]; i++)
		{
			for (int j = 0; j < dim[1]; j++)
			{
				if(new Color(im.getRGB(j * res, i * res)).equals(Color.blue))
				{
					result[i][j] = 1;
				}
			}
		}
		return result;
	}

	public NeuralMemory(DrawWindow w)			//PYTANIE: Czy inicjalizatorem powinien być DrawWindow czy jego wymiary
	{
		int[][] nullArray = new int[w.getDimensions()[0]][w.getDimensions()[1]];
		patterns = new HashMap<>();
		for(char letter = 'A'; letter <= 'Z'; letter++)
		{
			patterns.put(letter, new NeuralStructure(w.getDimensions()[0], w.getDimensions()[1]));
		}
	}
	
	public void overwrite(int[][] newArray, char letter)			//Czy to można zrobić lepiej?
	{
		try
		{
			patterns.get(letter).overwrite(newArray);
		}
		catch(Exception e)
		{
			System.out.println("No letter found, probably.");
		}
	}
}
