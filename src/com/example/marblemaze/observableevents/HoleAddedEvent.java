package com.example.marblemaze.observableevents;

import com.example.marblemaze.Hole;

// -------------------------------------------------------------------------
/**
 * Represents a new hole being added to a maze.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */

public class HoleAddedEvent
{

    private Hole addedHole;


    // ----------------------------------------------------------
    /**
     * Create a new HoleRemovedEvent object.
     *
     * @param addedHole
     *            the hole that was removed
     */
    public HoleAddedEvent(Hole addedHole)
    {
        this.addedHole = addedHole;
    }


    // ----------------------------------------------------------
    /**
     * Returns the hole in concern.
     *
     * @return the walle
     */
    public Hole getHole()
    {
        return addedHole;
    }
}
