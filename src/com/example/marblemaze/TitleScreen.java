package com.example.marblemaze;

import android.content.Intent;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * The title screen of the application.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.03
 */

public class TitleScreen
    extends Screen
{

    public void initialize()
    {

    }


    // ----------------------------------------------------------
    /**
     * Captures pressing the Start Game button.
     */
    public void startGameClicked()
    {
        Intent intent = new Intent(this, MarbleMazeScreen.class);
        startActivity(intent);
    }

}
