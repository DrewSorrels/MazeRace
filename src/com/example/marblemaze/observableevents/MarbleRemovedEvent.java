package com.example.marblemaze.observableevents;

import com.example.marblemaze.MarbleShape;

// -------------------------------------------------------------------------
/**
 * Represents a marble being removed from the maze (i.e. dying).
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */

public class MarbleRemovedEvent
{

    private MarbleShape marble;


    // ----------------------------------------------------------
    /**
     * Create a new MarbleRemovedEvent object.
     *
     * @param marble
     *            the marble that was killed
     */
    public MarbleRemovedEvent(MarbleShape marble)
    {
        this.marble = marble;
    }


    /**
     * Returns the marble.
     *
     * @return the marble
     */
    public MarbleShape getMarble()
    {
        return marble;
    }

}
