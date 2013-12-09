package com.example.marblemaze.weapons;

import android.graphics.PointF;
import com.example.marblemaze.observableevents.BulletAddedEvent;
import com.example.marblemaze.MarbleShape;
import com.example.marblemaze.Maze;
import com.example.marblemaze.Wall;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test the (@link Rocket) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class RocketTests
    extends TestCase
{
    private Rocket rock;


    /**
     * Sets up all the tests.
     */
    public void setUp()
    {
        rock = new Rocket(0, 0, 0);
    }


    // ----------------------------------------------------------
    /**
     * Tests move method.
     */
    public void testMove()
    {
        rock.move(12, 3);
        assertEquals(new PointF(0, -3), rock.getLinearVelocity());
        rock = new Rocket(0, 0, 1);
        rock.move(1, 1);
        rock = new Rocket(0, 0, 2);
        rock.move(1, 1);
        rock = new Rocket(0, 0, 3);
        rock.move(1, 21);
        rock = new Rocket(0, 0, 4);
        rock.move(1, 12);
    }


    /**
     * Tests methods in Rocket.
     */
    public void testLaser()
    {
        rock.onCollisionWith(new MarbleShape(1, 1));
        rock.onCollisionWith(new Wall());

        rock.addObserver(new Maze(2, 2));
        rock.notifyObservers(new BulletAddedEvent(rock));

        assertEquals(rock, rock.getShape());
        rock.remove();

    }

}
