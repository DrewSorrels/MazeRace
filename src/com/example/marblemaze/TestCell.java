package com.example.marblemaze;

import student.TestCase;

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
