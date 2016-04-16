package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main extends JFrame {

    private JPanel root;
    private JLabel label_information;
    private JButton button_loadImage;
    private JButton button_accept;
    private JButton button_disapprove;
    private JLabel label_imageSize;
    private JLabel label_image;
    private JLabel label_result;
    private JButton button_proceed;
    private JProgressBar progressBar_result;
    private BufferedImage img;

    private ImageParsingAlgorithm imageParsingAlgorithm;

    public Main()
    {
        setContentPane(root);
        setName("Sztuczna Inteligencja 2016 - J. Plebaniak, G. Prz�dka, M. Werda");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**Future use: User accepts Algorithms predition (plausible feature)*/
        button_accept.addActionListener(action -> label_information.setText(action.getActionCommand()));

        /**Future use: User disapproves Algorithms prediction (plausible feature)*/
        button_disapprove.addActionListener(action -> label_information.setText(action.getActionCommand()));


        /**
         * Listener enables choosing file with right file extension.
         * Reads image from file -> Scales it to put it into visible label.
         * Original image is saved to private variable Buffered Image img.
         * */
        button_loadImage.addActionListener(
                action -> { label_information.setText(action.getActionCommand());
                            final JFileChooser fileChooser = new JFileChooser("/");
                            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images","png","jpg","bmp");
                            fileChooser.setFileFilter(filter);
                            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                            {
                                try {
                                    img = ImageIO.read(fileChooser.getSelectedFile());
                                    int imgWidth = img.getWidth() > 10 ? 300 : img.getWidth()*100; //Brutal, but easy temporary solution
                                    int imgHeight = img.getHeight() > 10 ? 400 : img.getHeight()*100;
                                    ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(imgWidth,imgHeight, Image.SCALE_SMOOTH));
                                    label_imageSize.setText(img.getWidth() + " x " + img.getHeight());
                                    label_image.setIcon(imageIcon);
                                    label_image.setText("");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        //Progress bar - currently just for mock use
        //Shows simple logic behind
        progressBar_result.setMaximum(5);
        progressBar_result.setValue(0);
        button_proceed.addActionListener(action ->
        {
            if(img != null)
            {
                label_information.setText("Processing... Please wait.");
                Thread t = new Thread(
                        ()->
                        {
                            try {
                                for (int i =0; i < 6; i++)
                                {
                                    progressBar_result.setValue(i);

                                        Thread.sleep(1000);

                                }
                                label_information.setText("Please check result");
                                label_result.setText("Result currently unavailable");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                );
                t.start();

            }
            else
            {
                label_information.setText("Please choose image to analyze");
            }

        });

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {


        int[] simpleTsign = {   1, 1,  1,
                                0, 1,  0,
                                0, 1,  0,
                                0, 1,  0};

      //  DHGN dhgn = new DHGN(simpleTsign,3);

        Main app = new Main();
    }
}
