package com.company;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
/**
 * Created by Jakub on 18.04.2016.
 */
public class ImageToTab {
    public static int[] ImageToTab(String path){

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
        }

        int[] tab =  new int[img.getHeight() * img.getWidth()];
        int clr=0;
        int index = 0;
        for(int i = 0; i < img.getHeight(); i++ ) {
            for (int j = 0; j < img.getWidth(); j++) {
                clr=  img.getRGB(j,i);
                if(clr<0) clr=clr*(-1);
                if(clr > 0x7FFFFF)
                {
                    tab[index] = 1;
                }else{
                    tab[index] = 0;
                }
                index++;
            }
        }

        return tab ;
    }
}
