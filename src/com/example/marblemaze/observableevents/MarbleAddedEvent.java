package com.example.marblemaze.observableevents;

import com.example.marblemaze.MarbleShape;

// -------------------------------------------------------------------------
/**
 * Represents a new marble being added to a maze.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */

public class MarbleAddedEvent
{

    private MarbleShape oldMarble;
    private MarbleShape newMarble;


    // ----------------------------------------------------------
    /**
     * Create a new MarbleAddedEvent object.
     *
     * @param oldMarble
     *            the old marble (could be null)
     * @param newMarble
     *            the new marble that was added
     */
    public MarbleAddedEvent(MarbleShape oldMarble, MarbleShape newMarble)
    {
        this.oldMarble = oldMarble;
        this.newMarble = newMarble;
    }


    // ----------------------------------------------------------
    /**
     * Returns the old marble
     *
     * @return the oldMarble
     */
    public MarbleShape getOldMarble()
    {
        return oldMarble;
    }


    // ----------------------------------------------------------
    /**
     * Returns the new marble
     *
     * @return the newMarble
     */
    public MarbleShape getNewMarble()
    {
        return newMarble;
    }

}
