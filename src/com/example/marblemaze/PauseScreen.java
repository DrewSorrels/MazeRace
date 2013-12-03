package com.example.marblemaze;

import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
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
