package com.company;


import java.io.*;
import java.util.Arrays;

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
