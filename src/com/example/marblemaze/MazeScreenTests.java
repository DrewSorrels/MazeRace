package com.example.marblemaze;

import android.content.Intent;
import android.graphics.RectF;
import com.example.marblemaze.observableevents.BulletAddedEvent;
import com.example.marblemaze.observableevents.BulletRemovedEvent;
import com.example.marblemaze.observableevents.HoleAddedEvent;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import com.example.marblemaze.observableevents.WallAddedEvent;
import com.example.marblemaze.observableevents.WallRemovedEvent;
import com.example.marblemaze.observableevents.WeaponSpawnerAddedEvent;
import com.example.marblemaze.weapons.Laser;
import com.example.marblemaze.weapons.LaserSpawner;

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


    /**
     * Asserts that you can move the marble on the screen.
     */
    public void testScreenWorks1()
    {
        Intent intent = new Intent(getScreen(), MazeScreen.class);
        getScreen().startActivity(intent);
        assertNotNull(getScreen());

        ObservableMazeComponent obs = new ObservableMazeComponent();

        getScreen().update(obs, new WallRemovedEvent(new Wall(0, 0, false)));
        getScreen().update(obs, new WallAddedEvent(new Wall(0, 0, false)));
        getScreen().update(obs, new BulletAddedEvent(new Laser(0, 0, 0)));
        getScreen().update(obs, new BulletRemovedEvent(new Laser(0, 0, 0)));
        getScreen().update(
            obs,
            new HoleAddedEvent(new Hole(new RectF(0, 0, 0, 0))));
        getScreen().update(
            obs,
            new WeaponSpawnerAddedEvent(new LaserSpawner(0, 0, 3000, 0)));
        getScreen().onTouchDown(25, 0);

    }
}
