package com.example.marblemaze;

import android.content.Intent;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * The screen that the user will see if they win
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.08
 */
public class LossScreen
    extends Screen
{

    // ----------------------------------------------------------
    /**
     * Gives the user an option to choose a new name.
     */
    public void lossPlayAnotherClicked() {
        Intent intent = new Intent(this, MazeChooserScreen.class);
        startActivity(intent);
    }
}
