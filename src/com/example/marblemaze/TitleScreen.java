package com.example.marblemaze;

import sofia.graphics.Color;
import sofia.graphics.TextShape;
import sofia.app.ShapeScreen;
import android.content.Intent;
import android.widget.TextView;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 *  The title screen of the application. Really? You needed this javadoc?
 *
 *  @author Dennis Lysenko (dlysenko)
 *  @version 2013.11.15
 */

public class TitleScreen
    extends ShapeScreen
{
    //private TextView tapToContinueText;
    private TextShape tapToContinueText;

    public void initialize() {
        //onTouchDown();
        tapToContinueText = new TextShape("Tap to continue...", getWidth()/2, getHeight()/2);
        tapToContinueText.setColor(Color.white);
        add(tapToContinueText);
        //tapToContinueText.animate()
    }

    // ----------------------------------------------------------
    /**
     * Captures touch events.
     * @param x
     * @param y
     */
    public void onTouchDown(float x, float y) {
        Intent intent = new Intent(this, MarbleMazeScreen.class);
        startActivity(intent);
    }

}
