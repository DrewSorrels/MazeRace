package com.example.marblemaze;

import android.widget.Button;

// -------------------------------------------------------------------------
/**
 * Tests the (@link PauseScreen) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class PauseScreenTests
    extends student.AndroidTestCase<PauseScreen>
{
    private Button resume;


    // ----------------------------------------------------------
    /**
     * Create a new PauseScreenTests object.
     */
    public PauseScreenTests()
    {
        super(PauseScreen.class);
    }


    // ----------------------------------------------------------
    /**
     * Tests the one button onScreen.
     */
    public void testButtons()
    {
        assertTrue(resume.isEnabled());
        click(resume);
    }
}
