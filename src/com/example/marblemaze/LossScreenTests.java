package com.example.marblemaze;

import android.widget.Button;

// -------------------------------------------------------------------------
/**
 * Tests the (@link LossScreen) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class LossScreenTests
    extends student.AndroidTestCase<LossScreen>
{
    private Button lossPlayAgain;


    // ----------------------------------------------------------
    /**
     * Create a new LossScreenTests object.
     */
    public LossScreenTests()
    {
        super(LossScreen.class);
    }


    // ----------------------------------------------------------
    /**
     * Tests the only button on the screen.
     */
    public void testButtons()
    {
        assertTrue(lossPlayAgain.isEnabled());
        click(lossPlayAgain);

    }

}
