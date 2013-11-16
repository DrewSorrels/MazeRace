package com.example.marblemaze;

import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

/**
 * // -------------------------------------------------------------------------
/**
 *  Select the maze generation algorithm.
 *
 *  @author Drew
 *  @version Nov 15, 2013
 */
public class MazeChooserScreen extends ShapeScreen
{
    private RectangleShape prim;

    public void initialize() {
        prim = new RectangleShape(120, 250, 300, 150);
        prim.setFillColor(Color.gray.brighter());
        add(prim);
    }
}
