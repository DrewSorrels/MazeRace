package com.example.marblemaze.weapons;

import com.example.marblemaze.Maze;
import com.example.marblemaze.Wall;
import com.example.marblemaze.MarbleShape;
import android.graphics.PointF;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the (@link Laser) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class LaserTests
    extends TestCase
{
    private Laser las;


    /**
     * Sets up tests.
     */
    public void setUp()
    {
        las = new Laser(0, 0, 0);
    }


    // ----------------------------------------------------------
    /**
     * Tests move method.
     */
    public void testMove()
    {
        las.move(12, 3);
        assertEquals(new PointF(0, -3), las.getLinearVelocity());
        las = new Laser(0, 0, 1);
        las.move(1, 1);
        las = new Laser(0, 0, 2);
        las.move(1, 1);
        las = new Laser(0, 0, 3);
        las.move(1, 21);
        las = new Laser(0, 0, 4);
        las.move(1, 12);
    }


    /**
     * Tests methods in Laser.
     */
    public void testLaser()
    {
        las.onCollisionWith(new MarbleShape(1, 1));
        las.onCollisionWith(new Wall());

        las.addObserver(new Maze(2, 2));
        las.notify();

        assertEquals(las, las.getShape());
        las.remove();

    }

}
