package com.example.marblemaze;

import sofia.graphics.RectangleShape;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.TextShape;

// -------------------------------------------------------------------------
/**
 * The title screen of the application. Really? You needed this javadoc?
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.11.15
 */

public class TitleScreen
    extends ShapeScreen
{
    private TextShape tapToContinueText;


    public void initialize()
    {
        int height = 50;
        getCoordinateSystem().height(height);
        float ratio = getHeight() / 50;
        int width = (int)(getWidth() / ratio);

        tapToContinueText =
            new TextShape("Tap to continue...", width / 2, height / 2);
        tapToContinueText.setColor(Color.white);
        tapToContinueText.setTypeSize(1f);

        RectangleShape btn = new RectangleShape(0, 0, 5, 5);
        btn.setImage("pause_button");
        add(btn);

        add(tapToContinueText);
        // tapToContinueText.animate()
    }


    // ----------------------------------------------------------
    /**
     * Captures touch events.
     *
     * @param x
     *            the x-coordinate of the touch event
     * @param y
     *            the y-coordinate of the touch event
     */
    public void onTouchDown(float x, float y)
    {
        Intent intent = new Intent(this, MarbleMazeScreen.class);
        startActivity(intent);
    }

}
