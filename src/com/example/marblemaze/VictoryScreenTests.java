package com.example.marblemaze;

import android.widget.Button;

/**
 * // -------------------------------------------------------------------------
 * /** Tests the (@link VictoryScreen) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class VictoryScreenTests
    extends student.AndroidTestCase<VictoryScreen>
{
    private Button lossReplay;


    // ----------------------------------------------------------
    /**
     * Create a new VictoryScreenTests object.
     */
    public VictoryScreenTests()
    {
        super(VictoryScreen.class);
    }


    // ----------------------------------------------------------
    /**
     * Tests the only button.
     */
    public void testButtons()
    {
        assertTrue(true);
        click(lossReplay);
    }

}
