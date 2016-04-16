package com.company;

import javax.swing.*;

public class Main extends JFrame {

    private JPanel root;
    private JLabel label_information;
    private JButton button_loadImage;
    private JButton button_accept;
    private JButton button_disapprove;

    private ImageParsingAlgorithm imageParsingAlgorithm;

    public Main(ImageParsingAlgorithm imageParsingAlgorithm)
    {
        setContentPane(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button_accept.addActionListener(action -> label_information.setText(action.getActionCommand()));
        button_loadImage.addActionListener(action -> label_information.setText(action.getActionCommand()));
        button_disapprove.addActionListener(action -> label_information.setText(action.getActionCommand()));

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {


        int[] simpleTsign = {   1, 1,  1,
                                0, 1,  0,
                                0, 1,  0,
                                0, 1,  0};

      //  DHGN dhgn = new DHGN(simpleTsign,3);

        Main app = new Main(new DHGN(null));
    }
}
