package com.example.marblemaze;

import android.widget.RadioButton;
import android.content.Intent;
import sofia.app.Screen;

/**
 * // -------------------------------------------------------------------------
 * /** Select the maze generation algorithm.
 *
 * @author Drew Sorrels (amsorr), Dennis Lysenko (dlysenko)
 * @version 2013.12.03
 */
public class MazeChooserScreen
    extends Screen
{
    private RadioButton shadow;
    private RadioButton normal;
    private RadioButton action;


    // ----------------------------------------------------------
    /**
     * Opens a prim-generated maze screen.
     */
    public void primClicked()
    {
        Intent intent = new Intent(this, MazeScreen.class);
        intent.putExtra("algorithm", "prim");
        if (shadow.isChecked())
        {
            intent.putExtra("gamemode", "shadow");

        }
        else if (action.isChecked())
        {
            intent.putExtra("gamemode", "action");
        }
        startActivity(intent);
    }


    // ----------------------------------------------------------
    /**
     * Opens a DFS-generated maze screen.
     */
    public void dfsClicked()
    {
        Intent intent = new Intent(this, MazeScreen.class);
        intent.putExtra("algorithm", "dfs");
        if (shadow.isChecked())
        {
            intent.putExtra("gamemode", "shadow");
        }
        else if (action.isChecked())
        {
            intent.putExtra("gamemode", "action");
        }
        startActivity(intent);
    }
}
