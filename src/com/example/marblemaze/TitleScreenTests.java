package com.example.marblemaze;

import android.widget.Button;

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
        assertTrue(startGame.isEnabled());
        // prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(startGame);
        assertNotNull(startGame);
    }
}
