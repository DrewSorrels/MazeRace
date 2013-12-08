package com.example.marblemaze;

import android.content.Intent;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * The screen that the user will see if they win
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @version 2013.12.07
 */
public class VictoryScreen
    extends Screen
{

    // ----------------------------------------------------------
    /**
     * gives the user the option to replay the game
     */
    public void replayClicked()
    {
        Intent intent = new Intent(this, MazeChooserScreen.class);
        startActivity(intent);
    }
}
