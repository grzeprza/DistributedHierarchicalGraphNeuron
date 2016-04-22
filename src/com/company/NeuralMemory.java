package com.company;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NeuralMemory
{
	private static ArrayList<Integer> maps = new ArrayList<Integer>();
	
	public static int[][] shitToIntArray(BufferedImage im, int[] dim, int res)
	{
		int width = im.getWidth();
		int height = im.getHeight();
		int[][] result = new int[dim[0]][dim[1]];
		
		for (int i = 0; i < dim[1]; i++)
		{
			for (int j = 0; j < dim[0]; j++)
			{
				if(new Color(im.getRGB(j * res, i * res)).equals(Color.blue))
				{
					result[j][i] = 1;
				}
			}
		}
		return result;
	}
}
