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
    JButton saveBtn;
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
    
    public static void main(String[] args)
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
	clearBtn = new JButton("Clear");
	clearBtn.addActionListener(actionListener);
	saveBtn = new JButton("Save");
	controls.add(clearBtn);
	
	content.add(controls, BorderLayout.NORTH);
	//frame.setSize(516, 775);
	frame.setSize(drawWindow.dim[0] * 50 + 16, drawWindow.dim[1] * 50 + 75);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}
