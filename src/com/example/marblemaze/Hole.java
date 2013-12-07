package com.example.marblemaze;

import android.graphics.RectF;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 *  Creates holes that will cause the user to lose upon entering said hole.
 *
 *  @author Nicholas Kilmer (nkilmer8)
 *  @version 2013.12.07
 */
public class Hole extends OvalShape
{

    // ----------------------------------------------------------
    /**
     * Create a new Hole object.
     * @param bounds is the bounds of the object
     */
    public Hole(RectF bounds)
    {
        super(bounds);
    }

    /**
     * this method declares what will happen when the user runs into a hole
     * @param first is the marble
     */
    public void onCollisionWith(MarbleShape first)
    {
        first.animate(1000).rotation(720).alpha(0).removeWhenComplete()
        .play();
    }

}
