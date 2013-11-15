package com.example.marblemaze;

import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * A Controller for the marble maze game's main screen.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version Nov 14, 2013
 */

public class MarbleMazeScreen
    extends ShapeScreen
{
    private Marble squishy;


    @Override
    public void initialize()
    {
        getCoordinateSystem().width(50);

        // Apply standard Earth gravity to our scene.
        setGravity(0, 0);

        squishy = new Marble(15, 15);
        add(squishy);
        squishy.applyLinearImpulse(1, -1);
    }

}
