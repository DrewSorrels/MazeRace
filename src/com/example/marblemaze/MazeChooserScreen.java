package com.example.marblemaze;

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

    // ----------------------------------------------------------
    /**
     * Opens a prim-generated maze screen.
     */
    public void primClicked() {
        Intent intent = new Intent(this, MarbleMazeScreen.class);
        intent.putExtra("algorithm", "prim");
        startActivity(intent);
    }

    // ----------------------------------------------------------
    /**
     * Opens a DFS-generated maze screen.
     */
    public void dfsClicked() {
        Intent intent = new Intent(this, MarbleMazeScreen.class);
        intent.putExtra("algorithm", "dfs");
        startActivity(intent);
    }
}
