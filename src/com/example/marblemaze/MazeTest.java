package com.example.marblemaze;

import com.example.marblemaze.observableevents.ObservableMazeComponent;
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
    private Maze largeTest;


    /**
     * sets up the test case
     */
    public void setUp()
    {
        test = new Maze(2, 2);
        test = new Maze(6, 6);
    }


    // ----------------------------------------------------------
    /**
     * tests the getWallFromCell method
     */
    public void testGetWallFromCell()
    {
        Wall w = test.getWallFromCell(test.getCell(1, 1), 0);
        assertTrue(w.isHorizontal());
        Wall x = test.getWallFromCell(test.getCell(1, 1), 1);
        assertFalse(x.isHorizontal());

        Wall y = test.getWallFromCells(test.getCell(1, 1), test.getCell(1, 0));
        assertTrue(y.isHorizontal());
        Wall z = test.getWallFromCells(test.getCell(1, 1), test.getCell(0, 1));
        assertFalse(z.isHorizontal());
    }


    // ----------------------------------------------------------
    /**
     * Tests assorted corner cases in Maze methods.
     */
    public void testCornerCases()
    {
        assertNull(test.findWall(-1, -1, true));
        test.removeWall(null);

        MazeGenerator gen = new MazeGenerator();
        gen.primMaze();
        assertTrue(gen.getMaze().getHoles().size() > 0);
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
        assertFalse(test.inBounds(new Cell(4, 4)));
        assertFalse(test.inBounds(4, 4));
        assertTrue(test.inBounds(1, 1));
    }


    /**
     * tests the getHoles and getWalls methods
     */
    public void testWallsHoles()
    {
        assertTrue(test.getHoles().size() < 10);
        assertEquals(test.getWalls().size(), 12);
    }


    /**
     * tests different Calls
     */
    public void testCalls()
    {
        test.addSpawners();
        largeTest.addSpawners();
        ObservableMazeComponent obs = new ObservableMazeComponent();
        Object event = new Object();
        test.update(obs, event);
        test.setBlinkingWalls(true);
        assertTrue(test.getHoles().size() < 10);
    }
}
