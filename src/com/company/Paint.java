package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint
{

	// Here we can set a size of the tile and resolution of painting window

	int res = 50;
	int[] dim =
	{
		5, 7
	};
	JButton clearBtn;
	JButton saveBtn;
	DrawWindow drawWindow;

	NeuralMemory memory;
	
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
				NeuralMemory.shitToIntArray((BufferedImage) drawWindow.getImage(), dim, res);
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
	//TEMP part
//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//	frame.setUndecorated(true);

		Container content = frame.getContentPane();

		content.setLayout(new BorderLayout());
		drawWindow = new DrawWindow();
		content.add(drawWindow, BorderLayout.CENTER);

		JPanel controls = new JPanel();
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(actionListener);
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(actionListener);
		controls.add(clearBtn);
		controls.add(saveBtn);

		content.add(controls, BorderLayout.NORTH);
		//frame.setSize(516, 775);
		drawWindow.setDimensions(dim);
		frame.setSize(dim[0] * res + 16, dim[1] * res + 75);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
