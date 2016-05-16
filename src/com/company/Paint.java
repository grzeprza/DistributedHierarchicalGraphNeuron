package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Paint
{
	// Here we can set a size of the tile and resolution of painting window

	int res = 90;
	public static int[] dim = {7, 5};
	
	//Building the view

	BeautifulPrint beautifulPrint;
	JButton clearBtn;
	JButton saveBtn;
	JButton printBtn;
	JButton compareBtn;
	JButton readPatternBtn;
	JComboBox comboBox;
	DrawWindow drawWindow;
	NeuralMemory neuralMemory;

	JPanel informPanel;
	JTextArea textArea;
	JScrollPane jScrollPane;

	public volatile String message;

	public ActionListener actionListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == clearBtn)
			{
				drawWindow.clear();
				textArea.setText("Cleared.");
			} 
			else if (e.getSource() == saveBtn)
			{
				int[][] values = shitToIntArray((BufferedImage) drawWindow.getImage(),
						dim, res);
				neuralMemory.overwrite(values, comboBox.getSelectedItem().toString().charAt(0));
				BeautifulPrint.saveNeuralMemory(neuralMemory);
				textArea.setText(BeautifulPrint.message + "SAVED");
			}
			else if (e.getSource() == printBtn)
			{
				BeautifulPrint.printNeuralMemory(neuralMemory);
				textArea.setText(BeautifulPrint.message);
			}
			else if(e.getSource() == compareBtn)
			{
				int[][] values = shitToIntArray((BufferedImage) drawWindow.getImage(),
						dim, res);
				DHGN algo = new DHGN(values);
				//BeautifulPrint.printMatchingPoints(algo.compareToSet(neuralMemory.getHashMap()));
				BeautifulPrint.printMostMatchingPoints(algo.compareToSet(neuralMemory.getHashMap()));
				textArea.setText(BeautifulPrint.message);
			}
			else if(e.getSource() == readPatternBtn)
			{
				drawWindow.paintPattern(neuralMemory.getHashMap().get(comboBox.getSelectedItem().toString().charAt(0)).getArray());
				textArea.setText("Pattern displayed");
			}
		}
	};



	public static void main(String[] args)
	{

	}

	public void show()
	{
		JFrame frame = new JFrame("Paint module");

		//building combobox
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("(?<=\\G.{1})");
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
		compareBtn = new JButton("Comp");
		compareBtn.addActionListener(actionListener);
		readPatternBtn = new JButton("Patt");
		readPatternBtn.addActionListener(actionListener);
		controls.add(clearBtn);
		controls.add(saveBtn);
		controls.add(printBtn);
		controls.add(compareBtn);
		controls.add(readPatternBtn);
		controls.add(comboBox);

		content.add(controls, BorderLayout.NORTH);


		informPanel = new JPanel();
		textArea = new JTextArea(4,30);
		textArea.setEditable(true);
		 jScrollPane = new JScrollPane(textArea);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		informPanel.add(jScrollPane);
	//	informPanel.add(textArea);
	//	informPanel.setSize(200,400);
	//	jScrollPane.add(informPanel);
		content.add(informPanel, BorderLayout.SOUTH);


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
