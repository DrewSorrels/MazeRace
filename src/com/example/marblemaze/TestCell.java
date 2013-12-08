package com.example.marblemaze;

import android.graphics.RectF;
import student.TestCase;

// -------------------------------------------------------------------------
/**
<<<<<<< HEAD
 *  Tests the {@link Cell} class.
 *
 *  @author Nicholas Kilmer (nkilmer8)
 *  @author Dennis Lysenko (dlysenko)
 *  @version 2013.12.07
 */
public class TestCell extends TestCase
{
    private Cell test;

    @SuppressWarnings("unused")
    /**
     * sets up the test case
     */
    public void setUp()
    {
        RectF best = new RectF(0, 0, 0, 0);
        test = new Cell(2,2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getNumWalls() method.
     */
    public void testGetNumWalls()
    {
        assertEquals(test.getNumWalls(), 4);
    }
}
