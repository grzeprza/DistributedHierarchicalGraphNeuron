package com.company;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawWindow extends JComponent
{

	private Image image;
	private Graphics2D graphic;
	private int[] dim;
	private int resolutionX;
	private int resolutionY;

	private int currX, currY, oldX, oldY;

	public Image getImage()
	{
		return image;
	}

	public Graphics2D getGraphics()
	{
		return graphic;
	}

	public void setDimensions(int[] input)
	{
		dim = input;
	}
	
	public int[] getDimensions()
	{
		return dim;
	}

	public DrawWindow()
	{
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter()
		{

			@Override
			public void mousePressed(MouseEvent e)
			{
				oldX = e.getX();
				oldY = e.getY();
				paintTile(oldX, oldY);
				repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				currX = e.getX();
				currY = e.getY();

				if (graphic != null)
				{
					paintTile(currX, currY);
					repaint();
					oldX = currX;
					oldY = currY;
				}
			}
		});
	}

	//Perfect code to refactor due to the duplicates
	private void paintTile(int x, int y)
	{
		int resolutionX = getSize().width / dim[1];
		int resolutionY = getSize().height / dim[0];
		int leftUpper = x / resolutionX * resolutionX;
		int rightLower = y / resolutionY * resolutionY;
		graphic.fillRect(leftUpper, rightLower, resolutionX, resolutionY);
	}

	private void paintDiffusedTile(int x, int y)
	{
		paintSmallTile(x, y);
		paintSmallTile(x + 2 * resolutionX / 3, y);
		paintSmallTile(x, y + 2 * resolutionY / 3);
		paintSmallTile(x + 2 * resolutionX / 3, y + 2 * resolutionY / 3);
		paintSmallTile(x + resolutionX / 3, y + resolutionY / 3);
	}

	private void paintSmallTile(int x, int y)
	{
		int resolutionX = getSize().width / dim[1];
		int resolutionY = getSize().height / dim[0];
		graphic.fillRect(x, y, resolutionX / 3, resolutionY / 3);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		if (image == null)
		{
			image = createImage(getSize().width, getSize().height);
			graphic = (Graphics2D) image.getGraphics();
			graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	public void paintPattern(int[][] pattern)
	{
		graphic.setPaint(Color.red);
		for(int i = 0; i < pattern[0].length; i++)
		{
			for(int j = 0; j < pattern.length; j++)
			{
				if(pattern[j][i] == 1)
					paintDiffusedTile(i * resolutionX, j * resolutionY);
			}
		}
		graphic.setPaint(Color.BLUE);
	}

	public void clear()
	{
		graphic.setPaint(Color.white);
		graphic.fillRect(0, 0, getSize().width, getSize().height);
		graphic.setPaint(Color.blue);
		repaint();
		resolutionX = getSize().width / dim[1];
		resolutionY = getSize().height / dim[0];
	}

}
