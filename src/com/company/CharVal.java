package com.company;

public class CharVal
{
    public int val;
    public Character character;

    public CharVal(Character c, int v)
    {
        val = v;
        character = c;
    }

    public float getMatchingPercentage()
    {
        return (float)val / ((float)(Paint.dim[0] * (float)Paint.dim[1])) * 100;
    }
}
