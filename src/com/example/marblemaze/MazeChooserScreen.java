package com.example.marblemaze;

import android.content.Intent;
import android.widget.CheckBox;
import sofia.app.Screen;

/**
 * // -------------------------------------------------------------------------
 * /** Select the maze generation algorithm.
 *
 * @author Drew Sorrels (amsorr), Dennis Lysenko (dlysenko)
 * @author Nicholad Kilmer (nkilmer8)
 * @version 2013.12.03
 */
public class MazeChooserScreen
    extends Screen
{

    private CheckBox holesCheckbox;
    private CheckBox blinkingWallsCheckbox;
    private CheckBox enemiesCheckbox;
    private CheckBox slalomCheckbox;


    // ----------------------------------------------------------
    /**
     * Opens a prim-generated maze screen.
     */
    public void primClicked()
    {
        openMaze("prim");
    }


    // ----------------------------------------------------------
    /**
     * Opens a DFS-generated maze screen.
     */
    public void dfsClicked()
    {
        openMaze("dfs");
    }


    /**
     * Opens a maze screen passing the options from this screen as a bundle of
     * extras.
     */
    private void openMaze(String algorithm)
    {
        Intent intent = new Intent(this, MazeScreen.class);
        intent.putExtra("algorithm", algorithm);
        intent.putExtra("slalom", slalomCheckbox.isChecked());
        intent.putExtra("enemies", enemiesCheckbox.isChecked());
        intent.putExtra("blinkingWalls", blinkingWallsCheckbox.isChecked());
        intent.putExtra("holes", holesCheckbox.isChecked());
        startActivity(intent);
    }
}
