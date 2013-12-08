package com.example.marblemaze;

import android.graphics.RectF;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the {@link Cell} class.
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */
public class TestCell
    extends TestCase
{
    private Cell test;


    @SuppressWarnings("unused")
    /**
     * sets up the test case
     */
    public void setUp()
    {
        RectF best = new RectF(0, 0, 0, 0);
        test = new Cell(2, 2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getNumWalls() method.
     */
    public void testGetNumWalls()
    {
        assertEquals(test.getNumWalls(), 4);
        test.setWall(0, false);
        assertEquals(test.getNumWalls(), 3);
        test.setWall(1, false);
        test.setWall(2, false);
        test.setWall(3, false);
        assertEquals(test.getNumWalls(), 0);
    }


    // ----------------------------------------------------------
    /**
     * tests the getAccessibleNeighbors method
     */
    public void testGetAccessibleNeighbors()
    {
        assertEquals(test.getAccessibleNeighbors().size(), 0);
        test.setWall(0, false);
        test.setWall(1, false);
        test.setWall(2, false);
        test.setWall(3, false);
        assertEquals(test.getAccessibleNeighbors().size(), 4);
    }


    // ----------------------------------------------------------
    /**
     * tests the get Walls method
     */
    public void testGetWalls()
    {
        assertEquals(test.getWalls().size(), 4);
        test.setWall(0, false);
        test.setWall(1, false);
        test.setWall(2, false);
        test.setWall(3, false);
        assertEquals(test.getWalls().size(), 0);
    }


    // ----------------------------------------------------------
    /**
     * tests the getRandomWallIndex method
     */
    public void testGetRandomWallIndex()
    {
        assertTrue(test.getRandomWallIndex() < 4);
    }


    // ----------------------------------------------------------
    /**
     * tests the oppositeWall method
     */
    public void testOppositeWall()
    {
        assertEquals(test.oppositeWall(2), 0);
    }


    // ----------------------------------------------------------
    /**
     * tests the north, south, east, west, getX, and getY methods
     */
    public void testCardinalDirections()
    {
        assertEquals(test.north().getY(), 1);
        assertEquals(test.south().getY(), 3);
        assertEquals(test.east().getX(), 3);
        assertEquals(test.west().getX(), 1);
    }


    // ----------------------------------------------------------
    /**
     * tests the isVisited and visitCell methods
     */
    public void testVisited()
    {
        assertFalse(test.isVisited());
        test.visitCell();
        assertTrue(test.isVisited());
    }


    // ----------------------------------------------------------
    /**
     * tests the equals method
     */
    public void testEquals()
    {
        assertFalse(test.equals("not a Cell"));
        assertTrue(test.equals(test));
    }


    // ----------------------------------------------------------
    /**
     * tests the getBounds method
     */
    public void testGetBounds()
    {
        RectF rectTest = new RectF(2, 2, 3, 3);
        assertEquals(test.getBounds(), rectTest);
    }
}
