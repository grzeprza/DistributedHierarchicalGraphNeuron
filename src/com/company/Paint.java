package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint
{
	// Here we can set a size of the tile and resolution of painting window

	int res = 50;
	public static int[] dim = {7, 5};
	
	//Building the view
	
	JButton clearBtn;
	JButton saveBtn;
	JButton printBtn;
	JComboBox comboBox;
	DrawWindow drawWindow;
	NeuralMemory neuralMemory;
	
	ActionListener actionListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == clearBtn)
			{
				drawWindow.clear();
			} 
			else if (e.getSource() == saveBtn)
			{
				int[][] values = shitToIntArray((BufferedImage) drawWindow.getImage(),
						dim, res);
				neuralMemory.overwrite(values, comboBox.getSelectedItem().toString().charAt(0));
				BeautifulPrint.saveNeuralMemory(neuralMemory);
			}
			else if (e.getSource() == printBtn)
			{
				BeautifulPrint.printNeuralMemory(neuralMemory);
			}
		}
	};



	public static void main(String[] args)
	{
		new Paint().show();
	}

	public void show()
	{
		JFrame frame = new JFrame("Paint module");

		//building combobox
		String[] alphabet = "ABCDEFGHIJKLMOPQRSTUVWXYZ".split("(?<=\\G.{1})");
		comboBox = new JComboBox(alphabet);
		comboBox.setSelectedIndex(0);
		
		Container content = frame.getContentPane();

		content.setLayout(new BorderLayout());
		drawWindow = new DrawWindow();
		content.add(drawWindow, BorderLayout.CENTER);

		JPanel controls = new JPanel();
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(actionListener);
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(actionListener);
		printBtn = new JButton("PM");
		printBtn.addActionListener(actionListener);
		controls.add(clearBtn);
		controls.add(saveBtn);
		controls.add(printBtn);
		controls.add(comboBox);

		content.add(controls, BorderLayout.NORTH);
		//frame.setSize(516, 775);
		drawWindow.setDimensions(dim);
		frame.setSize(dim[1] * res + 16, dim[0] * res + 75);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//neuralMemory = new NeuralMemory(drawWindow);
		neuralMemory = BeautifulPrint.getMemoryFromFile();
	}

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
}
