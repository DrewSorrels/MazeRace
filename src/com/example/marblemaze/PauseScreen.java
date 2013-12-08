package com.example.marblemaze;

import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 *  Pauses the maze
 *
 *  @author Dennis Lysenko (dlysenko)
 *  @version 2013.12.03
 */

public class PauseScreen
    extends Screen
{

    // ----------------------------------------------------------
    /**
     * Goes back to the main maze screen.
     */
    public void resumeClicked() {
        super.onBackPressed();
    }

}
