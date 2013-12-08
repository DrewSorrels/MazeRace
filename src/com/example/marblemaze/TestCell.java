package com.example.marblemaze;


import junit.framework.TestCase;
import android.graphics.RectF;
import android.widget.*;

public class TestCell extends TestCase
{
    private Cell test;

    public void setUp()
    {
        RectF best = new RectF(0, 0, 0, 0);
        test = new Cell(2,2);
    }

    public void testGetNumWalls()
    {
        assertEquals(test.getNumWalls(), 4);
    }
}
