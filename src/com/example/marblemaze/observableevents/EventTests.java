package com.example.marblemaze.observableevents;

import com.example.marblemaze.weapons.LaserSpawner;
import com.example.marblemaze.Wall;
import com.example.marblemaze.MarbleShape;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests all event cases that are not normally called.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class EventTests
    extends TestCase
{
    private MarbleRemovedEvent      marbleE;
    private WallAddedEvent          wallE;
    private WeaponSpawnerAddedEvent wepE;


    /**
     * Sets up the tests.
     */
    public void setUp()
    {
        marbleE = new MarbleRemovedEvent(new MarbleShape(12, 12));
        wallE = new WallAddedEvent(new Wall(12, 12, true));
        wepE = new WeaponSpawnerAddedEvent(new LaserSpawner(1, 1, 1000, 0));
    }


    /**
     * Tests some methods in each event.
     */
    public void testEvents()
    {
        assertNotNull(marbleE.getMarble());
        assertNotNull(wallE.getWall());
        assertNotNull(wepE.getWeaponSpawner());
    }

}
