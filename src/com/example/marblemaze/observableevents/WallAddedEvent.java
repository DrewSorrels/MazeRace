package com.example.marblemaze.observableevents;

import com.example.marblemaze.Wall;

// -------------------------------------------------------------------------
/**
 * Represents a new wall being added to a maze or exists being set to true on an
 * existing wall.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */

public class WallAddedEvent
{

    private Wall walle;


    // ----------------------------------------------------------
    /**
     * Create a new WallRemovedEvent object.
     *
     * @param walle
     *            the wall that was removed
     */
    public WallAddedEvent(Wall walle)
    {
        this.walle = walle;
    }


    // ----------------------------------------------------------
    /**
     * Returns the wall in concern.
     *
     * @return the walle
     */
    public Wall getWall()
    {
        return walle;
    }
}
