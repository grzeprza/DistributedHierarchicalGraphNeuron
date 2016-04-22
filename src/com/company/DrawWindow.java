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

	private void paintTile(int x, int y)
	{
		int resolutionX = getSize().width / dim[1];
		int resolutionY = getSize().height / dim[0];
		int leftUpper = x / resolutionX * resolutionX;
		int rightLower = y / resolutionY * resolutionY;
		graphic.fillRect(leftUpper, rightLower, resolutionX, resolutionY);
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

	public void clear()
	{
		graphic.setPaint(Color.white);
		graphic.fillRect(0, 0, getSize().width, getSize().height);
		graphic.setPaint(Color.blue);
		repaint();
	}

}
