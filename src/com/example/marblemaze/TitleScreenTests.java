package com.example.marblemaze;

import android.content.Intent;
import android.widget.Button;
import cs2114.restaurant.RestaurantScreen;

// -------------------------------------------------------------------------
/**
 * Tests the (@link TitleScreen) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class TitleScreenTests
    extends student.AndroidTestCase<TitleScreen>
{
    private Button startGame;


    // ----------------------------------------------------------
    /**
     * Intentionally Left blank.
     */
    public void setUp()
    {
        // Does nothing
    }


    // ----------------------------------------------------------
    /**
     * Create a new TitleScreenTests object.
     */
    public TitleScreenTests()
    {
        super(TitleScreen.class);
    }


    /**
     * Tests the screen to make sure it goes to the next screen.
     */
    public void testScreen()
    {
        prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(startButton);
    }
}
