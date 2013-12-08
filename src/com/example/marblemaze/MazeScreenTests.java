package com.example.marblemaze;

import android.content.Intent;


//-------------------------------------------------------------------------
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
    public MazeScreenTests() {
        super(MazeScreen.class);
    }

    public void assertScreenWorks() {
        Intent intent = new Intent(getScreen(), MazeScreen.class);
        intent.putExtra("algorithm", "test");
        intent.putExtra("slalom", false);
        intent.putExtra("enemies", false);
        intent.putExtra("blinkingWalls", false);
        intent.putExtra("holes", false);
        getScreen().startActivity(intent);
    }
}
