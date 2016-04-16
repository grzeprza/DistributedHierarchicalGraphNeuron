package com.company;

import javax.swing.*;

/**
 * Created by GrzegorzLap on 2016-04-16.
 */
public interface ImageParsingAlgorithm<T> {
    default void parseImage(ImageIcon imageIcon)
    {
        System.out.println(this.getClass().getSimpleName() + " has NOT IMPLEMENTED image parsing");
    }
}
