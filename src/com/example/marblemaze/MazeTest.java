package com.example.marblemaze;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * tests the Maze class
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @version 2013.12.08
 */
public class MazeTest
    extends TestCase
{
    private Maze test;


    public void setUp()
    {
        test = new Maze(2, 2);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testGetWallFromCell()
    {
        Wall w = test.getWallFromCell(test.getCell(1, 1), 0);
        assertTrue(w.isHorizontal());
        Wall x = test.getWallFromCell(test.getCell(1, 1), 1);
        assertFalse(x.isHorizontal());
    }


    // ----------------------------------------------------------
    /**
     * tests the getAdjacentWalls method
     */
    public void testGetAdjacentWalls()
    {
        Wall w = test.getWallFromCell(test.getCell(1, 1), 0);
        Wall x = test.getWallFromCell(test.getCell(1, 1), 2);
        Wall y = test.getWallFromCell(test.getCell(1, 1), 3);
        Wall z = test.getWallFromCell(test.getCell(1, 1), 1);
        assertEquals(test.getAdjacentWalls(test.getCell(1, 1)).size(), 4);
        test.removeWall(w);
        test.removeWall(y);
        test.removeWall(x);
        test.removeWall(z);
        assertEquals(test.getAdjacentWalls(test.getCell(1, 1)).size(), 0);
    }


    // ----------------------------------------------------------
    /**
     * tests the getStart and getGoal methods
     */
    public void testStartGoal()
    {
        assertEquals(test.getCell(0, 0), test.getStart());
        assertEquals(test.getCell(1, 1), test.getGoal());
    }


    // ----------------------------------------------------------
    /**
     * tests the getAdjacentCells method
     */
    public void testGetAdjacentCells()
    {
        Wall w = test.getWallFromCell(test.getCell(1, 1), 0);
        Wall x = test.getWallFromCell(test.getCell(1, 1), 2);
        assertEquals(test.getAdjacentCells(w).size(), 2);
        assertEquals(test.getAdjacentCells(x).size(), 0);
    }


    // ----------------------------------------------------------
    /**
     * tests the height and width methods
     */
    public void testHeightWidth()
    {
        assertEquals(test.height(), 2);
        assertEquals(test.width(), 2);
    }

    // ----------------------------------------------------------
    /**
     * tests the inBounds method
     */
    public void testInBounds()
    {
        assertTrue(test.inBounds(test.getCell(1, 1)));
        assertFalse(test.inBounds(new Cell(4,4)));
        assertFalse(test.inBounds(4,4));
        assertTrue(test.inBounds(1,1));
    }
}
