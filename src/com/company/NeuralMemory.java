package com.company;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NeuralMemory
{
	private HashMap<Character, NeuralStructure> patterns;

	public NeuralMemory(DrawWindow w)			//Inicjalizatorem powinny być
	{
		patterns = new HashMap<>();
		for(char letter = 'A'; letter <= 'Z'; letter++)
		{
			patterns.put(letter, new NeuralStructure(w.getDimensions()[0], w.getDimensions()[1], letter));
		}
	}

	public NeuralMemory(int width, int height)
	{
		int[][] nullArray = new int[height][width];
		patterns = new HashMap<>();
		for(char letter = 'A'; letter <= 'Z'; letter++)
		{
			patterns.put(letter, new NeuralStructure(width, height, letter));
		}
	}

	public void overwrite(int[][] newArray, char letter)			//Czy to można zrobić lepiej?
	{
		try
		{
			patterns.get(letter).overwrite(newArray, letter);
		}
		catch(Exception e)
		{
			System.out.println("No letter found, probably.");
		}
	}

    public HashMap<Character, NeuralStructure> getHashMap()
    {
        return patterns;
    }
}
