package com.example.marblemaze;

import sofia.graphics.ShapeMotion;
import sofia.graphics.Color;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 *  This marble is an oval shape that rolls around on the phone screen.
 *
 *  @author Dennis Lysenko (dlysenko)
 *  @author Nicholas Kilmer (nkilmer8)
 *  @version 2013.12.06
 */

public class MarbleShape
    extends OvalShape
{
    private static final float DENSITY = 10f;
    private static final float RADIUS = 0.4f;
    private static final float FRICTION = 0.4f;
    private static final float RESTITUTION = 0.6f;


    // ----------------------------------------------------------
    /**
     * Create a new Marble object at the specified location.
     * @param x the starting x-location
     * @param y the starting y-location
     */
    public MarbleShape(float x, float y)
    {
        super(x, y, MarbleShape.RADIUS);
        this.setDensity(MarbleShape.DENSITY);
        this.setFillColor(Color.gray);
        this.setColor(Color.black);
        this.setFriction(MarbleShape.FRICTION);
        this.setShapeMotion(ShapeMotion.DYNAMIC); // dynamic = respond to force
        this.setRestitution(MarbleShape.RESTITUTION); // "bouncy-ness"
    }

    // ----------------------------------------------------------
    /**
     * sets the gravity of the marble
     */
    public void setGravityToZero()
    {
        this.setGravityScale(0);
    }
}
