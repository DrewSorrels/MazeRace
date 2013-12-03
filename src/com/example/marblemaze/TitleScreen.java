package com.example.marblemaze;

import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.TextShape;

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
    private TextShape tapToContinueText;

    public void initialize() {
        getCoordinateSystem().width(100).height(50);

        tapToContinueText = new TextShape("Tap to continue...", 0, 0);
        tapToContinueText.setColor(Color.white);
        tapToContinueText.setTypeSize(1f);

        // Center the text on screen
        float leftBound = (getWidth() - tapToContinueText.getWidth()) / 2;
        float topBound = (getHeight() - tapToContinueText.getHeight()) / 2;
        tapToContinueText.setPosition(topBound, leftBound);

        tapToContinueText.setBounds(new RectF(0, 0, 100, 50));

        ButtonShape btn = new ButtonShape("Test", 5, new PointF(200, 200));
        add(btn);

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
