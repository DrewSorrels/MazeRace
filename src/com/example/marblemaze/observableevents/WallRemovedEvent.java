package com.example.marblemaze.observableevents;

import com.example.marblemaze.Wall;

// -------------------------------------------------------------------------
/**
 * Represents a wall being removed from the maze.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */
public class WallRemovedEvent
{
    private Wall walle;


    // ----------------------------------------------------------
    /**
     * Create a new WallRemovedEvent object.
     *
     * @param walle
     *            the wall that was removed
     */
    public WallRemovedEvent(Wall walle)
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
