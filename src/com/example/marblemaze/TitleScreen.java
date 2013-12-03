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
        tapToContinueText = new TextShape("Tap to continue...", 0, 0);
        tapToContinueText.setColor(Color.white);
        tapToContinueText.setTypeSize(24f);

        // Center the text on screen
        float leftBound = (getWidth() - tapToContinueText.getWidth()) / 2;
        float topBound = (getHeight() - tapToContinueText.getHeight()) / 2;
        tapToContinueText.setPosition(topBound, leftBound);

        System.out.println(leftBound);
        System.out.println(topBound);

        add(tapToContinueText);
        //tapToContinueText.animate()
    }

    // ----------------------------------------------------------
    /**
     * Captures touch events.
     * @param x the x-coordinate of the touch event
     * @param y the y-coordinate of the touch event
     */
    public void onTouchDown(float x, float y) {
        Intent intent = new Intent(this, MarbleMazeScreen.class);
        startActivity(intent);
    }

}
