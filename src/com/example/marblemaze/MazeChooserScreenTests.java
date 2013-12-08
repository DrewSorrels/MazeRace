package com.example.marblemaze;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import student.TestCase;

public class MazeChooserScreenTests
    extends student.AndroidTestCase<MazeChooserScreen>
{
    private CheckBox holesCheckbox;
    private CheckBox blinkingWallsCheckbox;
    private CheckBox enemiesCheckbox;
    private CheckBox slalomCheckbox;
    private Button   dfs;
    private Button   prim;


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
        this.prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(dfs);

    }


    // ----------------------------------------------------------
    /**
     * Tests the prims button.
     */
    public void testButton2()
    {
        prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(prim);
    }
}
