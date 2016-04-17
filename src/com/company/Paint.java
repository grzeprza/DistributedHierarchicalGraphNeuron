package com.company;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint
{
    JButton clearBtn;
    DrawWindow drawWindow;
    ActionListener actionListener = new ActionListener()
    {
	@Override
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == clearBtn)
	    {
		drawWindow.clear();
	    }
	}
    };
    
    public static void main()
    {
	new Paint().show();
    }
    
    public void show()
    {
	JFrame frame = new JFrame("Paint module");
	Container content = frame.getContentPane();
	
	content.setLayout(new BorderLayout());
	drawWindow = new DrawWindow();
	content.add(drawWindow, BorderLayout.CENTER);
	
	JPanel controls = new JPanel();
	JButton clearButton = new JButton("Clear");
	clearButton.addActionListener(actionListener);
	
	controls.add(clearButton);
	
	content.add(controls, BorderLayout.NORTH);
	frame.setSize(400, 400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}