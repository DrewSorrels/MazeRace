package com.example.marblemaze;

import android.widget.Button;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

/**
 * // -------------------------------------------------------------------------
 * /** Select the maze generation algorithm.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.11.15
 */
public class MazeChooserScreen
    extends ShapeScreen
{

    @SuppressWarnings("unused")
    private Button prim;
    private Button dfs;
    private MazeGenerator gen;

    public void initialize()
    {
        gen = new MazeGenerator();
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void primClicked() {

    }
}
