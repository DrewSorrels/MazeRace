package com.example.marblemaze;

import student.TestCase;
import android.graphics.RectF;
import android.widget.*;

// -------------------------------------------------------------------------
/**
 *  This is the test class for the cell class and its methods
 *
 *  @author Nicholas Kilmer (nkilmer8)
 *  @version 2013.12.07
 */
public class TestCell extends TestCase
{
    private Cell test;

    /**
     * sets up the test case
     */
    public void setUp()
    {
        RectF best = new RectF(0, 0, 0, 0);
        test = new Cell(2,2);
    }
    /**
     * tests the method of getNumWalls
     */
    public void testGetNumWalls()
    {
        assertEquals(test.getNumWalls(), 4);
    }
}
