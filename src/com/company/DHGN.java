package com.company;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by GrzegorzLap on 2016-04-04.
 */
public class DHGN {

    static  char letterMatch[];
    static volatile boolean saveSem = true;

    private DHGN(){}
    public DHGN(Image imageToParse){}
    public  DHGN(int[] imageInIntegers, int ImageWidthToSeparate)
    {
        letterMatch = new char[imageInIntegers.length/ImageWidthToSeparate+1];

        for (int i = 0; i < imageInIntegers.length/ImageWidthToSeparate; i++) {
           // System.out.println(i*3 + " " + ((i+1)*3-1) ); //because our HARDCODED KNOWLEDGEBASE has 3 items in a row so we copy 0-2, 3-5,6-8, etc.
            new SIModule( Arrays.copyOfRange(imageInIntegers,i*3,(i+1)*3-1), i);
        }

        System.out.println(Arrays.toString(letterMatch));
    }


    /**Hardcoded 'knowledge-base'*/
    static int T[][] = {   {1, 1,  1},
            {0, 1,  0},
            {0, 1,  0},
            {0, 1,  0}};


    private class SIModule implements Runnable
    {
        int ID=0;
        int[] rowBinarySignature = null;

        /**Empty constructor not allowed*/
        private SIModule(){}

        /**Public constructor with binary matrix representing picture*/
        public SIModule(int[] binarySignature, int id)
        {
            this.ID = id;
            this.rowBinarySignature = binarySignature;
            new Thread(this,Integer.toString(id)).start();
        }

        @Override
        public void run() {
            boolean correctnessFlag = true;
            for (int i = 0; i < 2; i++) {
             //   System.out.println("THREAD " + this.ID + " == "+ T[ID][i] + " ? " + rowBinarySignature[i]);
                if(T[ID][i] != rowBinarySignature[i])
                {
                    correctnessFlag = false;
                    break;
                }
            }

            if(correctnessFlag)
            {
                synchronized (letterMatch) {
                    letterMatch[ID] = 'T';
                }
            }
        }
    }
}
