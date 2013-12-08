package com.example.marblemaze;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the MazeChooserScreen and all it's methods.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class MazeChooserScreenTests
    extends student.AndroidTestCase<MazeChooserScreen>
{
    private CheckBox holesCheckbox;
    private CheckBox blinkingWallsCheckbox;
    private CheckBox enemiesCheckbox;
    private CheckBox slalomCheckbox;
    private Button   dfs;
    private Button   prim;


    // ----------------------------------------------------------
    /**
     * Create a new MazeChooserScreenTests object.
     */
    public MazeChooserScreenTests()
    {
        super(MazeChooserScreen.class);
    }


    // ----------------------------------------------------------
    /**
     * Tests most buttons and maze gen.
     */
    public void testButtons()
    {
        click(holesCheckbox);
        click(blinkingWallsCheckbox);
        click(enemiesCheckbox);
        click(slalomCheckbox);

        assertTrue(slalomCheckbox.isChecked());
        //this.prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(dfs);

    }


    // ----------------------------------------------------------
    /**
     * Tests the prims button.
     */
    public void testButton2()
    {
        assertFalse(slalomCheckbox.isChecked());
        //prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(prim);
    }
}
