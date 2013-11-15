package com.example.marblemaze;

import sofia.graphics.ShapeMotion;
import sofia.graphics.Color;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 *  This marble is an oval shape that rolls around on the phone screen.
 *
 *  @author Dennis Lysenko (dlysenko)
 *  @version 2013.11.14
 */

public class Marble
    extends OvalShape
{
    private static final float DENSITY = 1.0f;
    private static final float RADIUS = 1.0f;


    // ----------------------------------------------------------
    /**
     * Create a new Marble object at the specified location.
     * @param x the starting x-location
     * @param y the starting y-location
     */
    public Marble(float x, float y)
    {
        super(x, y, Marble.RADIUS);
        this.setDensity(Marble.DENSITY);
        this.setFillColor(Color.gray);
        this.setColor(Color.black);
        this.setFriction(2.0f);
        this.setShapeMotion(ShapeMotion.DYNAMIC); // dynamic = respond to force
        this.setRestitution(0.4f); // "bouncy-ness"
    }

}
