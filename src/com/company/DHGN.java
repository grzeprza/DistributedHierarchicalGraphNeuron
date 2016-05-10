package com.company;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by GrzegorzLap on 2016-04-04.
 */

public class DHGN
{
    int[][] imageInIntegers;

    public  DHGN(int[][] imageInIntegers )
    {
        this.imageInIntegers = imageInIntegers;
    }

    public HashMap<Character, Integer> compareToSet(HashMap<Character, NeuralStructure> givenSet)
    {
        HashMap<Character, Integer> result = new HashMap<>();
        for(Character c = 'A'; c <= 'Z'; c++)
        {
            result.put(c, matchPoints(imageInIntegers, givenSet.get(c).getArray()));
        }
        return result;
    }

    private Integer matchPoints(int[][] toCompare, int[][] pattern)
    {
        int threadId = 0;
        Integer points = new Integer(0);
        for(int i = 0; i < pattern.length; i++)
        {
            points += new SIModule(toCompare[i], pattern[i], threadId).getValue();
            threadId++;
        }
        return points;
    }

    /**Hardcoded 'knowledge-base'*/
//    static int T[][] = {   {1, 1, 1},
//            {0, 1, 0},
//            {0, 1, 0},
//            {0, 1, 0}};


    private class SIModule implements Runnable
    {
        int[] binarySignature;
        int[] binaryPattern;
        int value;

        /**Empty constructor not allowed*/
        private SIModule(){}

        /**Public constructor with binary matrix representing picture*/
        public SIModule(int[] binarySignature, int[] binaryPattern, int id)
        {
            this.binarySignature = binarySignature;
            this.binaryPattern = binaryPattern;
            this.value = 0;
            Thread t = new Thread(this, Integer.toString(id));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            for(int i = 0; i < binaryPattern.length; i++)
            {
                if(binaryPattern[i] == binarySignature[i])
                    value++;
            }
        }

        public int getValue()
        {
            return value;
        }
    }
}
