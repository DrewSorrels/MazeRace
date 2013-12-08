package com.example.marblemaze;

import android.graphics.RectF;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 * Creates holes that will cause the user to lose upon entering said hole.
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @version 2013.12.07
 */
public class Hole
    extends OvalShape
{
    private collisionHole coll;


    // ----------------------------------------------------------
    /**
     * Create a new Hole object.
     *
     * @param bounds
     *            is the bounds of the object
     */
    public Hole(RectF bounds)
    {
        super(bounds);
        coll =
            new collisionHole(
                ((bounds.right - bounds.left) / 2),
                (bounds.bottom - bounds.top) / 2);
    }
    // ----------------------------------------------------------
    /**
     * this method will return the collision hole
     * @return the inner collision hole
     */
    public collisionHole getCollisionHole()
    {
        return coll;
    }


    // -------------------------------------------------------------------------
    /**
     *  This class creates an inner collision hole within the hole. This way
     *  the marble will only disappear if the user gets completely inside the
     *  hole.
     *
     *  @author Nicholas Kilmer(nkilmer8)
     *  @version 2013.12.07
     */
    public class collisionHole
        extends OvalShape
    {
        // ----------------------------------------------------------
        /**
         * Create a new collisionHole object.
         * @param x is the x - coordinate
         * @param y is the y - coordinate
         */
        public collisionHole(float x, float y)
        {
            super(x, y, .01f);
        }


        /**
         * this method declares what will happen when the user runs into a hole
         *
         * @param first
         *            is the marble
         */
        public void onCollisionWith(MarbleShape first)
        {
            first.animate(1000).rotation(720).alpha(0).removeWhenComplete()
                .play();
        }
    }

}
