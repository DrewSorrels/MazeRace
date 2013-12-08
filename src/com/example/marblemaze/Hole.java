package com.example.marblemaze;

import android.graphics.RectF;
import sofia.graphics.Color;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 * Creates holes that will cause the user to lose upon entering said hole.
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.08
 */
public class Hole
    extends OvalShape
{
    private CollisionHole coll;


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
        setActive(false);
        setFillColor(Color.black);
        coll =
            new CollisionHole(
                ((bounds.right + bounds.left) / 2),
                (bounds.bottom + bounds.top) / 2);
    }
    // ----------------------------------------------------------
    /**
     * this method will return the collision hole
     * @return the inner collision hole
     */
    public CollisionHole getCollisionHole()
    {
        return coll;
    }


    // -------------------------------------------------------------------------
    /**
     *  This class creates an inner collision hole within the hole. This way
     *  the marble will only disappear if the user gets completely inside the
     *  hole.
     *
     *  @author Nicholas Kilmer (nkilmer8)
     *  @version 2013.12.07
     */
    public class CollisionHole
        extends OvalShape
    {
        // ----------------------------------------------------------
        /**
         * Create a new collisionHole object.
         * @param x is the x - coordinate
         * @param y is the y - coordinate
         */
        public CollisionHole(float x, float y)
        {
            super(x, y, .01f);
            System.out.println("" + x + ", " + y);
            setFillColor(Color.gray);
        }


        /**
         * this method declares what will happen when the user runs into a hole
         *
         * @param first
         *            is the marble
         */
        public void onCollisionWith(MarbleShape first)
        {
            if (!first.isDying()) {
                first.animate(1000).rotation(720).color(Color.blue).alpha(0).removeWhenComplete()
                    .play();
                first.setDying(true);
            }
        }
    }

}
