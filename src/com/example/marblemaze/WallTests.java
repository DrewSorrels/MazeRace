package com.example.marblemaze;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the (@link Wall) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class WallTests
    extends TestCase
{
    private Wall w;
    private Wall w1;


    public void setUp()
    {
        w = new Wall(0, 0, true);
        w1 = new Wall(1, 1, false);
    }


    // ----------------------------------------------------------
    /**
     * Tests the destroyWall method.
     */
    public void testDestroyWall()
    {
        w.destroyWall();
        assertFalse(w.exists());
    }


    /**
     * Tests various cases for setWall.
     */
    public void testSetWall()
    {
        w.setWall(false);
        w.setWall(false);
        w.setWall(true);
    }


    /**
     * Tests methods for wall.
     */
    public void testWall()
    {
        boolean equal = w.equals(new Maze(2, 2));
        assertFalse(equal);

        w.setWidthAndHeight();
        w1.setWidthAndHeight();

        w.setBlinking(false);
        w.setBlinking(false);

        w1.setBlinking(true);
        w1.setBlinking(true);

        w.addObserver(new Maze(2, 2));
        assertTrue(w1.isBlinking());

    }
}
