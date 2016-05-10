package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeautifulPrint
{
    public static void printNeuralMemory(NeuralMemory nm)
    {
        for(NeuralStructure ns: nm.getHashMap().values())
        {
            System.out.println(String.valueOf(ns.getCharacter()) + " is represented as:");
            BeautifulPrint.printNeuralStructure(ns);
            System.out.println("");
            System.out.println("");
        }
    }

    public static void printNeuralStructure(NeuralStructure ns)
    {
        for(int[] line : ns.getArray())
        {
            System.out.printf(Arrays.toString(line));
            System.out.println("");
        }
    }

    public static void saveNeuralMemory(NeuralMemory memory)
    {
        File yourFile = new File("memory.txt");
        if(!yourFile.exists()) {
            try
            {
                yourFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try {
            PrintWriter writer = new PrintWriter("memory.txt", "UTF-8");
            for(NeuralStructure ns: memory.getHashMap().values())
            {
                for(int[] line: ns.getArray())
                {
                    for(int num: line)
                    {
                        writer.print(num);
                    }
                    writer.println();
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void printMatchingPoints(HashMap<Character, Integer> map)
    {
        for(Character c = 'A'; c <= 'Z'; c++)
        {
            System.out.printf(c + ":   " + String.valueOf(map.get(c)));
            System.out.println();
        }
        System.out.println();
    }

    public static void printMostMatchingPoints(HashMap<Character, Integer> map)
    {
        CharVal charVal = getMax(map);
        System.out.println("Most matching character: " + charVal.character + " with " +
                charVal.getMatchingPercentage() + " percent");
        charVal = getMax(map);
        System.out.println("Second most matching charater: " + charVal.character + " with " +
                charVal.getMatchingPercentage() + " percent");
        charVal = getMax(map);
        System.out.println("Second most matching charater: " + charVal.character + " with " +
                charVal.getMatchingPercentage() + " percent");
        System.out.println("");


//        System.out.println("Most matching character: " + getMax(map).character + " with " +
//                Float.toString((float)getMax(map).val / ((float)(Paint.dim[0] * Paint.dim[1]))) + " % of match");
//        System.out.println("Second most matching character: " + getMax(map).character + " with " +
//                Float.toString(getMax(map).val / (Paint.dim[0] * Paint.dim[1])) + " % of match");
//        System.out.println("Third most matching character: " + getMax(map).character + " with " +
//                Float.toString(getMax(map).val / (Paint.dim[0] * Paint.dim[1])) + " % of match");
    }

    public static CharVal getMax(HashMap<Character, Integer> map)
    {
        CharVal max = new CharVal(' ', 0);
        for(Character c = 'A'; c <= 'Z'; c++)
        {
            if(map.get(c) > max.val)
            {
                max.character = c;
                max.val = map.get(c);
            }
        }
        map.put(max.character, 0);
        return max;
    }

    public static NeuralMemory getMemoryFromFile()
    {
        int[] dim = Paint.dim;
        try {
            FileReader fileReader = new FileReader("memory.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            NeuralMemory memory = new NeuralMemory(dim[0], dim[1]);
            String line;
            for(char letter = 'A'; letter <= 'Z'; letter++)
            {
                int[][] array = new int[dim[0]][dim[1]];
                for(int i = 0; i < dim[0]; i++)
                {
                    line = reader.readLine();
                    String[] lineCharArray = line.split("");
                    for(int j = 0; j < lineCharArray.length; j++)
                    {
                        array[i][j] = Integer.parseInt(lineCharArray[j]);
                    }
                }
                memory.overwrite(array, letter);
            }
            return memory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

