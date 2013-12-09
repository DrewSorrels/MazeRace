package com.example.marblemaze;

import java.io.ObjectOutputStream.PutField;
import android.content.Intent;

// -------------------------------------------------------------------------
/**
 * Tests the MazeScreen GUI.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.08
 */
public class MazeScreenTests
    extends student.AndroidTestCase<MazeScreen>
{

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeScreenTests()
    {
        super(MazeScreen.class);
        Intent i = new Intent();
        i.putExtra("algorithm", "dfs");
        i.putExtra("enemies", true);
        i.putExtra("blinkingWalls", true);
        i.putExtra("holes", true);
    }

    /**
     * Sets up the test.
     */
    public void setUp()
    {
        // ugh
    }


    // ----------------------------------------------------------
    /**
     * Asserts that you can move the marble on the screen.
     */
    public void testScreenWorks()
    {
        Intent intent = new Intent(getScreen(), MazeScreen.class);
        intent.putExtra("algorithm", "test");
        intent.putExtra("slalom", false);
        intent.putExtra("enemies", false);
        intent.putExtra("blinkingWalls", false);
        intent.putExtra("holes", false);
        getScreen().startActivity(intent);

        getScreen().moveMarble(1, 1);

        assertNotNull(getScreen());
        assertNotNull(intent);
    }
}
